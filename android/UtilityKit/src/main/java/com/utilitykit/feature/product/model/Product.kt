package com.utilitykit.feature.product.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("ProductID") var ProductID: Int? = null,
    @SerializedName("UserID") var UserID: String? = null,
    @SerializedName("BusinessID") var BusinessID: String? = null,
    @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Description") var Description: String? = null,
    @SerializedName("Image") var Image: ArrayList<String> = arrayListOf(),
    @SerializedName("ManageInventory") var ManageInventory: Boolean? = null,
    @SerializedName("TaxIncluded") var TaxIncluded: Boolean? = null,
    @SerializedName("SGST") var SGST: Float? = null,
    @SerializedName("CGST") var CGST: Float? = null,
    @SerializedName("IGST") var IGST: Float? = null,
    @SerializedName("CESS") var CESS: Float? = null,
    @SerializedName("VAT") var VAT: Float? = null,
    @SerializedName("Discount") var Discount: Float? = null,
    @SerializedName("MRP") var MRP: Float? = null,
    @SerializedName("Price") var Price: Float? = null,
    @SerializedName("CostPrice") var CostPrice: Float? = null,
    @SerializedName("FinalPrice") var FinalPrice: Float? = null,
    @SerializedName("Tax") var Tax: Float? = null,
    @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null
)