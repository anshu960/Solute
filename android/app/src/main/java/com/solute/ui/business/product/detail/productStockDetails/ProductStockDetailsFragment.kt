package com.solute.ui.business.product.detail.productStockDetails

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.product.detail.ProductDetailActivity
import com.solute.ui.business.product.detail.productStockDetails.adapter.ProductDetailStockAdapter
import com.utilitykit.database.Database
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.ProductStock


class ProductStockDetailsFragment : Fragment() {

    var recycler : RecyclerView? = null
    var allStock : ArrayList<ProductStock> = arrayListOf()
    var adapter : ProductDetailStockAdapter? = null
    var removeQuantityBtn : Button? = null
    var resetQuantityBtn : Button? = null
    var addQuantityBtn : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_stock_details, container, false)
        recycler = view.findViewById(R.id.fragment_product_details_recycler)
        removeQuantityBtn = view.findViewById(R.id.fragment_product_details_remove_quantity_btn)
        addQuantityBtn = view.findViewById(R.id.fragment_product_details_add_quantity_btn)
        resetQuantityBtn = view.findViewById(R.id.fragment_product_details_reset_btn)
        removeQuantityBtn?.setOnClickListener { removeQuantity() }
        addQuantityBtn?.setOnClickListener { addQuantity() }
        resetQuantityBtn?.setOnClickListener { resetQuantity() }
        loadData()
        ProductHandler.shared().onUpdateProductCallBack={
            if(context is ProductDetailActivity){
                (context as ProductDetailActivity).runOnUiThread {
                    if(it?.Id != null){
                        Toast.makeText(this.context, "Stock Updated Successfully", Toast.LENGTH_SHORT).show()
                    }

                    loadData()
                }
            }
        }
        return  view
    }

    fun loadData(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null) {
            allStock = Database.shared().getAllStockForProduct(ProductHandler.shared().repository.selectedProduct.value!!)
        }
        adapter = ProductDetailStockAdapter(allStock)
        recycler?.layoutManager = LinearLayoutManager(this.context)
        recycler?.adapter = adapter
    }

    fun removeQuantity(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null) {
            val quantityEditText = EditText(this.context)
            quantityEditText.inputType = InputType.TYPE_CLASS_NUMBER
            quantityEditText.hint = "Quantity"
            val messageEditText = EditText(this.context)
            messageEditText.inputType = InputType.TYPE_CLASS_TEXT
            messageEditText.hint = "Comment"
            val inputFieldsLayout = LinearLayout(this.context)
            inputFieldsLayout.orientation = LinearLayout.VERTICAL
            inputFieldsLayout.addView(quantityEditText)
            inputFieldsLayout.addView(messageEditText)
            inputFieldsLayout.setPadding(40,0,40,0)
            val dialog: AlertDialog = AlertDialog.Builder(this.context)
                .setTitle("Remove Quanity from Stock")
                .setMessage("Please enter quantity you want to remove ?")
                .setView(inputFieldsLayout)
                .setPositiveButton("Remove"
                ) { dialog, which ->
                    val quantity = quantityEditText.text.toString().toInt()
                    val message = messageEditText.text.toString()
                    ProductHandler.shared().productViewModel?.removeStockQuantity(
                        ProductHandler.shared().repository.selectedProduct.value!!,
                        quantity,
                        message
                    )
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }
    }

    fun addQuantity(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null) {
            val quantityEditText = EditText(this.context)
            quantityEditText.inputType = InputType.TYPE_CLASS_NUMBER
            quantityEditText.hint = "Quantity"
            val messageEditText = EditText(this.context)
            messageEditText.inputType = InputType.TYPE_CLASS_TEXT
            messageEditText.hint = "Comment"
            val inputFieldsLayout = LinearLayout(this.context)
            inputFieldsLayout.orientation = LinearLayout.VERTICAL
            inputFieldsLayout.addView(quantityEditText)
            inputFieldsLayout.addView(messageEditText)
            inputFieldsLayout.setPadding(40,0,40,0)
            val dialog: AlertDialog = AlertDialog.Builder(this.context)
                .setTitle("Add Quanity to Stock")
                .setMessage("Please enter quantity you want to add ?")
                .setView(inputFieldsLayout)
                .setPositiveButton("Add"
                ) { dialog, which ->
                    val quantity = quantityEditText.text.toString().toInt()
                    val message = messageEditText.text.toString()
                    ProductHandler.shared().productViewModel?.addStockQuantity(
                        ProductHandler.shared().repository.selectedProduct.value!!,
                        quantity,
                        message
                    )
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }
    }

    fun resetQuantity(){
        if(ProductHandler.shared().repository.selectedProduct != null && ProductHandler.shared().repository.selectedProduct.value != null) {
            val quantityEditText = EditText(this.context)
            quantityEditText.inputType = InputType.TYPE_CLASS_NUMBER
            quantityEditText.hint = "Quantity"
            val messageEditText = EditText(this.context)
            messageEditText.inputType = InputType.TYPE_CLASS_TEXT
            messageEditText.hint = "Comment"
            val inputFieldsLayout = LinearLayout(this.context)
            inputFieldsLayout.orientation = LinearLayout.VERTICAL
            inputFieldsLayout.addView(quantityEditText)
            inputFieldsLayout.addView(messageEditText)
            inputFieldsLayout.setPadding(40,0,40,0)
            val dialog: AlertDialog = AlertDialog.Builder(this.context)
                .setTitle("Add Quanity To Reset your stock")
                .setMessage("Please enter quantity you want to reset to ?")
                .setView(inputFieldsLayout)
                .setPositiveButton("Reset"
                ) { dialog, which ->
                    val quantity = quantityEditText.text.toString().toInt()
                    val message = messageEditText.text.toString()
                    ProductHandler.shared().productViewModel?.resetStockQuantity(
                        ProductHandler.shared().repository.selectedProduct.value!!,
                        quantity,
                        message
                    )
                }
                .setNegativeButton("Cancel", null)
                .create()
            dialog.show()
        }
    }

}