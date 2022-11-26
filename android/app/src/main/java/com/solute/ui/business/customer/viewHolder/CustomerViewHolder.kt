package com.solute.ui.business.customer.viewHolder

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.solute.R
import com.utilitykit.Constants.Key.Companion.customer
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer

class CustomerViewHolder (inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_customer, parent, false
    )
) {
    private var name: TextView? = null
    private var mobile: TextView? = null
    var invoiceCount : TextView? = null
    var totalInvoiceValue : TextView? = null
    var barcodeImg : ImageView? = null
    var barcodeTxt : TextView? = null
    var selectionImg : ImageView? = null

    init {
        name = itemView.findViewById(R.id.recycler_item_customer_name_txt)
        mobile = itemView.findViewById(R.id.recycler_item_customer_mobile_txt)
        invoiceCount = itemView.findViewById(R.id.recycler_item_customer_receipt_txt)
        totalInvoiceValue = itemView.findViewById(R.id.recycler_item_customer_wallet_txt)
        barcodeImg = itemView.findViewById(R.id.recycler_item_customer_barcode_img)
        barcodeTxt = itemView.findViewById(R.id.recycler_item_customer_barcode_txt)
        selectionImg = itemView.findViewById(R.id.recycler_item_customer_selection_img)
    }

    fun bind(customer: Customer, onSelect: ((customer: Customer) -> Unit)? = null) {
        name?.text = customer.Name
        mobile?.text = customer.MobileNumber
        if(customer.Barcode?.isNotEmpty() == true){
            generateBarcode(customer.Barcode!!)
            barcodeTxt?.text = customer.Barcode
            barcodeImg?.visibility = View.VISIBLE
            barcodeTxt?.visibility = View.VISIBLE
        }else{
            barcodeImg?.visibility = View.GONE
            barcodeTxt?.visibility = View.GONE
        }
        CustomerHandler.shared().viewModel?.getInvoiceCount(customer.Id){
            invoiceCount?.text = "$it Invoices"
        }
        CustomerHandler.shared().viewModel?.getTotalInvoiceValue(customer.Id){
            totalInvoiceValue?.text = "$it Invoice Amount"
        }
        if(onSelect != null){
            selectionImg?.visibility = View.VISIBLE
            validateSelection(customer)
            CustomerHandler.shared().repository.customer.observe(BusinessHandler.shared().activity){
                validateSelection(customer)
            }
        }else{
            selectionImg?.visibility = View.GONE
        }
    }

    fun validateSelection(customer: Customer){
        val selectedCustomer = CustomerHandler.shared().repository.customer.value?.Id
        if(selectedCustomer != null && selectedCustomer == customer.Id){
            selectionImg?.setImageResource(R.drawable.ic_selected)
        }else{
            selectionImg?.setImageResource(R.drawable.ic_un_selected)
        }
    }

    fun generateBarcode(code:String){
        val multiFormatWriter = MultiFormatWriter()
        var width = Resources.getSystem().getDisplayMetrics().widthPixels
        var height = Resources.getSystem().getDisplayMetrics().heightPixels/4
        Log.d("Dimension","Widht : $width height $height")
        try {
            val bitMatrix = multiFormatWriter.encode(
                code,
                BarcodeFormat.CODE_128,
                width,
                height
            )
            val bitmap =
                Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565)
            for (i in 0 until width) {
                for (j in 0 until height) {
                    bitmap.setPixel(i, j, if (bitMatrix[i, j]) Color.BLACK else Color.WHITE)
                }
            }
            barcodeImg!!.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}