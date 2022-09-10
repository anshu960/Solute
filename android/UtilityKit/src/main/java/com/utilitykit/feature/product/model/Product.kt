package com.utilitykit.feature.product.model

import com.google.gson.annotations.SerializedName

 data class Product(
    @SerializedName("_id"             ) var Id              : String?           = null,
    @SerializedName("ProductID"       ) var ProductID       : Int?              = null,
    @SerializedName("UserID"          ) var UserID          : String?           = null,
    @SerializedName("BusinessID"      ) var BusinessID      : String?           = null,
    @SerializedName("IsDeleted"       ) var IsDeleted       : Boolean?          = null,
    @SerializedName("Name"            ) var Name            : String?           = null,
    @SerializedName("Description"     ) var Description     : String?           = null,
    @SerializedName("Image"           ) var Image           : ArrayList<String> = arrayListOf(),
    @SerializedName("ManageInventory" ) var ManageInventory : Boolean?          = null,
    @SerializedName("TaxIncluded"     ) var TaxIncluded     : Boolean?          = null,
    @SerializedName("SGST"            ) var SGST            : String?           = null,
    @SerializedName("CGST"            ) var CGST            : String?           = null,
    @SerializedName("IGST"            ) var IGST            : String?           = null,
    @SerializedName("CESS"            ) var CESS            : String?           = null,
    @SerializedName("VAT"             ) var VAT             : String?           = null,
    @SerializedName("Discount"        ) var Discount        : Int?              = null,
    @SerializedName("MRP"             ) var MRP             : Int?              = null,
    @SerializedName("Price"           ) var Price           : Int?              = null,
    @SerializedName("CostPrice"       ) var CostPrice       : Int?              = null,
    @SerializedName("FinalPrice"      ) var FinalPrice      : Int?              = null,
    @SerializedName("Tax"             ) var Tax             : Int?              = null,
    @SerializedName("CreatedAt"       ) var CreatedAt       : String?           = null,
    @SerializedName("UpdatedAt"       ) var UpdatedAt       : String?           = null,
    @SerializedName("__v"             ) var _v              : Int?              = null
)