package com.solute.ui.business.receipt

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.solute.R
import com.solute.utility.SMSManager
import com.solute.utility.WhatsappManager
import com.utilitykit.Constants.Key
import com.utilitykit.UtilityActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.handler.CustomerHandler
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.invoice.handler.InvoiceHandler
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.qr.QRCodeUtill
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
    var whatsappButton : ImageButton? = null
    var messageTitle : TextView? = null
    var shareTitle : TextView? = null
    var whatsappTitle : TextView? = null
    var customer : Customer? = null
    var customerDetailsCard : CardView? = null
    var customerDetailsName : TextView? = null
    var customerDetailsMobile : TextView? = null
    var backButton : ImageButton? = null
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
        whatsappButton = findViewById(R.id.receipt_details_receipt_whatsapp_btn)
        messageTitle = findViewById(R.id.receipt_details_receipt_message_title)
        whatsappTitle = findViewById(R.id.receipt_details_receipt_whatsapp_title)
        shareTitle = findViewById(R.id.receipt_details_receipt_share_title)
        customerDetailsCard = findViewById(R.id.receipt_details_receipt_customer_card)
        customerDetailsName = findViewById(R.id.receipt_details_receipt_customer_name)
        customerDetailsMobile = findViewById(R.id.receipt_details_receipt_customer_mobile)
        backButton = findViewById(R.id.receipt_details_header_back)
        backButton?.setOnClickListener { onBackPressed() }
        messageButton?.setOnClickListener {
            if(customerInvoice != null && customerInvoice!!.InvoiceNumber != null){
                    SMSManager().sendInvoiceReceipt(this,
                        getCustomerMobileNumber(),customerInvoice!!.InvoiceNumber!!,customerInvoice!!.FinalPrice!!)
            }else{
                toast("Invoice Data Not Found")
            }
        }
        shareButton?.setOnClickListener {
            if(customer!=null && customer!!.MobileNumber != null && customerInvoice != null){
                SMSManager().shareInvoice(this,customerInvoice!!.InvoiceNumber!!,customerInvoice!!.FinalPrice!!)
            }

        }
        whatsappButton?.setOnClickListener {
            if(customer!=null && customer!!.MobileNumber != null && customerInvoice != null){
                WhatsappManager().sendInvoice(this,customer!!.MobileNumber!!,customerInvoice!!.InvoiceNumber!!,customerInvoice!!.FinalPrice!!)
            }
        }
        InvoiceHandler.shared().repository.allSalesLiveData.observe(this) {
            sales = it as ArrayList<Sale>
            reloadData()
        }
        CustomerHandler.shared().repository.customerLiveData.observe(this){
            this.customer = it
            loadShareDetails()
        }
        pupulateExistingReceipt()
    }
    fun getCustomerMobileNumber():String{
        if(this.customer != null && this.customer!!.MobileNumber != null){
            return this.customer!!.MobileNumber!!
        }else{
            return ""
        }

    }
    fun loadShareDetails(){
        if(CustomerHandler.shared().repository.customer != null && CustomerHandler.shared().repository!!.customer!!.value?.MobileNumber !=  null){
            this.customer =  CustomerHandler.shared().repository!!.customer!!.value
            messageButton?.visibility = View.VISIBLE
            whatsappButton?.visibility = View.VISIBLE
            messageTitle?.visibility = View.VISIBLE
            whatsappTitle?.visibility = View.VISIBLE
            customerDetailsCard?.visibility = View.VISIBLE
            customerDetailsName?.text = this.customer!!.Name
            customerDetailsMobile?.text = this.customer!!.MobileNumber
        }else{
            messageButton?.visibility = View.GONE
            whatsappButton?.visibility = View.GONE
            messageTitle?.visibility = View.GONE
            whatsappTitle?.visibility = View.GONE
            customerDetailsCard?.visibility = View.GONE
            if(customerInvoice != null && customerInvoice!!.CustomerID != null){
                CustomerHandler.shared().searchCustomerById(customerInvoice!!.CustomerID!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CustomerHandler.shared().repository.customerLiveData.postValue(null)
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
        }else{
            InvoiceHandler.shared().repository.customerInvoice.value
            customerInvoice = InvoiceHandler.shared().repository.customerInvoice.value
            InvoiceHandler.shared().viewModel?.fetchAllSales()
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

        discount?.text =  "₹ " + customerInvoice?.InstantDiscount.toString()
        pupulateFooter()
        val qrBitmap = QRCodeUtill().getQRImage("https://solute.app/#/receipt?id=$customerInvoice?.InvoiceNumber.toString()")
        qrImage?.setImageBitmap(qrBitmap)
        loadShareDetails()
    }
    fun pupulateFooter(){
        var subTotal = 0F
        var finalPrice = 0F
        sales.forEach {
            subTotal = subTotal + it.FinalPrice!!
            finalPrice = finalPrice + it.FinalPrice!!
        }
        if(customerInvoice != null && customerInvoice!!.InstantDiscount != null){
            finalPrice = finalPrice - customerInvoice!!.InstantDiscount!!
        }
        this.subTotal?.text =  "₹ " + subTotal.toString()
        this.total?.text =  "₹ " + finalPrice.toString()
    }
}