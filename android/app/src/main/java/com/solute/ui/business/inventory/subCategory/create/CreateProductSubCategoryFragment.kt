package com.solute.ui.business.inventory.subCategory.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.ToastService

class CreateProductSubCategoryFragment : Fragment() {
    var productCategoryText : TextView? = null
    var productSubCategoryText : TextInputEditText? = null
    var saveBtn : Button? = null

    var allCategoory: ArrayList<ProductCategory> = ArrayList()
    var selectedCategoryIndex = 0
    var selectedCategoryName = ""
    var selectedCategory : ProductCategory? = null
    var backButton : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductCategoryHandler.shared().repository.allCategory.observe(this){
            if(!it.isNullOrEmpty()){
                allCategoory = it as ArrayList<ProductCategory>
            }
        }
        ProductSubCategoryHandler.shared().onCreateNewSubCategory={
            ToastService.shared().toast("Sub Category Create successfully")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_product_sub_category, container, false)
        productCategoryText = view.findViewById(R.id.create_sub_category_category_txt)
        productCategoryText?.setOnClickListener { showCategorySellection() }
        productSubCategoryText = view.findViewById(R.id.create_sb_category_name_tiet)
        saveBtn = view.findViewById(R.id.create_sb_category_save_btn)
        saveBtn?.setOnClickListener { onClickSave() }
        return view
    }
    fun showCategorySellection(){
        var allCategoryNames : Array<String> = Array(allCategoory.count()){""}
        var index = 0
        allCategoory.forEach {
            it.Name?.let { it1 ->
                allCategoryNames.set(index,it1)
                if(it1 == selectedCategoryName){
                    selectedCategoryIndex = index
                    selectedCategory = it
                }
            }
            index+=1
        }
        index = -1
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose an Category")
        builder.setSingleChoiceItems(allCategoryNames, selectedCategoryIndex) { dialog, which ->
            productCategoryText?.text = allCategoryNames[which]
            allCategoory.forEach {
                it.Name?.let { it1 ->
                    if(allCategoryNames[which] == it1){
                        selectedCategoryIndex = index
                        selectedCategory = it
                    }
                }
                index+=1
            }
            index = 0
        }
        builder.setPositiveButton("OK") { dialog, which ->
            if(selectedCategory == null && allCategoory.isNotEmpty()){
                selectedCategory = allCategoory[0]
                selectedCategoryIndex = 0
                productCategoryText?.text = allCategoory[0].Name
            }
        }
        builder.setNegativeButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }

    fun onClickSave(){
        val subCategory = productSubCategoryText?.text.toString()
        if(selectedCategory != null && !subCategory.isEmpty()){
            ProductSubCategoryHandler.shared().viewModel?.createNewSubCategory(subCategory!!,selectedCategory!!)
        }
    }

}