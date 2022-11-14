package com.utilitykit.feature.invoice.model

import com.google.gson.annotations.SerializedName

data class CustomerInvoice(
    @SerializedName("_id") val _id : String,
    @SerializedName("UserID") val userID : String,
    @SerializedName("BusinessID") val businessID : String,
    @SerializedName("CustomerID") val customerID : String,
    @SerializedName("IsDeleted") val isDeleted : Boolean,
    @SerializedName("SalesID") val salesID : List<String>,
    @SerializedName("InvoiceNumber") val invoiceNumber : Long,
    @SerializedName("InvoiceType") val invoiceType : String,
    @SerializedName("TotalPrice") val totalPrice : Float,
    @SerializedName("GST") val GST : Float,
    @SerializedName("SGST") val SGST : Float,
    @SerializedName("CGST") val CGST : Float,
    @SerializedName("IGST") val IGST : Float,
    @SerializedName("CESS") val CESS : Float,
    @SerializedName("VAT") val VAT : Float,
    @SerializedName("Tax") val Tax : Float,
    @SerializedName("CostPrice") val costPrice : Float,
    @SerializedName("InstantDiscount") val instantDiscount : Float,
    @SerializedName("FinalPrice") val finalPrice : Float,
    @SerializedName("PendingAmount") val pendingAmount : Float,
    @SerializedName("ClearedAmount") val clearedAmount : Float,
    @SerializedName("InvoiceDate") val invoiceDate : String,
    @SerializedName("InvoiceID") val invoiceID : Long,
    @SerializedName("CreatedAt") val createdAt : String,
    @SerializedName("UpdatedAt") val updatedAt : String,
    @SerializedName("__v") val __v : Int

)
