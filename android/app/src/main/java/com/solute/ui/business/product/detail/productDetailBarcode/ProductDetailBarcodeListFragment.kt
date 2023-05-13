package com.solute.ui.business.product.detail.productDetailBarcode

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.product.handler.ProductHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.solute.R
import com.solute.app.ToastService
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
    var cameraExecutor: ExecutorService? = null
    private lateinit var barcodeBoxView: BarCodeBoxView
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
        makeRequest()
        return view
    }
    override fun onDestroy() {
        super.onDestroy()
        if(cameraExecutor != null){
            cameraExecutor?.shutdown()
        }
    }
    private fun loadBarcodes(){
        if(this.context != null){
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
    }

    private fun onClickScanBarCode() {
        val optionsBuilder = GmsBarcodeScannerOptions.Builder()
//        optionsBuilder.allowManualInput()
        val gmsBarcodeScanner = GmsBarcodeScanning.getClient(requireContext(), optionsBuilder.build())
        gmsBarcodeScanner
            .startScan()
            .addOnSuccessListener { barcode: Barcode ->
                if(barcode.displayValue != null){
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        ToastService.shared().toast("BarCode : " + barcode.displayValue)
                        if (ProductHandler.shared().repository.selectedProduct.value != null) {
                            val product = ProductHandler.shared().repository.selectedProduct.value!!
                            ProductHandler.shared().onCreateProductBarCodeCallBack={
                                ToastService.shared().toast("Bar Code Saved")
                                CoroutineScope(Job() + Dispatchers.Main).launch {
                                    loadBarcodes()
                                }
                            }
                            ProductHandler.shared().viewModel?.createBarCode(product, barcode.displayValue!!)
                        }
                    }
                }
            }
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



}
