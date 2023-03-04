package com.solute.ui.business.inventory.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.google.android.material.textfield.TextInputEditText
import com.solute.R


class CreateCategoryActivity : UtilityActivity() {
    var categoryNameText : TextInputEditText? = null
    var saveButton : Button? = null
    var backButton:ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        categoryNameText = findViewById(R.id.create_categoory_name_tiet)
        saveButton = findViewById(R.id.create_categoory_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        ProductCategoryHandler.shared().activity = this
        backButton = findViewById(R.id.create_categoory_header_back)
        backButton?.setOnClickListener { onBackPressed() }
    }

    fun onClickSave(){
        if(categoryNameText!= null && categoryNameText!!.text != null && categoryNameText!!.text!!.length > 2){
            ProductCategoryHandler.shared().viewModel?.createNewCategory(categoryNameText!!.text.toString())
        }
    }

}