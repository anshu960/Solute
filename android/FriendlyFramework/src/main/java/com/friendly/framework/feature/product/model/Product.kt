package com.friendly.framework.feature.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey @ColumnInfo(name="_id") @SerializedName("_id") var Id: String = "",
    @ColumnInfo (name =  "ProductID" ) @SerializedName("ProductID") var ProductID: Int? = null,
    @ColumnInfo (name =  "UserID" ) @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo (name =  "BusinessID" ) @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo (name =  "CategoryID" ) @SerializedName("CategoryID") var CategoryID: String? = null,
    @ColumnInfo (name =  "SubCategoryID" ) @SerializedName("SubCategoryID") var SubCategoryID: String? = null,
    @ColumnInfo (name =  "IsDeleted" ) @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @ColumnInfo (name =  "Name" ) @SerializedName("Name") var Name: String? = null,
    @ColumnInfo (name =  "Description" ) @SerializedName("Description") var Description: String? = null,
    @ColumnInfo (name =  "Image" ) @SerializedName("Image") var Image: ArrayList<String> = arrayListOf(),
    @ColumnInfo (name =  "ManageInventory" ) @SerializedName("ManageInventory") var ManageInventory: Boolean? = null,
    @ColumnInfo (name =  "TaxIncluded" ) @SerializedName("TaxIncluded") var TaxIncluded: Boolean? = null,
    @ColumnInfo (name =  "SGST" ) @SerializedName("SGST") var SGST: Float? = null,
    @ColumnInfo (name =  "CGST" ) @SerializedName("CGST") var CGST: Float? = null,
    @ColumnInfo (name =  "IGST" ) @SerializedName("IGST") var IGST: Float? = null,
    @ColumnInfo (name =  "CESS" ) @SerializedName("CESS") var CESS: Float? = null,
    @ColumnInfo (name =  "VAT" ) @SerializedName("VAT") var VAT: Float? = null,
    @ColumnInfo (name =  "Discount" ) @SerializedName("Discount") var Discount: Float? = null,
    @ColumnInfo (name =  "MRP" ) @SerializedName("MRP") var MRP: Float? = null,
    @ColumnInfo (name =  "Price" ) @SerializedName("Price") var Price: Float? = null,
    @ColumnInfo (name =  "CostPrice" ) @SerializedName("CostPrice") var CostPrice: Float? = null,
    @ColumnInfo (name =  "FinalPrice" ) @SerializedName("FinalPrice") var FinalPrice: Float? = null,
    @ColumnInfo (name =  "Tax" ) @SerializedName("Tax") var Tax: Float? = null,
    @ColumnInfo (name =  "CreatedAt" ) @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo (name =  "UpdatedAt" ) @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo (name =  "__v" ) @SerializedName("__v") var _v: Int? = null
)