package com.friendly.framework.dataclass

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class ProductPrice(
    @ColumnInfo(name = "TaxIncluded") @SerializedName("TaxIncluded") var TaxIncluded: Boolean? = null,
    @ColumnInfo(name = "SGST") @SerializedName("SGST") var SGST: Float? = null,
    @ColumnInfo(name = "CGST") @SerializedName("CGST") var CGST: Float? = null,
    @ColumnInfo(name = "IGST") @SerializedName("IGST") var IGST: Float? = null,
    @ColumnInfo(name = "CESS") @SerializedName("CESS") var CESS: Float? = null,
    @ColumnInfo(name = "VAT") @SerializedName("VAT") var VAT: Float? = null,
    @ColumnInfo(name = "Discount") @SerializedName("Discount") var Discount: Float? = null,
    @ColumnInfo(name = "MRP") @SerializedName("MRP") var MRP: Float? = null,
    @ColumnInfo(name = "Price") @SerializedName("Price") var Price: Float? = null,
    @ColumnInfo(name = "CostPrice") @SerializedName("CostPrice") var CostPrice: Float? = null,
    @ColumnInfo(name = "FinalPrice") @SerializedName("FinalPrice") var FinalPrice: Float? = null,
    @ColumnInfo(name = "Tax") @SerializedName("Tax") var Tax: Float? = null,
)