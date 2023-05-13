package com.solute.pdf.pdfService

import android.content.Context
import android.os.Environment
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.solute.R
import com.solute.pdf.util.asString
import java.io.File
import java.io.FileOutputStream


class PdfService(private val context: Context) {
    private val tableHeadingColor = BaseColor(97, 112, 121)
    private val tableCellColor = BaseColor(243, 246, 250)
    private val footerCellColor = BaseColor(243, 246, 250)

    private val colorGrey = BaseColor(80, 91, 100)
    private val colorGreyFiord = BaseColor(80, 92, 100)
    private val colorGreyFooter = BaseColor(99, 111, 118)

    private val fontCompanyName = Font(Font.FontFamily.TIMES_ROMAN, 16f, Font.BOLD, colorGrey)
    private val fontInvoice = Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD, colorGreyFiord)
    private val fontInvoiceContentTitle =
        Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, colorGreyFiord)
    private val fontInvoiceContent =
        Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, BaseColor.BLACK)
    private val fontBillingTitle =
        Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, BaseColor.WHITE)
    private val fontTableTitle =
        Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, BaseColor.WHITE)
    private val fontContent = Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, BaseColor.BLACK)
    private val fontThankYou = Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.NORMAL, colorGreyFooter)

    private lateinit var pdf: PdfWriter

    private fun createFile(): File {
        //Prepare file
        val title = "Pdf to export.pdf"
        val path = Environment.getExternalStorageDirectory()
        val mediaStorageDir = File(
            context.filesDir,
            "external_files"
        )
        val storageDir = File("$mediaStorageDir/invoices")
        if (!storageDir.exists())
        {
            storageDir.mkdirs();
        }
        val invoiceFile = File(storageDir, "invoice.pdf")
        if(!invoiceFile.exists()){
            invoiceFile.createNewFile()
        }

        return invoiceFile
    }

    private fun createDocument(): Document {
        //Create Document
        val document = Document()
        document.setMargins(24f, 24f, 32f, 32f)
        document.pageSize = PageSize.A4
        return document
    }

    private fun setupPdfWriter(document: Document, file: File) {
        pdf = PdfWriter.getInstance(document, FileOutputStream(file))
        pdf.setFullCompression()
        //Open the document
        document.open()
    }

    private fun createTable(column: Int, columnWidth: FloatArray): PdfPTable {
        val table = PdfPTable(column)
        table.widthPercentage = 100f
        table.setWidths(columnWidth)
        table.defaultCell.verticalAlignment = Element.ALIGN_CENTER
        table.defaultCell.horizontalAlignment = Element.ALIGN_CENTER
        return table
    }

    private fun createImageCell(image: Image): PdfPCell {
        val cell = PdfPCell(image)
        cell.horizontalAlignment = Element.ALIGN_LEFT
        cell.verticalAlignment = Element.ALIGN_MIDDLE
        //setup padding
        cell.setPadding(0f)
        cell.isUseAscender = true
        cell.paddingLeft = 0f
        cell.paddingRight = 0f
        cell.paddingTop = 0f
        cell.paddingBottom = 0f
        cell.border = Rectangle.NO_BORDER
        return cell
    }

    private fun createCell(content: String, font: Font): PdfPCell {
        val cell = PdfPCell(Phrase(content, font))
        cell.horizontalAlignment = Element.ALIGN_LEFT
        cell.verticalAlignment = Element.ALIGN_MIDDLE
        //setup padding
        cell.setPadding(8f)
        cell.isUseAscender = true
        cell.paddingLeft = 4f
        cell.paddingRight = 4f
        cell.paddingTop = 8f
        cell.paddingBottom = 8f
        cell.border = Rectangle.NO_BORDER
        return cell
    }

    private fun addLineSpace(document: Document, number: Int) {
        for (i in 0 until number) {
            document.add(Paragraph(" "))
        }
    }

    fun generateInvoice(
        invoice: CustomerInvoice,
        onFinish: (file: File) -> Unit,
        onError: (Exception) -> Unit
    ) {
        //Define the document
        val file = createFile()
        val document = createDocument()

        //Setup PDF Writer
        setupPdfWriter(document, file)

        addLineSpace(document, 1)

        // Create header table
        val headerTable = createHeaderTable(invoice)

        document.add(headerTable)
        addLineSpace(document, 1)

        // Create BillTo table
        val billToTable = createBillToTable(invoice)

        document.add(billToTable)
        addLineSpace(document, 1)

        //Define item Table
        val itemTable = createItemTable(invoice)

        document.add(itemTable)
        addLineSpace(document, 1)

        // Create total table
        val specialNotesToTable = createNotesAndTotalTable(invoice)

        document.add(specialNotesToTable)

        document.add(
            Paragraph(
                R.string.str_invoice_disclaimer.asString(context),
                fontInvoiceContentTitle
            )
        )
        addLineSpace(document, 1)
        val footerThankYou =
            Paragraph(R.string.str_invoice_thankyou.asString(context), fontThankYou)
        footerThankYou.alignment = Element.ALIGN_CENTER
        document.add(footerThankYou)
        val footerInquiry = Paragraph(
            R.string.str_invoice_inquiry.asString(context),
            fontInvoiceContentTitle
        )
        footerInquiry.alignment = Element.ALIGN_CENTER
        document.add(footerInquiry)
        addLineSpace(document, 1)

        // Create footer table
        val footerTable = createFooterTable()

        document.add(footerTable)
        document.close()
        try {
            pdf.close()
        } catch (ex: Exception) {
            onError(ex)
        } finally {
            onFinish(file)
        }
    }

    private fun createFooterTable(): PdfPTable {
        val footerToWidth = 1f
        val footerColumnWidth = floatArrayOf(
            footerToWidth
        )

        val footerTable = createTable(1, footerColumnWidth)
        val footerAddressCell =
            createCell(R.string.str_invoice_footer_addr.asString(context), fontContent)
        footerAddressCell.backgroundColor = footerCellColor
        footerAddressCell.horizontalAlignment = Element.ALIGN_CENTER
        footerTable.addCell(footerAddressCell)

        val footerContactCell =
            createCell(R.string.str_invoice_footer_tel.asString(context), fontContent)
        footerContactCell.backgroundColor = footerCellColor
        footerContactCell.horizontalAlignment = Element.ALIGN_CENTER
        footerTable.addCell(footerContactCell)
        return footerTable
    }

    private fun createNotesAndTotalTable(invoice: CustomerInvoice): PdfPTable {
        val specialNotesWidth = 1f
        val subtotalDescWidth = 1f
        val subtotalWidth = 1f
        val barCodeWidth = 1f
        val specialNotesColumnWidth = floatArrayOf(
            specialNotesWidth,
            subtotalDescWidth,
            subtotalWidth,
            barCodeWidth,
        )


        val specialNotesToTable = createTable(4, specialNotesColumnWidth)

        val specialNotesTitleCell =
            createCell(
                R.string.str_invoice_special_note.asString(context),
                fontTableTitle
            )
        specialNotesTitleCell.colspan = 2
        specialNotesTitleCell.backgroundColor = tableHeadingColor;
        specialNotesToTable.addCell(specialNotesTitleCell)

        val subTotalTitleCell =
            createCell(
                R.string.str_invoice_subtotal.asString(context),
                fontInvoiceContentTitle
            )
        specialNotesToTable.addCell(subTotalTitleCell)
        val subTotalCell =
            createCell(
                "${R.string.str_invoice_currency_symbol.asString(context)}${invoice.totalPrice}",
                fontContent
            )

        specialNotesToTable.addCell(subTotalCell)

        val specialNotesCell = createCell(invoice.invoiceType.toString(), fontInvoiceContentTitle)
        specialNotesCell.rowspan = 2
        specialNotesCell.colspan = 2
        specialNotesToTable.addCell(specialNotesCell)

        val subDiscountTitleCell =
            createCell(
                R.string.str_invoice_discount.asString(context),
                fontInvoiceContentTitle
            )
        specialNotesToTable.addCell(subDiscountTitleCell)
        val discountCell =
            createCell(
                "${R.string.str_invoice_currency_symbol.asString(context)}${invoice.instantDiscount}",
                fontContent
            )
        specialNotesToTable.addCell(discountCell)

        val taxRateTitleCell = createCell(
            R.string.str_invoice_taxrate.asString(context),
            fontInvoiceContentTitle
        )
        specialNotesToTable.addCell(taxRateTitleCell)
        val taxRateCell =
            createCell(
                "${R.string.str_invoice_currency_symbol.asString(context)}${invoice.totalPrice}",
                fontContent
            )
        specialNotesToTable.addCell(taxRateCell)

//        val qrCell =
//            createImageCell("Test".asBarCode(BarcodeFormat.QR_CODE, 50, 50))
//        qrCell.rowspan = 2
//        specialNotesToTable.addCell(qrCell)

//        val barCodeCell =
//            createImageCell("Test".asBarCode(BarcodeFormat.CODE_128, 50, 30))
//        barCodeCell.rowspan = 2
//        specialNotesToTable.addCell(barCodeCell)


        val taxTitleCell = createCell(
            R.string.str_invoice_tax.asString(context),
            fontInvoiceContentTitle
        )
        specialNotesToTable.addCell(taxTitleCell)
        val taxCell =
            createCell(
                "${R.string.str_invoice_currency_symbol.asString(context)}${invoice.costPrice}",
                fontContent
            )
        specialNotesToTable.addCell(taxCell)

        val totalTitleCell = createCell(
            R.string.str_invoice_total.asString(context),
            fontInvoiceContentTitle
        )
        specialNotesToTable.addCell(totalTitleCell)
        val totalCell =
            createCell(
                "${R.string.str_invoice_currency_symbol.asString(context)}${invoice.finalPrice}",
                fontContent
            )
        specialNotesToTable.addCell(totalCell)
        return specialNotesToTable
    }

    private fun createItemTable(invoice: CustomerInvoice): PdfPTable {
        val itemNameWidth = 1f
        val unitCostWidth = .35f
        val qtyWidth = .35f
        val amountWidth = .35f
        val columnWidth = floatArrayOf(itemNameWidth, unitCostWidth, qtyWidth, amountWidth)
        val itemTable = createTable(4, columnWidth)
        //Table header (first row)
        val tableHeaderContent = listOf(
            R.string.str_invoice_item_description,
            R.string.str_invoice_item_unit_cost,
            R.string.str_invoice_item_qty,
            R.string.str_invoice_item_amount
        )

        //write table header into table
        tableHeaderContent.forEach {
            //define a cell
            val cell = createCell(it.asString(context), fontTableTitle)
            cell.backgroundColor = tableHeadingColor;
            //add our cell into our table
            itemTable.addCell(cell)
        }
        //write user data into table
        invoice.sales.forEach {
            val itemCell = createCell(it.ProductName!!, fontContent)
            itemCell.backgroundColor = tableCellColor
            itemTable.addCell(itemCell)

            val unitCostCell =
                createCell(
                    "${R.string.str_invoice_currency_symbol.asString(context)}${it.Price}",
                    fontContent
                )
            unitCostCell.backgroundColor = tableCellColor
            itemTable.addCell(unitCostCell)

            val quantityCell = createCell(it.Quantity.toString(), fontContent)
            quantityCell.backgroundColor = tableCellColor
            itemTable.addCell(quantityCell)

            val amountCell =
                createCell(
                    "${R.string.str_invoice_currency_symbol.asString(context)}${it.FinalPrice}",
                    fontContent
                )
            amountCell.backgroundColor = tableCellColor
            itemTable.addCell(amountCell)
        }
        return itemTable
    }

    private fun createBillToTable(invoice: CustomerInvoice): PdfPTable {
        val billToWidth = 1f
        val shipToWidth = 1f
        val spacingBtw = 1f
        val billToColumnWidth = floatArrayOf(
            billToWidth,
            shipToWidth,
            spacingBtw,
        )

        val billToTable = createTable(3, billToColumnWidth)
        val emptyCell = createCell("", fontContent)
        val billToTitleCell =
            createCell(R.string.str_invoice_bill_to.asString(context), fontBillingTitle)
        billToTitleCell.setBackgroundColor(tableHeadingColor)
        billToTable.addCell(billToTitleCell)

        billToTable.addCell(emptyCell)

        val shipToTitleCell =
            createCell(R.string.str_invoice_ship_to.asString(context), fontBillingTitle)
        shipToTitleCell.setBackgroundColor(tableHeadingColor)
        billToTable.addCell(shipToTitleCell)

        val billToContentCell = createCell(invoice.invoiceType!!, fontContent)
        billToTable.addCell(billToContentCell)

        billToTable.addCell(emptyCell)

        val shipToContentCell = createCell(invoice.invoiceType!!, fontContent)
        billToTable.addCell(shipToContentCell)
        return billToTable
    }

    private fun createHeaderTable(invoice: CustomerInvoice): PdfPTable {
        val companyNameWidth = 1f
        val companyAddressWidth = 1f
        val invoiceDescWidth = 1f
        val invoiceContentWidth = 1f

        val headerColumnWidth = floatArrayOf(
            companyNameWidth,
            companyAddressWidth,
            invoiceDescWidth,
            invoiceContentWidth
        )
        val headerTable = createTable(4, headerColumnWidth)

        // First row
        val companyNameCell = createCell(invoice.businessID!!, fontCompanyName)
        companyNameCell.colspan = 3
        headerTable.addCell(companyNameCell)

        val invoiceCell =
            createCell(R.string.str_invoice.asString(context), fontInvoice)
        headerTable.addCell(invoiceCell)

        // Second row
        val addressCell = createCell(invoice.businessID!!, fontContent)
        addressCell.rowspan = 5
        headerTable.addCell(addressCell)

        val contactCell = createCell(invoice.businessID!!, fontContent)
        contactCell.rowspan = 5
        headerTable.addCell(contactCell)

        val invoiceTitleDateCell = createCell(
            R.string.str_invoice_date.asString(context), fontInvoiceContentTitle
        )
        headerTable.addCell(invoiceTitleDateCell)
        val invoiceDateCell = createCell(invoice.createdAt!!, fontInvoiceContent)
        headerTable.addCell(invoiceDateCell)

        val invoiceTitleInvoiceNo = createCell(
            R.string.str_invoice_no.asString(context),
            fontInvoiceContentTitle
        )
        headerTable.addCell(invoiceTitleInvoiceNo)
        val invoiceInvoiceNo = createCell(invoice.invoiceID!!.toString(), fontInvoiceContent)
        headerTable.addCell(invoiceInvoiceNo)

        val invoiceTitleCustNo =
            createCell(
                R.string.str_invoice_customer_no.asString(context),
                fontInvoiceContentTitle
            )
        headerTable.addCell(invoiceTitleCustNo)
        val invoiceCustNo = createCell(invoice.invoiceType!!, fontInvoiceContent)
        headerTable.addCell(invoiceCustNo)

        val invoiceTitlePurchaseId =
            createCell(
                R.string.str_invoice_order_id.asString(context),
                fontInvoiceContentTitle
            )
        headerTable.addCell(invoiceTitlePurchaseId)
        val invoicePurchaseId = createCell(invoice.invoiceNumber.toString(), fontInvoiceContent)
        headerTable.addCell(invoicePurchaseId)

        val invoiceTitlePaymentDue =
            createCell(
                R.string.str_invoice_due_date.asString(context),
                fontInvoiceContentTitle
            )
        headerTable.addCell(invoiceTitlePaymentDue)
        val invoicePaymentDue = createCell(invoice.updatedAt!!, fontInvoiceContent)
        headerTable.addCell(invoicePaymentDue)
        return headerTable
    }
}