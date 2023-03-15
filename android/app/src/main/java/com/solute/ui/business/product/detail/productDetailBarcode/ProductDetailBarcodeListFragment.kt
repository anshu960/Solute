package com.solute.ui.business.product.detail.productDetailBarcode

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.ToastService
import com.solute.ui.business.barcode.BarCodeAnalyzer
import com.solute.ui.business.barcode.BarCodeBoxView
import com.solute.ui.business.product.detail.productDetailBarcode.adapter.ProductBarCodeAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ProductDetailBarcodeListFragment : Fragment() {

    private val CAMERA = 2

    var recycler: RecyclerView? = null
    var scanFabBtn: FloatingActionButton? = null
    var adapter : ProductBarCodeAdapter? = null
    var scannerView : PreviewView? = null
    var cameraExecutor: ExecutorService? = null
    private lateinit var barcodeBoxView: BarCodeBoxView
    var barcodeAnalyser: BarCodeAnalyzer? = null
    var onDetectNewBarcode: ((code: String) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail_barcode_list, container, false)
        recycler = view.findViewById(R.id.product_details_barcode_list_recycler)
        scanFabBtn = view.findViewById(R.id.product_details_barcode_list_fab)
        scanFabBtn?.setOnClickListener { onClickScanBarCode() }
        scannerView = view.findViewById(R.id.product_barcode_scanner_preview)
        loadBarcodes()
        onDetectNewBarcode = {
            CoroutineScope(Job() + Dispatchers.Main).launch {
                ToastService.shared().toast("BarCode : " + it)
                if (ProductHandler.shared().repository.selectedProduct.value != null) {
                    val product = ProductHandler.shared().repository.selectedProduct.value!!
                    ProductHandler.shared().onCreateProductBarCodeCallBack={
                        ToastService.shared().toast("Bar Code Saved")
                    }
                    ProductHandler.shared().viewModel?.createBarCode(product, it)
                }
            }
        }
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        if(cameraExecutor != null){
            cameraExecutor?.shutdown()
        }
    }
    fun loadBarcodes(){
        ProductHandler.shared().repository.productBarCode.observe(requireParentFragment().viewLifecycleOwner){
            this.adapter = this.context?.let { it1 ->
                ProductBarCodeAdapter(it1,it){

                }
            }
            recycler?.layoutManager = LinearLayoutManager(this.context)
            recycler?.adapter = this.adapter
        }
        if (ProductHandler.shared().repository.selectedProduct.value != null) {
            val product = ProductHandler.shared().repository.selectedProduct.value!!
            ProductHandler.shared().viewModel?.loadProductBarCode(product)
        }

    }

    fun onClickScanBarCode() {
        val permission = ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.CAMERA
        )
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(ContentValues.TAG, "Permission to record denied")
            makeRequest()
        } else {
            startScanner()
        }
    }

    fun startScanner(){
        scannerView?.visibility = View.VISIBLE
        cameraExecutor = Executors.newSingleThreadExecutor()
        barcodeBoxView = BarCodeBoxView(requireContext())
//        addContentView(barcodeBoxView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        barcodeAnalyser = BarCodeAnalyzer(
            requireContext(),
            barcodeBoxView,
            onDetectNewBarcode,
            this.scannerView!!.width.toFloat(),
            this.scannerView!!.height.toFloat()
        )
        startCamera()
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.LOCATION_HARDWARE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            this.CAMERA
        )
    }
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        this.let { ContextCompat.getMainExecutor(requireContext()) }?.let { it ->
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
                            cameraExecutor!!,
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
