package com.solute.ui.business.inventory.product

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.squareup.picasso.Picasso
import com.utilitykit.Constants.Key.Companion.product
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.handler.MediaFileHandler
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
            if(context is BusinessActivity){
                ProductHandler.shared().repository.selectedProductLiveData.postValue(product)
                val activity = BusinessHandler.shared().activity as? BusinessActivity
                activity?.navController?.navigate(R.id.business_product_details_container)
            }
        }
        MediaFileHandler.shared().viewModel?.loadFor(product.Id){
            if(it.isNotEmpty()){
                picasso.load(it.first().FileURL).into(image)
            }else{
                image?.setImageResource(R.drawable.image)
            }
        }
    }
}