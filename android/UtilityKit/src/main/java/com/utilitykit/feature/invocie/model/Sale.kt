package com.utilitykit.feature.cart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Sale")
data class Sale(
    @PrimaryKey @ColumnInfo(name="_id") @SerializedName("_id"            ) var Id             : String = "",
    @ColumnInfo(name="UserID") @SerializedName("UserID"         ) var UserID         : String?  = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID"     ) var BusinessID     : String?  = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted"      ) var IsDeleted      : Boolean? = null,
    @ColumnInfo(name="CustomerName") @SerializedName("CustomerName"   ) var CustomerName   : String?  = null,
    @ColumnInfo(name="CustomerMobile") @SerializedName("CustomerMobile" ) var CustomerMobile : String?  = null,
    @ColumnInfo(name="VehicleNumber") @SerializedName("VehicleNumber"  ) var VehicleNumber  : String?  = null,
    @ColumnInfo(name="ProductName") @SerializedName("ProductName"    ) var ProductName    : String?  = null,
    @ColumnInfo(name="ProductID") @SerializedName("ProductID"      ) var ProductID      : String?  = null,
    @ColumnInfo(name="PaymentMode") @SerializedName("PaymentMode"    ) var PaymentMode    : String?  = null,
    @ColumnInfo(name="Quantity") @SerializedName("Quantity"       ) var Quantity       : Int?     = null,
    @ColumnInfo(name="Price") @SerializedName("Price"          ) var Price          : Float?     = null,
    @ColumnInfo(name="Discount") @SerializedName("Discount"       ) var Discount       : Float?     = null,
    @ColumnInfo(name="SGST") @SerializedName("SGST"           ) var SGST           : Float?  = null,
    @ColumnInfo(name="CGST") @SerializedName("CGST"           ) var CGST           : Float?  = null,
    @ColumnInfo(name="IGST") @SerializedName("IGST"           ) var IGST           : Float?  = null,
    @ColumnInfo(name="CESS") @SerializedName("CESS"           ) var CESS           : Float?  = null,
    @ColumnInfo(name="VAT") @SerializedName("VAT"            ) var VAT            : Float?  = null,
    @ColumnInfo(name="Tax") @SerializedName("Tax"            ) var Tax            : Float?     = null,
    @ColumnInfo(name="FinalPrice") @SerializedName("FinalPrice"     ) var FinalPrice     : Float?     = null,
    @ColumnInfo(name="SaleDate") @SerializedName("SaleDate"       ) var SaleDate       : String?  = null,
    @ColumnInfo(name="SaleTime") @SerializedName("SaleTime"       ) var SaleTime       : Long?     = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt"      ) var CreatedAt      : String?  = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt"      ) var UpdatedAt      : String?  = null,
    @ColumnInfo(name="__v") @SerializedName("__v"            ) var _v             : Int?     = null,
)
