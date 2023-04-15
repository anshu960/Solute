package com.friendly.framework.feature.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductStock")
data class ProductStock (
    @PrimaryKey @ColumnInfo(name="_id") @SerializedName("_id"              ) var Id               : String = "",
    @ColumnInfo(name="UserID") @SerializedName("UserID"           ) var UserID           : String? = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID"       ) var BusinessID       : String? = null,
    @ColumnInfo(name="ProductID") @SerializedName("ProductID"        ) var ProductID        : String? = null,
    @ColumnInfo(name="ActionID") @SerializedName("ActionID"         ) var ActionID         : String? = null,
    @ColumnInfo(name="InventoryID") @SerializedName("InventoryID"         ) var InventoryID         : String? = null,
    @ColumnInfo(name="InvoiceID") @SerializedName("InvoiceID"         ) var InvoiceID         : Long? = null,
    @ColumnInfo(name="IncreaseQuantity") @SerializedName("IncreaseQuantity" ) var IncreaseQuantity : Int?    = null,
    @ColumnInfo(name="DecreaseQuantity") @SerializedName("DecreaseQuantity" ) var DecreaseQuantity : Int?    = null,
    @ColumnInfo(name="TotalQuantity") @SerializedName("TotalQuantity"    ) var TotalQuantity    : Int?    = null,
    @ColumnInfo(name="Comment") @SerializedName("Comment"          ) var Comment          : String? = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt"        ) var CreatedAt        : String? = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt"        ) var UpdatedAt        : String? = null,
    @ColumnInfo(name="__v") @SerializedName("__v"              ) var _v               : Int?    = null
)