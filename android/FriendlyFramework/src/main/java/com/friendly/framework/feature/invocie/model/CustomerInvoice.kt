package com.friendly.framework.feature.invoice.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.friendly.framework.feature.cart.model.Sale
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

@Entity(tableName = "CustomerInvoice")
data class CustomerInvoice(
    @PrimaryKey @ColumnInfo(name="_id") @SerializedName("_id") var _id : String = "",
    @ColumnInfo(name="InvoiceID") @SerializedName("InvoiceID") var invoiceID : Long? = null,
    @ColumnInfo(name="ShareLink") @SerializedName("ShareLink") var ShareLink : String? = null,
    @ColumnInfo(name="UserID") @SerializedName("UserID") var userID : String? = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID") var businessID : String? = null,
    @ColumnInfo(name="CustomerID") @SerializedName("CustomerID") var customerID : String? = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted") var isDeleted : Boolean? = null,
    @ColumnInfo(name="Sales") @SerializedName("Sales") var sales : ArrayList<Sale> = arrayListOf(),
    @ColumnInfo(name="InvoiceNumber") @SerializedName("InvoiceNumber") var invoiceNumber : Long? = null,
    @ColumnInfo(name="InvoiceType") @SerializedName("InvoiceType") var invoiceType : String? = null,
    @ColumnInfo(name="TotalPrice") @SerializedName("TotalPrice") var totalPrice : Float? = null,
    @ColumnInfo(name="GST") @SerializedName("GST") var GST : Float? = null,
    @ColumnInfo(name="SGST") @SerializedName("SGST") var SGST : Float? = null,
    @ColumnInfo(name="CGST") @SerializedName("CGST") var CGST : Float? = null,
    @ColumnInfo(name="IGST") @SerializedName("IGST") var IGST : Float? = null,
    @ColumnInfo(name="CESS") @SerializedName("CESS") var CESS : Float? = null,
    @ColumnInfo(name="VAT") @SerializedName("VAT") var VAT : Float? = null,
    @ColumnInfo(name="Tax") @SerializedName("Tax") var Tax : Float? = null,
    @ColumnInfo(name="CostPrice") @SerializedName("CostPrice") var costPrice : Float? = null,
    @ColumnInfo(name="InstantDiscount") @SerializedName("InstantDiscount") var instantDiscount : Float? = null,
    @ColumnInfo(name="FinalPrice") @SerializedName("FinalPrice") var finalPrice : Float? = null,
    @ColumnInfo(name="PendingAmount") @SerializedName("PendingAmount") var pendingAmount : Float? = null,
    @ColumnInfo(name="ClearedAmount") @SerializedName("ClearedAmount") var clearedAmount : Float? = null,
    @ColumnInfo(name="InvoiceDate") @SerializedName("InvoiceDate") var invoiceDate : String? = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt") var createdAt : String? = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt") var updatedAt : String? = null,
    @ColumnInfo(name="__v") @SerializedName("__v") var __v : Int? = null

)
