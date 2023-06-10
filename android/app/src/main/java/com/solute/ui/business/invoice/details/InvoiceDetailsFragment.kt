package com.solute.ui.business.invoice.details

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.print.PrintHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.DateUtill
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.sale.model.Sale
import com.friendly.framework.qr.QRCodeUtill
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.solute.app.ToastService
import com.solute.pdf.pdfService.AppPermission
import com.solute.pdf.pdfService.AppPermission.Companion.requestPermission
import com.solute.pdf.pdfService.FileHandler
import com.solute.ui.address.AddressUtill
//import com.solute.pdf.pdfService.PdfService
import com.solute.ui.business.barcode.BarcodeHelper
import com.solute.utility.SMSManager
import com.solute.utility.WhatsappManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File


class InvoiceDetailsFragment : Fragment() {
    var business = BusinessHandler.shared().repository.business.value
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
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    var salesRecycler : RecyclerView? = null
    private var customerCardView : CardView? = null
    private var invoicenumberTv : TextView? = null
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

    var scrollView : ScrollView? = null
    var webView : WebView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_invoice_details, container, false)
        scrollView = view.findViewById(R.id.receipt_details_scroll_view)
        webView = view.findViewById(R.id.receipt_details_web_view)
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
            printinvoice()
        }
        whatsappButton?.setOnClickListener {
            if (customerInvoice != null) {
                WhatsappManager().sendInvoice(
                    App.shared().mainActivity,
                    customer,
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

    fun printinvoice(){
        val bitmap = Bitmap.createBitmap(
            scrollView!!.getChildAt(0).getWidth(),
            scrollView!!.getChildAt(0).getHeight(), Bitmap.Config.ARGB_8888
        )
        val c = Canvas(bitmap)
        scrollView!!.getChildAt(0).draw(c)
        val printHelper = PrintHelper(requireContext())
        printHelper.scaleMode = PrintHelper.SCALE_MODE_FIT
        printHelper.printBitmap("Print Bitmap", bitmap)
    }

    override fun onDestroy() {
        super.onDestroy()
        CustomerHandler.shared().repository.customerLiveData.postValue(null)
    }

    fun reloadData() {
        showNativeWay()
//        showPDFWay()
    }

    private fun showPDFWay(){
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            AppPermission.REQUEST_PERMISSION
        )
        this.webView?.visibility = View.VISIBLE
        this.scrollView?.visibility = View.GONE
        if (customerInvoice != null && customerInvoice?.ShareLink != null && this.context != null) {
            createPdf()
        }
    }

    fun showNativeWay(){
        this.webView?.visibility = View.GONE
        this.scrollView?.visibility = View.VISIBLE
        if (customerInvoice != null && customerInvoice?.ShareLink != null && this.context != null) {
//            createPdf()
            if(this.context != null){
                val adapter = InvoiceDetailsAdapter(requireContext(), customerInvoice!!.sales)
                this.salesRecycler?.layoutManager = LinearLayoutManager(requireContext())
                this.salesRecycler?.adapter = adapter
                invoicenumberTv?.text = "INV #${customerInvoice?.invoiceID}"
                discountTv?.text = "₹ " + customerInvoice?.instantDiscount.toString()
                this.subTotalTv?.text = "₹ " + customerInvoice?.totalPrice.toString()
                this.taxTv?.text = "₹ ${customerInvoice!!.finalPrice!! - customerInvoice!!.totalPrice!!}"
                this.totalTv?.text = "₹ " + customerInvoice?.finalPrice.toString()
                val qrBitmap =
                    QRCodeUtill().getQRImage(customerInvoice!!.ShareLink!!)
                qrcodeIv?.setImageBitmap(qrBitmap)
                BarcodeHelper().generateBarcode(customerInvoice!!.invoiceID.toString(),this.barcodeIv)
                invoiceDateTv?.text = DateUtill().formatDate(customerInvoice!!.invoiceDate!!)
                businessNameTv?.text = business?.Name
                businessMobileTv?.text = business?.MobileNumber
                businessAddressTv?.text = AddressUtill().formatToDisplay(business?.Address)
            }
        }
        if(customerInvoice != null && customerInvoice!!.customerID != null && customer == null){
            CustomerHandler.shared().viewModel?.getCustomerById(customerInvoice!!.customerID!!){cust->
                scope.launch {
                    customer = cust
                    populateCustomerInfo()
                    if(cust != null){
                        customerCardView?.visibility = View.VISIBLE
                    }
                    customer = cust
                }
            }
        }else if(customerInvoice != null){
            if(customer != null){
                populateCustomerInfo()
                customerCardView?.visibility = View.VISIBLE
            }else{
                customerCardView?.visibility = View.GONE
            }
        }
    }

    fun populateCustomerInfo(){
        customerNameTv?.text = customer?.Name
        customerMobileTv?.text = customer?.DialCode + customer?.MobileNumber
        customerAddressTv?.text = customer?.Address
        if(customer?.Address.isNullOrEmpty()){
            customerAddressTv?.visibility = View.GONE
        }
    }


    ///////////////////////////////////////////////
    ///////////////////////////////////////////////
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppPermission.REQUEST_PERMISSION) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                requestPermission(requireActivity() as MainActivity)
                ToastService.shared().toast("Permission should be allowed")
            }
        }
    }

    private fun createPdf() {
        val onError: (Exception) -> Unit = { ToastService.shared().toast(it.message.toString()) }
        val onFinish: (File) -> Unit = { openFile(it) }
//        val pdfService = PdfService(requireContext())
//        pdfService.generateInvoice(
//            invoice = this.customerInvoice!!,
//            onFinish = onFinish,
//            onError = onError
//        )
    }


    private fun openFile(file: File) {
        val path = FileHandler().getPathFromUri(requireContext(), file.toUri())

//        pdfIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        val uri: Uri = FileProvider.getUriForFile(this.requireContext(), "com.solute.provider", file)
         webView?.settings?.allowFileAccess = true
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.allowContentAccess = true
        webView?.settings?.allowUniversalAccessFromFileURLs = true
        webView?.settings?.allowFileAccessFromFileURLs = true

//        webView?.webChromeClient = WebChromeClient()
        val newPath = uri.toString().replace("content://","file:///")
        if (path != null) {
            webView?.loadUrl("file:///$path")
        }


//        val intent = Intent(Intent.ACTION_VIEW)
//        intent.setDataAndType(uri, "application/pdf")
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////        intent.setDataAndType(uri,"application/vnd.android.package-archive");
//        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
//        startActivity(intent)
//        try {
//            startActivity(pdfIntent)
//        } catch (e: ActivityNotFoundException) {
//            ToastService.shared().toast("Can't read pdf file")
//        }
    }
}