package com.friendly.framework.pdf

import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.invoice.InvoiceTemplate


class PDFService {

    fun createInvoice(
        customer: Customer?,
        business: Business?,
        invoice: CustomerInvoice,
    ) :String{
        var invoiceHtml =  InvoiceTemplate.htmlTemplate
        business?.Name?.let { invoiceHtml = invoiceHtml.replace("###BusinessName", it) }
//        business?.Address?.let { invoiceHtml =invoiceHtml.replace("###BusinessAddress",it ) }
        business?.MobileNumber?.let { invoiceHtml =invoiceHtml.replace("###BusinessMobile",it ) }
        customer?.Name?.let { invoiceHtml = invoiceHtml.replace("###CustomerName",it ) }
        customer?.Address?.let { invoiceHtml = invoiceHtml.replace("###CustomerAddress",it ) }
        customer?.MobileNumber?.let { invoiceHtml = invoiceHtml.replace("###CustomerMobile",it ) }

        invoice.createdAt?.let { invoiceHtml = invoiceHtml.replace("###InvoiceDate",it ) }
        invoice.invoiceNumber?.let { invoiceHtml = invoiceHtml.replace("###InvoiceNumber",it.toString() ) }
        var saleItemHtml = ""
        invoice.sales.forEach {sale->
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
        invoiceHtml = invoiceHtml.replace("###SubTotal",invoice.totalPrice.toString())
        invoiceHtml = invoiceHtml.replace("###Tax",invoice.Tax.toString())
        invoiceHtml = invoiceHtml.replace("###FinalPrice",invoice.finalPrice.toString())
        invoiceHtml = invoiceHtml.replace("###InstantDiscount",invoice.instantDiscount.toString())
        return invoiceHtml
    }



}