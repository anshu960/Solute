package com.solute.ui.business.invoice.details

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.sale.model.Sale
import com.friendly.framework.pdf.PDFService
import com.friendly.framework.qr.QRCodeUtill
import com.google.gson.Gson
import com.solute.app.App
import com.solute.R
import com.solute.ui.business.barcode.BarcodeHelper
import com.solute.utility.SMSManager
import com.solute.utility.WhatsappManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class InvoiceDetailsFragment : Fragment() {
    private val PRINT_SERVICE: Int = 0
    var business = BusinessHandler.shared().repository.business.value
    val gson = Gson()
    var receiptData = JSONObject()
    var customerInvoice: CustomerInvoice? = null
    var sales: ArrayList<Sale> = arrayListOf()

    var messageButton: ImageButton? = null
    var shareButton: ImageButton? = null
    var whatsappButton: ImageButton? = null
    var printButton : ImageButton? = null
    var messageTitle: TextView? = null
    var shareTitle: TextView? = null
    var whatsappTitle: TextView? = null
    var customer: Customer? = null
    var pdfView : WebView? = null
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    var salesRecycler : RecyclerView? = null
    var invoiceDateCardView : CardView? = null
    var businessCardView : CardView? = null
    var customerCardView : CardView? = null
    var itemDetailsCardView : CardView? = null

    var invoicenumberTv : TextView? = null
    var invoiceDateTv : TextView? = null
    var businessNameTv : TextView? = null
    var businessMobileTv : TextView? = null
    var businessAddressTv : TextView? = null

    var customerNameTv : TextView? = null
    var customerMobileTv : TextView? = null
    var customerAddressTv : TextView? = null

    var subTotalTv : TextView? = null
    var taxTv : TextView? = null
    var discountTv : TextView? = null
    var totalTv : TextView? = null
    var barcodeIv : ImageView? = null
    var qrcodeIv : ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_invoice_details, container, false)
        salesRecycler = view.findViewById(R.id.items_recycler)
        customerCardView = view.findViewById(R.id.customer_cv)
        invoiceDateTv = view.findViewById(R.id.invoice_date_tv)
        businessNameTv = view.findViewById(R.id.business_name_tv)
        businessMobileTv = view.findViewById(R.id.business_mobile_tv)
        businessAddressTv = view.findViewById(R.id.business_address_tv)
        customerNameTv = view.findViewById(R.id.customer_name_tv)
        customerMobileTv = view.findViewById(R.id.customer_mobile_tv)
        customerAddressTv = view.findViewById(R.id.customer_address_tv)

        invoicenumberTv = view.findViewById(R.id.invoice_number_tv)
        subTotalTv = view.findViewById(R.id.subtotal_tv)
        discountTv = view.findViewById(R.id.discount_tv)
        taxTv = view.findViewById(R.id.tax_tv)
        totalTv = view.findViewById(R.id.total_tv)
        barcodeIv = view.findViewById(R.id.barcode_iv)
        qrcodeIv = view.findViewById(R.id.qr_iv)


        pdfView = view.findViewById(R.id.receipt_details_pdf_img)
        messageButton = view.findViewById(R.id.receipt_details_receipt_message_btn)
        shareButton = view.findViewById(R.id.receipt_details_receipt_share_btn)
        whatsappButton = view.findViewById(R.id.receipt_details_receipt_whatsapp_btn)
        messageTitle = view.findViewById(R.id.receipt_details_receipt_message_title)
        whatsappTitle = view.findViewById(R.id.receipt_details_receipt_whatsapp_title)
        shareTitle = view.findViewById(R.id.receipt_details_receipt_share_title)
        printButton = view.findViewById(R.id.receipt_details_receipt_print_btn)

        messageButton?.setOnClickListener {
            if (customerInvoice != null && customerInvoice!!.invoiceNumber != null) {
                SMSManager().sendInvoiceReceipt(
                    App.shared().mainActivity,
                    getCustomerMobileNumber(),
                    customerInvoice!!.invoiceNumber!!,
                    customerInvoice!!.finalPrice!!,
                    customerInvoice!!
                )
            } else {
                App.shared().mainActivity?.toast("Invoice Data Not Found")
            }
        }
        shareButton?.setOnClickListener {
            if ( customerInvoice != null) {
                SMSManager().shareInvoice(
                    App.shared().mainActivity,
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
            if (customerInvoice != null) {
                WhatsappManager().sendInvoice(
                    App.shared().mainActivity,
                    customer?.MobileNumber,
                    customerInvoice!!.invoiceNumber!!,
                    customerInvoice!!.finalPrice!!,
                    customerInvoice!!
                )
            }
        }
        InvoiceHandler.shared().repository.allSalesLiveData.observe(App.shared().mainActivity!!) {
            sales = it as ArrayList<Sale>
            scope.launch { reloadData() }
        }
        InvoiceHandler.shared().repository.customerInvoice.observe(App.shared().mainActivity!!) {
            this.customerInvoice = it
            scope.launch { reloadData() }
        }
        CustomerHandler.shared().repository.customerLiveData.observe(App.shared().mainActivity!!) {
            this.customer = it
            scope.launch { reloadData() }
        }
        if(InvoiceHandler.shared().repository.customerInvoice.value != null){
            this.customerInvoice = InvoiceHandler.shared().repository.customerInvoice.value
            scope.launch { reloadData() }
        } else if (InvoiceHandler.shared().invoiceNumber != 0L){
            scope.launch { App.shared().mainActivity?.startActivityIndicator() }
            val invoiceId = InvoiceHandler.shared().invoiceNumber
            InvoiceHandler.shared().onRetriveSingleInvoiceCallBack={invoice,cust,business->
                App.shared().mainActivity?.runOnUiThread {
                    this.customerInvoice = invoice
                    this.sales = invoice.sales
                    this.customer = cust
                    this.business = business
                    scope.launch {
                        App.shared().mainActivity?.stopActivityIndicator()
                        reloadData()
                    }
                }
            }
            InvoiceHandler.shared().retrieveSingleInvoice(invoiceId)
        }

        return view
    }

    fun getCustomerMobileNumber(): String {
        if (this.customer != null && this.customer!!.MobileNumber != null) {
            return this.customer!!.MobileNumber!!
        } else {
            return ""
        }

    }

    fun loadShareDetails() {
//        if (customer != null && customer!!.MobileNumber != null) {
//            messageButton?.visibility = View.VISIBLE
//            whatsappButton?.visibility = View.VISIBLE
//            messageTitle?.visibility = View.VISIBLE
//            whatsappTitle?.visibility = View.VISIBLE
//            customerDetailsCard?.visibility = View.VISIBLE
//            customerDetailsName?.text = this.customer!!.Name
//            customerDetailsMobile?.text = this.customer!!.MobileNumber
//        } else {
//            messageButton?.visibility = View.GONE
//            whatsappButton?.visibility = View.GONE
//            messageTitle?.visibility = View.GONE
//            whatsappTitle?.visibility = View.GONE
//            customerDetailsCard?.visibility = View.GONE
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CustomerHandler.shared().repository.customerLiveData.postValue(null)
    }

    fun reloadData() {
        pupulateFooter()
        if (customerInvoice != null && customerInvoice?.ShareLink != null) {
            if(this.context != null){
                val adapter = InvoiceDetailsAdapter(requireContext(), customerInvoice!!.sales)
                this.salesRecycler?.layoutManager = LinearLayoutManager(requireContext())
                this.salesRecycler?.adapter = adapter
            }
            invoicenumberTv?.text = "INV #${customerInvoice?.invoiceID}"
            val qrBitmap =
                QRCodeUtill().getQRImage(customerInvoice!!.ShareLink!!)
            qrcodeIv?.setImageBitmap(qrBitmap)
            BarcodeHelper().generateBarcode(customerInvoice!!.invoiceID.toString(),this.barcodeIv)
            invoiceDateTv?.text = customerInvoice?.invoiceDate
            businessNameTv?.text = business?.Name
            businessMobileTv?.text = business?.MobileNumber
            businessAddressTv?.text = business?.Address
        }
        loadShareDetails()
        if(customerInvoice != null && customerInvoice!!.customerID != null && customer == null){
            CustomerHandler.shared().viewModel?.getCustomerById(customerInvoice!!.customerID!!){cust->
                scope.launch {
                    customer = cust
                    populateCustomerInfo()
                    if(cust != null){
                        customerCardView?.visibility = View.VISIBLE
                    }
                    customer = cust
                    val pdf = PDFService().createInvoice(customer,business,customerInvoice!!)
                    pdfView!!.settings.loadWithOverviewMode = true;
                    pdfView!!.settings.useWideViewPort = true;
                    pdfView?.loadDataWithBaseURL(null, pdf, "text/html", "utf-8", null)
                }
            }
        }else if(customerInvoice != null){
            if(customer != null){
                populateCustomerInfo()
                customerCardView?.visibility = View.VISIBLE
            }else{
                customerCardView?.visibility = View.GONE
            }
//            val pdf = PDFService().createInvoice(customer,business,customerInvoice!!)
//            pdfView!!.settings.loadWithOverviewMode = true;
//            pdfView!!.settings.useWideViewPort = true;
//            pdfView?.loadDataWithBaseURL(null, pdf, "text/html", "utf-8", null)
        }
    }

    fun populateCustomerInfo(){
        customerNameTv?.text = customer?.Name
        customerMobileTv?.text = customer?.MobileNumber
        customerAddressTv?.text = customer?.Address
        if(customer?.Address.isNullOrEmpty()){
            customerAddressTv?.visibility = View.GONE
        }
    }

    fun pupulateFooter() {
        var subTotal = 0F
        var finalPrice = 0F
        sales.forEach {
            subTotal += it.FinalPrice!!
            finalPrice += it.FinalPrice!!
        }
        if (customerInvoice != null && customerInvoice!!.instantDiscount != null) {
            finalPrice -= customerInvoice!!.instantDiscount!!
        }
        discountTv?.text = "₹ " + customerInvoice?.instantDiscount.toString()
        this.subTotalTv?.text = "₹ " + customerInvoice?.totalPrice.toString()
        this.taxTv?.text = "₹ " + customerInvoice?.Tax.toString()
        this.totalTv?.text = "₹ " + customerInvoice?.finalPrice.toString()
    }
    fun createWebPagePrint(webView: WebView) {
        /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        val printManager = App.shared().mainActivity?.getSystemService(Context.PRINT_SERVICE) as PrintManager
        val printAdapter = webView.createPrintDocumentAdapter()
        val jobName: String = "getString(R.string.webapp_name).toString()" + " Document"
        val builder = PrintAttributes.Builder()
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A5)
        val printJob: PrintJob = printManager!!.print(jobName, printAdapter, builder.build())
        if (printJob.isCompleted()) {
            Toast.makeText(
                App.shared(),
                "Printed Successfully",
                Toast.LENGTH_LONG
            ).show()
        } else if (printJob.isFailed()) {
            Toast.makeText(
                App.shared(),
                "Couldn't prnit, please try after some time",
                Toast.LENGTH_LONG
            ).show()
        }
        // Save the job object for later status checking
    }

    fun generatePdfInvoice(){

    }
}