package com.solute.ui.business.inventory.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

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
        productPrice?.text = "₹ ${product.ProductPrice?.MRP}"
        card?.setOnClickListener {
                ProductHandler.shared().repository.selectedProductLiveData.postValue(product)
                App.shared().mainActivity?.navController?.navigate(R.id.business_product_details_container)

        }
        MediaFileHandler.shared().viewModel?.loadFor(product.Id){
            if(it.isNotEmpty()){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    picasso.load(it.first().FileURL).into(image)
                }
            }else{
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    image?.setImageResource(R.drawable.image)
                }
            }
        }
    }
}