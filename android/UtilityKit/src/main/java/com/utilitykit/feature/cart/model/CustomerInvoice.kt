package com.utilitykit.feature.cart.model

import com.google.gson.annotations.SerializedName

data class CustomerInvoice(
    @SerializedName("UserID"          ) var UserID          : String?                      = null,
    @SerializedName("BusinessID"      ) var BusinessID      : String?                      = null,
    @SerializedName("CustomerID"      ) var CustomerID      : String?                      = null,
    @SerializedName("IsDeleted"       ) var IsDeleted       : Boolean?                     = null,
    @SerializedName("SalesID"         ) var SalesID         : ArrayList<ArrayList<String>> = arrayListOf(),
    @SerializedName("InvoiceNumber"   ) var InvoiceNumber   : Long?                         = null,
    @SerializedName("TotalPrice"      ) var TotalPrice      : Float?                         = null,
    @SerializedName("SGST"            ) var SGST            : String?                      = null,
    @SerializedName("CGST"            ) var CGST            : String?                      = null,
    @SerializedName("IGST"            ) var IGST            : String?                      = null,
    @SerializedName("CESS"            ) var CESS            : String?                      = null,
    @SerializedName("VAT"             ) var VAT             : String?                      = null,
    @SerializedName("Tax"             ) var Tax             : Float?                         = null,
    @SerializedName("InstantDiscount" ) var InstantDiscount : Float?                         = null,
    @SerializedName("FinalPrice"      ) var FinalPrice      : Float?                         = null,
    @SerializedName("PendingAmount"   ) var PendingAmount   : Float?                         = null,
    @SerializedName("ClearedAmount"   ) var ClearedAmount   : Float?                         = null,
    @SerializedName("InvoiceDate"     ) var InvoiceDate     : String?                      = null,
    @SerializedName("InvoiceID"       ) var InvoiceID       : Long?                         = null,
    @SerializedName("CreatedAt"       ) var CreatedAt       : String?                      = null,
    @SerializedName("UpdatedAt"       ) var UpdatedAt       : String?                      = null,
    @SerializedName("_id"             ) var Id              : String?                      = null,
    @SerializedName("__v"             ) var _v              : Int?                         = null

)
