package com.solute.ui.business.receipt

import android.content.Context
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.view.View
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.gson.Gson
import com.solute.App
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
import com.utilitykit.pdf.PDFService
import com.utilitykit.qr.QRCodeUtill
import org.json.JSONObject


class ReceiptDetailsActivity : UtilityActivity() {
    private val PRINT_SERVICE: Int = 0
    var business = BusinessHandler.shared().repository.business.value
    val gson = Gson()
    var receiptData = JSONObject()
    var customerInvoice: CustomerInvoice? = null
    var sales: ArrayList<Sale> = arrayListOf()
    var qrImage: ImageView? = null

    var subTotal: TextView? = null
    var discount: TextView? = null
    var total: TextView? = null
    var messageButton: ImageButton? = null
    var shareButton: ImageButton? = null
    var whatsappButton: ImageButton? = null
    var printButton : ImageButton? = null
    var messageTitle: TextView? = null
    var shareTitle: TextView? = null
    var whatsappTitle: TextView? = null
    var customer: Customer? = null
    var customerDetailsCard: CardView? = null
    var customerDetailsName: TextView? = null
    var customerDetailsMobile: TextView? = null
    var backButton: ImageButton? = null
    var pdfView : WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_details)
        pdfView = findViewById(R.id.receipt_details_pdf_img)
        messageButton = findViewById(R.id.receipt_details_receipt_message_btn)
        shareButton = findViewById(R.id.receipt_details_receipt_share_btn)
        whatsappButton = findViewById(R.id.receipt_details_receipt_whatsapp_btn)
        messageTitle = findViewById(R.id.receipt_details_receipt_message_title)
        whatsappTitle = findViewById(R.id.receipt_details_receipt_whatsapp_title)
        shareTitle = findViewById(R.id.receipt_details_receipt_share_title)
        printButton = findViewById(R.id.receipt_details_receipt_print_btn)
        backButton?.setOnClickListener { onBackPressed() }
        messageButton?.setOnClickListener {
            if (customerInvoice != null && customerInvoice!!.invoiceNumber != null) {
                SMSManager().sendInvoiceReceipt(
                    this,
                    getCustomerMobileNumber(),
                    customerInvoice!!.invoiceNumber!!,
                    customerInvoice!!.finalPrice!!,
                    customerInvoice!!
                )
            } else {
                toast("Invoice Data Not Found")
            }
        }
        shareButton?.setOnClickListener {
            if (customer != null && customer!!.MobileNumber != null && customerInvoice != null) {
                SMSManager().shareInvoice(
                    this,
                    customerInvoice!!.invoiceNumber!!,
                    customerInvoice!!.finalPrice!!,
                    customerInvoice!!
                )
            }
        }
        printButton?.setOnClickListener {
            this.pdfView?.let {
                createWebPagePrint(it)
            }
        }
        whatsappButton?.setOnClickListener {
            if (customer != null && customer!!.MobileNumber != null && customerInvoice != null) {
                WhatsappManager().sendInvoice(
                    this,
                    customer!!.MobileNumber!!,
                    customerInvoice!!.invoiceNumber!!,
                    customerInvoice!!.finalPrice!!,
                    customerInvoice!!
                )
            }
        }
        InvoiceHandler.shared().repository.allSalesLiveData.observe(this) {
            sales = it as ArrayList<Sale>
            reloadData()
        }
        CustomerHandler.shared().repository.customerLiveData.observe(this) {
            this.customer = it
            loadShareDetails()
        }
        pupulateExistingReceipt()
        if (intent.hasExtra(Key.invoiceId)){
            val invoiceId = intent.getStringExtra(Key.invoiceId)!!.toLong()
            InvoiceHandler.shared().onRetriveSingleInvoiceCallBack={invoice,sales,customer,business->
                this.runOnUiThread {
                    this.customerInvoice = invoice
                    this.sales = sales
                    this.customer = customer
                    this.business = business
                    reloadData()
                }
            }
            InvoiceHandler.shared().retrieveSingleInvoice(invoiceId)
        }
    }

    fun getCustomerMobileNumber(): String {
        if (this.customer != null && this.customer!!.MobileNumber != null) {
            return this.customer!!.MobileNumber!!
        } else {
            return ""
        }

    }

    fun loadShareDetails() {
        if (customer != null && customer!!.MobileNumber != null) {
            messageButton?.visibility = View.VISIBLE
            whatsappButton?.visibility = View.VISIBLE
            messageTitle?.visibility = View.VISIBLE
            whatsappTitle?.visibility = View.VISIBLE
            customerDetailsCard?.visibility = View.VISIBLE
            customerDetailsName?.text = this.customer!!.Name
            customerDetailsMobile?.text = this.customer!!.MobileNumber
        } else {
            messageButton?.visibility = View.GONE
            whatsappButton?.visibility = View.GONE
            messageTitle?.visibility = View.GONE
            whatsappTitle?.visibility = View.GONE
            customerDetailsCard?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CustomerHandler.shared().repository.customerLiveData.postValue(null)
    }

    fun pupulateExistingReceipt() {
         if (intent.hasExtra(Key.invoice)) {
            receiptData = JSONObject(intent.getStringExtra(Key.invoice))
            val payload = receiptData.getJSONObject(Key.payload)
            customerInvoice = gson.fromJson(payload.toString(), CustomerInvoice::class.java)
            var salesData = receiptData.getJSONArray(Key.sales)
            for (i in 0 until salesData.length()) {
                val newSale = salesData.getJSONObject(i)
                val sale = gson.fromJson(newSale.toString(), Sale::class.java)
                sales.add(sale)
            }
            if(!customerInvoice?.customerID.isNullOrEmpty()){
                CustomerHandler.shared().viewModel?.getCustomerById(customerInvoice!!.customerID!!){
                    this.customer = it
                    reloadData()
                }
            }else{
                reloadData()
            }
        } else if(InvoiceHandler.shared().repository.customerInvoice.value !=null) {
            InvoiceHandler.shared().repository.customerInvoice.value
            customerInvoice = InvoiceHandler.shared().repository.customerInvoice.value
            InvoiceHandler.shared().viewModel?.fetchAllSales()
            if(!customerInvoice?.customerID.isNullOrEmpty()){
                CustomerHandler.shared().viewModel?.getCustomerById(customerInvoice!!.customerID!!){
                    this.customer = it
                    reloadData()
                }
            }else{
                reloadData()
            }
        }
    }

    fun reloadData() {
        pupulateFooter()
        if (customerInvoice != null) {
            val qrBitmap =
                QRCodeUtill().getQRImage("https://solute.app/#/receipt?id=${customerInvoice!!.invoiceNumber}")
            qrImage?.setImageBitmap(qrBitmap)
        }
        loadShareDetails()
        if(customerInvoice != null){
            val pdf = PDFService().createInvoice(this,customer,business,customerInvoice!!,sales)
            pdfView?.getSettings()?.setUseWideViewPort(true)
            pdfView?.setInitialScale(1)
            pdfView?.getSettings()?.setSupportZoom(true);
            pdfView?.getSettings()?.setBuiltInZoomControls(true);
            pdfView?.getSettings()?.setDisplayZoomControls(false);
            pdfView?.loadDataWithBaseURL(null, pdf, "text/html", "utf-8", null)
        }
    }

    fun pupulateFooter() {
        var subTotal = 0F
        var finalPrice = 0F
        sales.forEach {
            subTotal = subTotal + it.FinalPrice!!
            finalPrice = finalPrice + it.FinalPrice!!
        }
        if (customerInvoice != null && customerInvoice!!.instantDiscount != null) {
            finalPrice = finalPrice - customerInvoice!!.instantDiscount!!
        }
        this.subTotal?.text = "₹ " + subTotal.toString()
        this.total?.text = "₹ " + finalPrice.toString()
    }
    fun createWebPagePrint(webView: WebView) {
        /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        val printManager = this.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val printAdapter = webView.createPrintDocumentAdapter()
        val jobName: String = "getString(R.string.webapp_name).toString()" + " Document"
        val builder = PrintAttributes.Builder()
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A5)
        val printJob: PrintJob = printManager!!.print(jobName, printAdapter, builder.build())
        if (printJob.isCompleted()) {
            Toast.makeText(
                App.applicationContext(),
                "Printed Successfully",
                Toast.LENGTH_LONG
            ).show()
        } else if (printJob.isFailed()) {
            Toast.makeText(
                App.applicationContext(),
                "Couldn't prnit, please try after some time",
                Toast.LENGTH_LONG
            ).show()
        }
        // Save the job object for later status checking
    }

}