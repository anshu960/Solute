package com.solute.ui.business.inventory.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product
import com.utilitykit.feature.product.viewModel.ProductViewModalFactory
import com.utilitykit.feature.product.viewModel.ProductViewModel
import com.utilitykit.feature.productCategory.handler.ProductCategoryHandler
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.utilitykit.feature.productCategory.viewModel.ProductCategoryViewModel

class CreateCategoryActivity : UtilityActivity() {
    var categoryNameText : TextInputEditText? = null
    var saveButton : Button? = null
    var productCategoryViewModal : ProductCategoryViewModel? = null
    var backButton:ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)
        categoryNameText = findViewById(R.id.create_categoory_name_tiet)
        saveButton = findViewById(R.id.create_categoory_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        productCategoryViewModal = ViewModelProvider(
            this,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        ).get(
            ProductCategoryViewModel::class.java
        )
        ProductCategoryHandler.shared().setup(productCategoryViewModal!!)
        ProductCategoryHandler.shared().activity = this
        backButton = findViewById(R.id.create_categoory_header_back)
        backButton?.setOnClickListener { onBackPressed() }
    }

    fun onClickSave(){
        if(categoryNameText!= null && categoryNameText!!.text != null && categoryNameText!!.text!!.length > 2){
            productCategoryViewModal?.createNewCategory(categoryNameText!!.text.toString())
        }
    }

}