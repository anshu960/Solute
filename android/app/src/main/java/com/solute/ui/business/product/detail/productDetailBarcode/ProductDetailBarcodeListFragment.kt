package com.solute.ui.business.product.detail.productDetailBarcode

import android.Manifest
import android.content.ContentValues
import android.content.Intent
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
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.barcode.scanner.BarCodeScannerActivity
import com.solute.ui.business.product.detail.productDetailBarcode.adapter.ProductBarCodeAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailBarcodeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailBarcodeListFragment : Fragment() {

    private val CAMERA = 2

    var recycler: RecyclerView? = null
    var scanFabBtn: FloatingActionButton? = null


    var adapter : ProductBarCodeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail_barcode_list, container, false)
        recycler = view.findViewById(R.id.product_details_barcode_list_recycler)
        scanFabBtn = view.findViewById(R.id.product_details_barcode_list_fab)
        scanFabBtn?.setOnClickListener { onClickScanBarCode() }
//        scannerView?.visibility = View.GONE

        loadBarcodes()
        return view
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
            val intent = Intent(requireContext(), BarCodeScannerActivity::class.java)
            intent.putExtra("OPERATION","ADD_INVENTTORY")
            startActivity(intent)
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
