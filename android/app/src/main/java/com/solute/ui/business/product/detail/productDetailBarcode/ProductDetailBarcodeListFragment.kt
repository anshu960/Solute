package com.solute.ui.business.product.detail.productDetailBarcode

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.solute.R
import com.solute.scanner.BarCodeAnalyzer
import com.solute.scanner.BarcodeBoxView
import com.solute.ui.business.BusinessActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailBarcodeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailBarcodeListFragment : Fragment() {
    private val CAMERA = 2
    val activity = BusinessHandler.shared().activity as? BusinessActivity
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeBoxView: BarcodeBoxView

    var recycler: RecyclerView? = null
    var scanFabBtn: FloatingActionButton? = null
    var scannerView : PreviewView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()

        barcodeBoxView = BarcodeBoxView(this.requireContext())
//        addContentView(barcodeBoxView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail_barcode_list, container, false)
        scannerView = view.findViewById(R.id.product_details_barcode_list_scanner)
        recycler = view.findViewById(R.id.product_details_barcode_list_recycler)
        scanFabBtn = view.findViewById(R.id.product_details_barcode_list_fab)
        scanFabBtn?.setOnClickListener { onClickScanBarCode() }
//        scannerView?.visibility = View.GONE


        return view
    }

    fun onClickScanBarCode() {
        val permission = ContextCompat.checkSelfPermission(requireActivity(),
            Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(ContentValues.TAG, "Permission to record denied")
            makeRequest()
        }else{
            startCamera()
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        cameraExecutor.shutdown()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this.requireContext())

        this.context?.let { ContextCompat.getMainExecutor(it) }?.let { it ->
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                // Preview
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(this.scannerView?.surfaceProvider)
                    }

                // Image analyzer
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        this.context?.let { it1 ->
                            BarCodeAnalyzer(
                                it1,
                                barcodeBoxView,
                                this.scannerView!!.width.toFloat(),
                                this.scannerView!!.height.toFloat()
                            )
                        }?.let { it2 ->
                            it.setAnalyzer(
                                cameraExecutor,
                                it2
                            )
                        }
                    }

                // Select back camera as a default
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageAnalyzer
                    )

                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
            }, it)
        }
    }




    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE),
            this.CAMERA)
    }

    fun onScanNewBarcode(code: String) {

    }

}