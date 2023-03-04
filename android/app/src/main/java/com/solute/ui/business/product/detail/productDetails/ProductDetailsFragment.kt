package com.solute.ui.business.product.detail.productDetails

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.App
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductDetailsFragment : Fragment() {
    var image : ImageView? = null
    var productName : TextView? = null
    var productDescription : TextView? = null
    var productCategory : TextView? = null
    var productSubCategory : TextView? = null
    var deleteButton : FloatingActionButton? = null
    var fabButton : FloatingActionButton? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        image = view.findViewById(R.id.fragment_product_detail_img)
        productName = view.findViewById(R.id.fragment_product_detail_name)
        productDescription = view.findViewById(R.id.fragment_product_detail_description)
        productCategory = view.findViewById(R.id.fragment_product_detail_category)
        productSubCategory = view.findViewById(R.id.fragment_product_detail_sub_category)
        deleteButton = view.findViewById(R.id.activity_product_delete_fab)
        deleteButton?.setOnClickListener { onClickDeleteProduct() }
        fabButton = view.findViewById(R.id.activity_product_details_fab)
        fabButton?.setOnClickListener { onClickEdit() }
        loadData()
        return view
    }

    fun loadData(){
        val picasso = Picasso.get()
        if(ProductHandler.shared().repository.selectedProduct.value != null){
            val product = ProductHandler.shared().repository.selectedProduct.value!!
            productName?.text = product.Name
            productDescription?.text = product.Description
            productCategory?.text = product.CategoryID
            productSubCategory?.text = product.SubCategoryID
            MediaFileHandler.shared().viewModel?.loadFor(product.Id){
                if(it.isNotEmpty()){
                    CoroutineScope(Job() + Dispatchers.Main).launch {
                        picasso.load(it.first().FileURL).into(image)
                    }
                }
            }
            if(ProductCategoryHandler.shared().repository.allCategory.value != null){
                ProductCategoryHandler.shared().repository.allCategory.value!!.forEach {
                    if(it.Id == product.CategoryID){
                        productCategory?.text = it.Name
                    }
                }
            }
            if(ProductSubCategoryHandler.shared().repository.allSubCategory.value != null){
                ProductSubCategoryHandler.shared().repository.allSubCategory.value!!.forEach {
                    if(it.Id == product.SubCategoryID){
                        productSubCategory?.text = it.Name
                    }
                }
            }
        }
    }

    fun onClickDeleteProduct(){
        AlertDialog.Builder(activity)
            .setMessage("Are you sure you want to delete ?")
            .setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, id ->
                    deleteProduct()
                })
            .setNegativeButton("No", null)
            .show()
    }

    fun onClickEdit(){
        App.shared().mainActivity?.navController?.navigate(R.id.business_product_create)
    }

    fun deleteProduct(){
        if(ProductHandler.shared().repository.selectedProduct.value != null){
            ProductHandler.shared().onDeleteProductCallBack={
                if(it != null){
                    App.shared().mainActivity?.runOnUiThread {
                        App.shared().mainActivity?.toastLong("Product Deleted Successfully");
                        App.shared().mainActivity?.onBackPressed()
                    }
                }
            }
            ProductHandler.shared().viewModel?.deleteProduct(ProductHandler.shared().repository.selectedProduct.value!!)
        }
    }


}