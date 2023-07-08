package com.friendly.framework.feature.product.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.R
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.model.Product
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class BusinessProductAdapter(
    val context: Context,
    val fragment: Fragment?,
    val allProduct: List<Product>,
    onClick:(product:Product)->Unit
) :
    RecyclerView.Adapter<BusinessProductViewHolder>() {
    val onClickProduct = onClick
    override fun getItemCount(): Int {
        return allProduct.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessProductViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BusinessProductViewHolder, position: Int) {
        val item = allProduct[position]
        holder.bind( fragment, item){prd->
            onClickProduct(prd)
        }
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

    fun bind( fragment: Fragment?, product: Product,onClick: (product: Product) -> Unit) {
        val picasso = Picasso.get()
//        image?.setImageResource(R.drawable.product_default_img)
        val imgUrl = MediaFileHandler.shared().viewModel?.find(product.Id)
        if(!imgUrl.isNullOrEmpty()){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                picasso.load(imgUrl).into(image)
            }
        }else{
            image?.setImageResource(R.drawable.image)
        }
        productName?.text = product.Name
        productDescription?.text = product.Description
        productPrice?.text = "₹ ${product.ProductPrice?.MRP}"
        val discount = discountToPercent(product)
        if (discount != "") {
            productDiscount?.visibility = View.VISIBLE
            productPrice?.visibility = View.VISIBLE
            productDiscount?.text = discount
        } else {
            productPrice?.visibility = View.GONE
            productDiscount?.visibility = View.GONE
        }
        productFinalPrice?.text = "₹ ${product.ProductPrice?.FinalPrice}"
        stepperContainer?.visibility = View.GONE

        if (product.ProductPrice?.Tax != null && product.ProductPrice?.Tax!! > 0) {
            productTax?.text = "₹ ${product.ProductPrice?.Tax}"
            taxIncludedCheckBox?.isChecked = product.ProductPrice?.TaxIncluded!!
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
//        addToCartBtn?.visibility = View.GONE
        addToCartBtn?.setOnClickListener { CartHandler.shared().addToCart(product) }
        addToCartBtn?.setOnLongClickListener { onLongPressAddToCart(fragment,product) }
        increaseButton?.setOnClickListener { CartHandler.shared().addToCart(product) }
        decreaseButton?.setOnClickListener { CartHandler.shared().removeFromCart(product) }
        image?.setOnClickListener {
                ProductHandler.shared().repository.selectedProductLiveData.postValue(product)
                onClick(product)
        }
    }

    private fun onLongPressAddToCart(fragment:Fragment?,product: Product):Boolean{
        val alert: AlertDialog.Builder = AlertDialog.Builder(fragment!!.context)
        val edittext = EditText(fragment!!.requireContext())
        edittext.inputType = InputType.TYPE_CLASS_NUMBER
        alert.setMessage("Add Quantity")
        alert.setTitle("Enter Your Desired quantity")

        alert.setView(edittext)

        alert.setPositiveButton("Done",
            DialogInterface.OnClickListener { dialog, whichButton -> //What ever you want to do with the value
                val valueEntered = edittext.text.toString()
                try {
                    val quantityValue = valueEntered.toInt()
                    CartHandler.shared().addToCart(product,quantityValue)
                }catch(e:Exception){

                }
            })

        alert.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, whichButton ->
                // what ever you want to do with No option.
            })

        alert.show()
        return true
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
        if (product.ProductPrice?.Discount != null && product.ProductPrice?.Discount!! > 0 && product.ProductPrice?.MRP != null) {
            val perc = ((product.ProductPrice?.Discount!! / product.ProductPrice?.MRP!!) * 100).toInt()
            off = perc.toString() + "%Off"
        }
        return off
    }

}