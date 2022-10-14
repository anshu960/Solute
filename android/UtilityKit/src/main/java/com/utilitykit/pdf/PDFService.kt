package com.utilitykit.pdf

import android.content.Context
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.invoice.InvoiceTemplate


class PDFService {

    fun createInvoice(context: Context?,customer:Customer?,business:Business?,invoice: CustomerInvoice,sales:ArrayList<Sale>) :String{
        var invoiceHtml =  InvoiceTemplate.htmlTemplate
        business?.Name?.let { invoiceHtml = invoiceHtml.replace("###BusinessName", it) }
        business?.Address?.let { invoiceHtml =invoiceHtml.replace("###BusinessAddress",it ) }
        business?.MobileNumber?.let { invoiceHtml =invoiceHtml.replace("###BusinessMobile",it ) }
        customer?.Name?.let { invoiceHtml = invoiceHtml.replace("###CustomerName",it ) }
        customer?.Address?.let { invoiceHtml = invoiceHtml.replace("###CustomerAddress",it ) }
        customer?.MobileNumber?.let { invoiceHtml = invoiceHtml.replace("###CustomerMobile",it ) }

        invoice.CreatedAt?.let { invoiceHtml = invoiceHtml.replace("###InvoiceDate",it ) }
        invoice.InvoiceNumber?.let { invoiceHtml = invoiceHtml.replace("###InvoiceNumber",it.toString() ) }
        var saleItemHtml = ""
        sales.forEach {sale->
            val newRow = """
                    <tr>
                    <td class="text-center">${sale.ProductName}</td>
                        <td class="text-center">₹${sale.Price}</td>
                        <td class="text-center">${sale.Tax}</td>
                        <td class="text-center">${sale.Quantity}</td>
                        <td class="text-right">₹${sale.FinalPrice}</td>
                     </tr>
            """.trimIndent()
            saleItemHtml = saleItemHtml + newRow
        }
        invoiceHtml = invoiceHtml.replace("###SalesData",saleItemHtml)
        invoiceHtml = invoiceHtml.replace("###SubTotal",invoice.TotalPrice.toString())
        invoiceHtml = invoiceHtml.replace("###Tax",invoice.Tax.toString())
        invoiceHtml = invoiceHtml.replace("###FinalPrice",invoice.FinalPrice.toString())
        invoiceHtml = invoiceHtml.replace("###InstantDiscount",invoice.InstantDiscount.toString())
        return invoiceHtml
    }



}