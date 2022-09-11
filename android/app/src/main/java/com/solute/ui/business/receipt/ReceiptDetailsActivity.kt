package com.solute.ui.business.receipt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.solute.R
import com.solute.ui.business.product.BusinessProductAdapter
import com.solute.utility.SMSManager
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.CustomerInvoice
import com.utilitykit.feature.cart.model.Sale
import org.json.JSONObject

class ReceiptDetailsActivity : UtilityActivity() {
    val business = BusinessHandler.shared().repository.business
    val gson = Gson()
    var receiptData = JSONObject()
    var customerInvoice : CustomerInvoice? = null
    var sales : ArrayList<Sale> = arrayListOf()
    var adapter : ReceiptAdapter? = null
    var recycler : RecyclerView? = null
    var qrImage : ImageView? = null
    var businessName : TextView? = null
    var businessAddress : TextView? = null
    var businessMobile : TextView? = null
    var receiptNumber : TextView? = null
    var receiptDate : TextView? = null
    var subTotal : TextView? = null
    var discount : TextView? = null
    var total : TextView? = null
    var messageButton : ImageButton? = null
    var shareButton : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_details)
        recycler = findViewById(R.id.receipt_details_receipt_recycler)
        qrImage = findViewById(R.id.receipt_details_qr_img)
        businessName = findViewById(R.id.receipt_details_reeceipt_business_name_txt)
        businessAddress = findViewById(R.id.receipt_details_reeceipt_business_address_txt)
        businessMobile = findViewById(R.id.receipt_details_reeceipt_business_mobile_txt)
        receiptNumber = findViewById(R.id.receipt_details_reeceipt_number_txt)
        receiptDate = findViewById(R.id.receipt_details_reeceipt_business_date_txt)
        subTotal = findViewById(R.id.receipt_details_receipt_business_sub_total_txt)
        discount = findViewById(R.id.receipt_details_receipt_business_discount_txt)
        total = findViewById(R.id.receipt_details_receipt_business_total_txt)
        messageButton = findViewById(R.id.receipt_details_receipt_message_btn)
        shareButton = findViewById(R.id.receipt_details_receipt_share_btn)
        pupulateExistingReceipt()
        messageButton?.setOnClickListener {
            if(customerInvoice != null && customerInvoice!!.InvoiceNumber != null){
                    SMSManager().sendInvoiceReceipt(this,
                        getCustomerMobileNumber(),customerInvoice!!.InvoiceNumber!!,customerInvoice!!.FinalPrice!!)
            }else{
                toast("Invoice Data Not Found")
            }
        }
        shareButton?.setOnClickListener {
            SMSManager().shareInvoice(this,customerInvoice!!.InvoiceNumber!!,customerInvoice!!.FinalPrice!!)
        }
    }
    fun getCustomerMobileNumber():String{
        toast("Mobile Number Not found For Customer")
        return ""
    }

    fun pupulateExistingReceipt(){
        if(intent.hasExtra(Key.invoice)){
            receiptData = JSONObject(intent.getStringExtra(Key.invoice))
            val payload = receiptData.getJSONObject(Key.payload)
            customerInvoice = gson.fromJson(payload.toString(),CustomerInvoice::class.java)
            var salesData = receiptData.getJSONArray(Key.sales)
            for (i in 0 until salesData.length()) {
                val newSale = salesData.getJSONObject(i)
                val sale = gson.fromJson(newSale.toString(),Sale::class.java)
                sales.add(sale)
            }
            reloadData()
        }
    }
    fun reloadData(){
        this.recycler!!.layoutManager = LinearLayoutManager(this)
        adapter = this?.let { ReceiptAdapter(it ,sales) }
        this.recycler!!.adapter = this.adapter

        businessName?.text = business?.Name
        receiptNumber?.text = customerInvoice?.InvoiceNumber.toString()
        businessAddress?.text = business?.Address
        businessMobile?.text = business?.MobileNumber
        receiptDate?.text = customerInvoice?.InvoiceDate
        subTotal?.text =  "₹ " + customerInvoice?.TotalPrice.toString()
        discount?.text =  "₹ " + customerInvoice?.InstantDiscount.toString()
        total?.text =  "₹ " + customerInvoice?.FinalPrice.toString()
    }
}