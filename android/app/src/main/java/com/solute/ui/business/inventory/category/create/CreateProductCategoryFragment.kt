package com.solute.ui.business.inventory.category.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.ToastService
import com.solute.navigation.AppNavigator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateProductCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateProductCategoryFragment : Fragment() {
    var categoryNameText : TextInputEditText? = null
    var saveButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductCategoryHandler.shared().onCreateNewCategory={
            ToastService.shared().toast("Category created Successfully")
            AppNavigator.shared().goBack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_product_category, container, false)
        categoryNameText = view.findViewById(R.id.create_categoory_name_tiet)
        saveButton = view.findViewById(R.id.create_categoory_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        return view
    }

    fun onClickSave(){
        if(categoryNameText!= null && categoryNameText!!.text != null && categoryNameText!!.text!!.length > 2){
            ProductCategoryHandler.shared().viewModel?.createNewCategory(categoryNameText!!.text.toString())
        }
    }
}