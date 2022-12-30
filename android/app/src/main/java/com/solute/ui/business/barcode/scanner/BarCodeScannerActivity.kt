package com.solute.ui.business.barcode.scanner

import android.os.Bundle
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.solute.R
import com.solute.ui.business.barcode.BarCodeAnalyzer
import com.solute.ui.business.barcode.BarCodeBoxView
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.product.handler.ProductHandler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BarCodeScannerActivity : UtilityActivity() {
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeBoxView: BarCodeBoxView
    var barcodeAnalyser: BarCodeAnalyzer? = null

    var onDetectNewBarcode: ((code: String) -> Unit)? = null
    var scannerView: PreviewView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code_scanner)
        scannerView  = findViewById(R.id.bar_code_scannner_preview)
        cameraExecutor = Executors.newSingleThreadExecutor()
        onDetectNewBarcode = {
            this.runOnUiThread {
                toastLong("BarCode : " + it)
                if (ProductHandler.shared().repository.selectedProduct.value != null) {
                    val product = ProductHandler.shared().repository.selectedProduct.value!!
                    ProductHandler.shared().onCreateProductBarCodeCallBack={
                        this.runOnUiThread {
                            super.onBackPressed()
                        }
                    }
                    ProductHandler.shared().viewModel?.createBarCode(product, it)
                }
            }
        }

        barcodeBoxView = BarCodeBoxView(this)
        addContentView(barcodeBoxView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        barcodeAnalyser = BarCodeAnalyzer(
            this,
            barcodeBoxView,
            onDetectNewBarcode,
            this.scannerView!!.width.toFloat(),
            this.scannerView!!.height.toFloat()
        )
        startCamera()
    }
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        this.let { ContextCompat.getMainExecutor(it) }?.let { it ->
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(this.scannerView?.surfaceProvider)
                    }
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(
                            cameraExecutor,
                            this.barcodeAnalyser!!
                        )
                    }
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
            }, it)
        }
    }
}