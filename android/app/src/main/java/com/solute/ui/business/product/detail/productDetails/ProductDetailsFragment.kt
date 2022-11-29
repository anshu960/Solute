package com.solute.ui.business.product.detail.productDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.solute.R
import com.squareup.picasso.Picasso
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productSubCategory.handler.ProductSubCategoryHandler

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
            if(product.Image.isNotEmpty()){
                picasso.load(product.Image.first()).into(image)
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

}