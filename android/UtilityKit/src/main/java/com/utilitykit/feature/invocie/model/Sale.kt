package com.utilitykit.feature.cart.model

import com.google.gson.annotations.SerializedName

data class Sale(
    @SerializedName("UserID"         ) var UserID         : String?  = null,
    @SerializedName("BusinessID"     ) var BusinessID     : String?  = null,
    @SerializedName("IsDeleted"      ) var IsDeleted      : Boolean? = null,
    @SerializedName("CustomerName"   ) var CustomerName   : String?  = null,
    @SerializedName("CustomerMobile" ) var CustomerMobile : String?  = null,
    @SerializedName("VehicleNumber"  ) var VehicleNumber  : String?  = null,
    @SerializedName("ProductName"    ) var ProductName    : String?  = null,
    @SerializedName("ProductID"      ) var ProductID      : String?  = null,
    @SerializedName("PaymentMode"    ) var PaymentMode    : String?  = null,
    @SerializedName("Quantity"       ) var Quantity       : Int?     = null,
    @SerializedName("Price"          ) var Price          : Float?     = null,
    @SerializedName("Discount"       ) var Discount       : Float?     = null,
    @SerializedName("SGST"           ) var SGST           : Float?  = null,
    @SerializedName("CGST"           ) var CGST           : Float?  = null,
    @SerializedName("IGST"           ) var IGST           : Float?  = null,
    @SerializedName("CESS"           ) var CESS           : Float?  = null,
    @SerializedName("VAT"            ) var VAT            : Float?  = null,
    @SerializedName("Tax"            ) var Tax            : Float?     = null,
    @SerializedName("FinalPrice"     ) var FinalPrice     : Float?     = null,
    @SerializedName("SaleDate"       ) var SaleDate       : String?  = null,
    @SerializedName("SaleTime"       ) var SaleTime       : Long?     = null,
    @SerializedName("CreatedAt"      ) var CreatedAt      : String?  = null,
    @SerializedName("UpdatedAt"      ) var UpdatedAt      : String?  = null,
    @SerializedName("_id"            ) var Id             : String?  = null,
    @SerializedName("__v"            ) var _v             : Int?     = null,
)
