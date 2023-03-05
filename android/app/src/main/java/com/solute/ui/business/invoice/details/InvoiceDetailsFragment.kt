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
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.cart.model.Sale
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.pdf.PDFService
import com.friendly.framework.qr.QRCodeUtill
import com.google.gson.Gson
import com.solute.app.App
import com.solute.R
import com.solute.utility.SMSManager
import com.solute.utility.WhatsappManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InvoiceDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvoiceDetailsFragment : Fragment() {
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
    var pdfView : WebView? = null
    val scope = CoroutineScope(Job() + Dispatchers.Main)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_invoice_details, container, false)
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
        if (customerInvoice != null) {
            val qrBitmap =
                QRCodeUtill().getQRImage("https://solute.app/#/receipt?id=${customerInvoice!!.invoiceID}")
            qrImage?.setImageBitmap(qrBitmap)
        }
        loadShareDetails()
        if(customerInvoice != null && customerInvoice!!.customerID != null && customer == null){
            CustomerHandler.shared().viewModel?.getCustomerById(customerInvoice!!.customerID!!){cust->
                scope.launch {
                    customer = cust
                    val pdf = PDFService().createInvoice(customer,business,customerInvoice!!)
                    pdfView!!.getSettings().setLoadWithOverviewMode(true);
                    pdfView!!.getSettings().setUseWideViewPort(true);
                    pdfView?.loadDataWithBaseURL(null, pdf, "text/html", "utf-8", null)
                }
            }
        }else if(customerInvoice != null){
            val pdf = PDFService().createInvoice(customer,business,customerInvoice!!)
            pdfView!!.getSettings().setLoadWithOverviewMode(true);
            pdfView!!.getSettings().setUseWideViewPort(true);
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