package com.friendly.framework.feature.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductBarCode")
data class ProductBarCode(
    @PrimaryKey @ColumnInfo(name = "UniqueID") @SerializedName("UniqueID") var UniqueID: Long = 0,
    @ColumnInfo(name = "_id") @SerializedName("_id") var Id: String = "",
    @ColumnInfo(name = "UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name = "BusinessID") @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo(name = "ProductID") @SerializedName("ProductID") var ProductID: String? = null,
    @ColumnInfo(name = "InventoryID") @SerializedName("InventoryID") var InventoryID: String? = null,
    @ColumnInfo(name = "InvoiceID") @SerializedName("InvoiceID") var InvoiceID: Long? = null,
    @ColumnInfo(name = "BarCode") @SerializedName("BarCode") var BarCode: String? = null,
    @ColumnInfo(name = "ISUsed") @SerializedName("ISUsed") var ISUsed: Boolean? = null,
    @ColumnInfo(name = "ISDeleted") @SerializedName("ISDeleted") var ISDeleted: Boolean? = null,
    @ColumnInfo(name = "CreatedAt") @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo(name = "UpdatedAt") @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo(name = "__v") @SerializedName("__v") var _v: Int? = null
)