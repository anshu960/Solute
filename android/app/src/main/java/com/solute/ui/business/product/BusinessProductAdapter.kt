package com.solute.ui.business.product

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.business.BusinessMainActivity
import com.utilitykit.feature.cart.helper.CartHelper
import com.utilitykit.feature.product.model.Product

class BusinessProductAdapter(val context: Context,val fragment: Fragment,val allProduct: List<Product>) :
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
        holder.itemView.setOnClickListener {
            if(context is MainActivity){
                val mainActivty = context as MainActivity
                val intent = Intent(mainActivty, BusinessMainActivity::class.java)
                mainActivty.startActivity(intent)
            }
        }
        holder.bind(fragment,item)
    }
}

class BusinessProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_product, parent, false
    )
) {
    private var productName: TextView? = null
    private var productDescription: TextView? = null
    private var productPrice: TextView? = null
    private var productDiscount: TextView? = null
    private var productFinalPrice: TextView? = null
    private var productQuantity: TextView? = null
    private var stepperContainer: LinearLayout? = null
    private var addToCartCard: CardView? = null
    private var increaseButton: ImageButton? = null
    private var decreaseButton: ImageButton? = null

    init {
        productName = itemView.findViewById(R.id.recycler_item_product_name_txt)
        productDescription = itemView.findViewById(R.id.recycler_item_product_description_txt)
        productPrice = itemView.findViewById(R.id.recycler_item_product_price_txt)
        productDiscount = itemView.findViewById(R.id.recycler_item_product_discount_txt)
        productFinalPrice = itemView.findViewById(R.id.recycler_item_product_final_price_txt)
        productQuantity = itemView.findViewById(R.id.recycler_item_product_stepper_count_txt)
        stepperContainer = itemView.findViewById(R.id.recycler_item_product_stepper_layout)
        addToCartCard = itemView.findViewById(R.id.recycler_item_product_add_to_cart_card)
        increaseButton = itemView.findViewById(R.id.recycler_item_product_stepper_add_btn)
        decreaseButton = itemView.findViewById(R.id.recycler_item_product_stepper_remove_btn)
    }

    fun bind(fragment:Fragment,product: Product) {
        productName?.text = product.Name
        productDescription?.text = product.Description
        productPrice?.text = "₹ " + product.MRP.toString()
        productDiscount?.text = "₹ " + product.Discount.toString()
        productFinalPrice?.text = "₹ " + product.FinalPrice.toString()
        stepperContainer?.visibility = View.GONE
        updateQuanity(product)
        CartHelper.shared().viewModel?.cart?.observe(fragment) {
            updateQuanity(product)
        }
        addToCartCard?.setOnClickListener { CartHelper.shared().addToCart(product) }
        increaseButton?.setOnClickListener { CartHelper.shared().addToCart(product) }
        decreaseButton?.setOnClickListener { CartHelper.shared().removeFromCart(product) }
    }
    fun updateQuanity(product:Product){
        val quanity = CartHelper.shared().viewModel?.getProductQuantity(product)
        if(quanity != null &&  quanity>0){
            stepperContainer?.visibility = View.VISIBLE
            addToCartCard?.visibility = View.GONE
            productQuantity?.text = quanity.toString()
        }else{
            stepperContainer?.visibility = View.GONE
            addToCartCard?.visibility = View.VISIBLE
        }
    }

}