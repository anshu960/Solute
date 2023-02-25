package com.solute.ui.business.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BusinessProductAdapter(
    val context: Context,
    val fragment: Fragment?,
    val allProduct: List<Product>
) :
    RecyclerView.Adapter<BusinessProductViewHolder>() {

    override fun getItemCount(): Int {
        return allProduct.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BusinessProductViewHolder, position: Int) {
        val item = allProduct[position]
        holder.bind(context, fragment, item)
    }
}

class BusinessProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.recycler_item_product_sale, parent, false
        )
    ) {
    private var image: ImageView? = null
    private var productName: TextView? = null
    private var productDescription: TextView? = null
    private var productPrice: TextView? = null
    private var productDiscount: TextView? = null
    private var productFinalPrice: TextView? = null
    private var productQuantity: TextView? = null
    private var stepperContainer: CardView? = null
    private var addToCartBtn: ImageButton? = null
    private var increaseButton: ImageButton? = null
    private var decreaseButton: ImageButton? = null
    private var productTax: TextView? = null
    private var taxIncludedCheckBox: CheckBox? = null
    private var taxLable: TextView? = null

    init {
        image = itemView.findViewById(R.id.recycler_item_product_image)
        productName = itemView.findViewById(R.id.recycler_item_product_name_txt)
        productPrice = itemView.findViewById(R.id.recycler_item_product_mrp_txt)
        productDiscount = itemView.findViewById(R.id.recycler_item_product_discount_txt)
        productFinalPrice = itemView.findViewById(R.id.recycler_item_product_final_price_txt)
        productQuantity = itemView.findViewById(R.id.recycler_item_product_stepper_count_txt)
        stepperContainer = itemView.findViewById(R.id.recycler_item_product_stepper_layout)
        addToCartBtn = itemView.findViewById(R.id.recycler_item_product_cart_btn)
        increaseButton = itemView.findViewById(R.id.recycler_item_product_stepper_add_btn)
        decreaseButton = itemView.findViewById(R.id.recycler_item_product_stepper_remove_btn)
    }

    fun bind(context: Context, fragment: Fragment?, product: Product) {
        val picasso = Picasso.get()
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
        productName?.text = product.Name
        productDescription?.text = product.Description
        productPrice?.text = "₹ ${product.MRP}"
        val discount = discountToPercent(product)
        if (discount != "") {
            productDiscount?.visibility = View.VISIBLE
            productPrice?.visibility = View.VISIBLE
            productDiscount?.text = discount
        } else {
            productPrice?.visibility = View.GONE
            productDiscount?.visibility = View.GONE
        }
        productFinalPrice?.text = "₹ ${product.FinalPrice}"
        stepperContainer?.visibility = View.GONE

        if (product.Tax != null && product.Tax!! > 0) {
            productTax?.text = "₹ ${product.Tax}"
            taxIncludedCheckBox?.isChecked = product.TaxIncluded!!
            productTax?.visibility = View.VISIBLE
            taxIncludedCheckBox?.visibility = View.VISIBLE
            taxLable?.visibility = View.VISIBLE
        } else {
            productTax?.visibility = View.GONE
            taxIncludedCheckBox?.visibility = View.GONE
            taxLable?.visibility = View.GONE
        }
        taxIncludedCheckBox?.isEnabled = false
        updateQuanity(product)
        fragment.let {
            if (it != null) {
                CartHandler.shared().viewModel?.cart?.observe(it) {
                    updateQuanity(product)
                }
            }
        }
        addToCartBtn?.setOnClickListener { CartHandler.shared().addToCart(product) }
        increaseButton?.setOnClickListener { CartHandler.shared().addToCart(product) }
        decreaseButton?.setOnClickListener { CartHandler.shared().removeFromCart(product) }
        image?.setOnClickListener {
            if (context is BusinessActivity) {
                ProductHandler.shared().repository.selectedProductLiveData.postValue(product)
                val activity = BusinessHandler.shared().activity as? BusinessActivity
                activity?.navController?.navigate(R.id.business_product_details_container)
            }
        }
    }

    fun updateQuanity(product: Product) {
        val quanity = CartHandler.shared().viewModel?.getProductQuantity(product)
        if (quanity != null && quanity > 0) {
            stepperContainer?.visibility = View.VISIBLE
            addToCartBtn?.visibility = View.GONE
            productQuantity?.text = quanity.toString()
        } else {
            stepperContainer?.visibility = View.GONE
            addToCartBtn?.visibility = View.VISIBLE
        }
    }

    fun discountToPercent(product: Product): String {
        var off = ""
        if (product.Discount != null && product.Discount!! > 0 && product.MRP != null) {
            val perc = ((product.Discount!! / product.MRP!!) * 100).toInt()
            off = perc.toString() + "%Off"
        }
        return off
    }

}