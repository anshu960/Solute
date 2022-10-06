package com.solute.ui.business.inventory.product

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.BusinessMainActivity
import com.solute.ui.business.product.detail.ProductDetailActivity
import com.squareup.picasso.Picasso
import com.utilitykit.Constants.Key.Companion.image
import com.utilitykit.Constants.Key.Companion.productName
import com.utilitykit.feature.product.handler.ProductHandler
import com.utilitykit.feature.product.model.Product

class ProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product, parent, false
    )
) {
    private var image : ImageView? = null
    private var card : CardView? = null
    private var productName: TextView? = null
    private var productDescription: TextView? = null
    private var productPrice: TextView? = null
    init {
        image = itemView.findViewById(R.id.product_item_image)
        card = itemView.findViewById(R.id.product_item_card)
        productName = itemView.findViewById(R.id.product_item_name)
        productDescription = itemView.findViewById(R.id.product_item_description)
        productPrice = itemView.findViewById(R.id.product_item_mrp)
    }

    fun bind(context: Context, product: Product) {
        val picasso = Picasso.get()
        productName?.text = product.Name
        productDescription?.text = product.Description
        productPrice?.text = "₹ ${product.MRP}"
        card?.setOnClickListener {
            if(context is BusinessMainActivity){
                ProductHandler.shared().repository.selectedProductLiveData.postValue(product)
                val intent = Intent(context,ProductDetailActivity::class.java)
                context.startActivity(intent)
            }
        }
        if(product.Image.isNotEmpty()){
            picasso.load(product.Image.first()).into(image)
        }else{
            image?.setImageResource(R.drawable.image)
        }
    }
}