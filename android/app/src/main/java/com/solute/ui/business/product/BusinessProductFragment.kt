package com.solute.ui.business.product

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.solute.R
import com.solute.app.ToastService
import com.solute.navigation.AppNavigator
//import com.solute.ui.business.barcode.BarCodeAnalyzer
import com.solute.ui.business.barcode.BarCodeBoxView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BusinessProductFragment : Fragment() {
    private val CAMERA = 2
    var recyclerView: RecyclerView? = null
    private var adapter: BusinessProductAdapter? = null
    var allProduct: ArrayList<Product> = ArrayList()
    var cartButton : FloatingActionButton? = null
    var scanBtn : FloatingActionButton? = null
    var searchView : SearchView? = null
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeBoxView: BarCodeBoxView
    var onDetectNewBarcode: ((code: String) -> Unit)? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        ProductHandler.shared().viewModel?.allProduct?.observe(this) {
            allProduct = it as ArrayList<Product>
            this.reload()
        }

        onDetectNewBarcode = {barCode->
            CoroutineScope(Job() + Dispatchers.Main).launch {
                CartHandler.shared().addToCart(barCode){status->
                    ToastService.shared().toast(status)
                }
            }
        }
    }

    fun reload() {
        if(ProductHandler.shared().repository.productLiveData.value != null){
            allProduct = ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
        }
        this.recyclerView!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }
    fun search(query:String){
        allProduct = arrayListOf()
        if(ProductHandler.shared().repository.productLiveData.value != null){
            val unFilteredList= ProductHandler.shared().repository.productLiveData.value as ArrayList<Product>
            unFilteredList.forEach {
                if(it.Name?.contains(query) == true || it.Description?.contains(query) == true){
                    allProduct.add(it)
                }
            }
        }
        this.recyclerView!!.layoutManager = GridLayoutManager(this.context,2)
        adapter = this.context?.let { BusinessProductAdapter(it,this ,allProduct) }
        this.recyclerView!!.adapter = this.adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_sale, container, false)
        recyclerView = view.findViewById(R.id.fragment_business_product_recycler)
        scanBtn = view.findViewById(R.id.fragment_business_product_scan_btn)
        scanBtn?.setOnClickListener {onClickScan()}
        cartButton = view.findViewById(R.id.fragment_business_product_cart_btn)
        cartButton?.setOnClickListener { onClickCart() }
        searchView = view.findViewById(R.id.fragment_business_product_search)
        searchView?.isIconified = false
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // do something on text submit
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // do something when text changes
                if(newText != ""){
                    search(newText)
                }else{
                    reload()
                }
                return false
            }
        })
        ProductHandler.shared().viewModel?.loadProduct()
        ProductHandler.shared().viewModel?.fetchAllProduct()
        reload()
        return view
    }

    fun onClickCart(){
        AppNavigator.shared().gotToCart()
    }
    fun onClickScan(){
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
        val optionsBuilder = GmsBarcodeScannerOptions.Builder()
//        if (allowManualInput) {
//            optionsBuilder.allowManualInput()
//        }
        val gmsBarcodeScanner = GmsBarcodeScanning.getClient(requireContext(), optionsBuilder.build())
        gmsBarcodeScanner
            .startScan()
            .addOnSuccessListener { barcode: Barcode ->
                if(barcode.displayValue != null){
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        CartHandler.shared().addToCart(barcode.displayValue!!){status->
                            ToastService.shared().toast(status)
                        }
                    }
                }
//                barcodeResultView!!.text = getSuccessfulMessage(barcode)
            }
//            .addOnFailureListener { e: Exception -> barcodeResultView!!.text = getErrorMessage(e) }
            .addOnCanceledListener {
//                barcodeResultView!!.text = getString(R.string.error_scanner_cancelled)
            }

//        if(scannerView?.visibility == View.VISIBLE){
//            scannerView?.visibility = View.GONE
//            cameraExecutor.shutdown()
//            return
//        }
//        scannerView?.visibility = View.VISIBLE
//        cameraExecutor = Executors.newSingleThreadExecutor()
//        barcodeBoxView = BarCodeBoxView(requireContext())
////        addContentView(barcodeBoxView, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//        barcodeAnalyser = BarCodeAnalyzer(
//            requireContext(),
//            barcodeBoxView,
//            onDetectNewBarcode,
//            this.scannerView!!.width.toFloat(),
//            this.scannerView!!.height.toFloat()
//        )
//        startCamera()
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