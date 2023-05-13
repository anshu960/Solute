package com.friendly.framework.feature.productInventory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.friendly.framework.dataclass.ProductPrice
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductInventory")
data class ProductInventory(
    @PrimaryKey @ColumnInfo(name = "_id") @SerializedName("_id") var Id: String = "",
    @ColumnInfo(name = "UniqueID") @SerializedName("UniqueID") var UniqueID: String? = null,
    @ColumnInfo(name = "UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name = "BusinessID") @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo(name = "ProductID") @SerializedName("ProductID") var ProductID: String? = null,
    @ColumnInfo (name =  "ProductPrice" ) @SerializedName("ProductPrice") var ProductPrice: ProductPrice? = null,
    @ColumnInfo(name = "BatchNumber") @SerializedName("BatchNumber") var BatchNumber: Int? = null,
    @ColumnInfo(name = "BatchDate") @SerializedName("BatchDate") var BatchDate: Int? = null,
    @ColumnInfo(name = "Name") @SerializedName("Name") var Name: String? = null,
    @ColumnInfo(name = "Description") @SerializedName("Description") var Description: String? = null,
    @ColumnInfo(name = "Model") @SerializedName("Model") var Model: String? = null,
    @ColumnInfo(name = "Brand") @SerializedName("Brand") var Brand: String? = null,
    @ColumnInfo(name = "Color") @SerializedName("Color") var Color: String? = null,
    @ColumnInfo(name = "MeasurementUnit") @SerializedName("MeasurementUnit") var MeasurementUnit: String? = null,
    @ColumnInfo(name = "IsAvailableOnline") @SerializedName("MeasurementUnit") var IsAvailableOnline: Boolean? = null,
    @ColumnInfo(name = "IncreaseQuantity") @SerializedName("IncreaseQuantity") var IncreaseQuantity: Int? = null,
    @ColumnInfo(name = "DecreaseQuantity") @SerializedName("DecreaseQuantity") var DecreaseQuantity: Int? = null,
    @ColumnInfo(name = "TotalQuantity") @SerializedName("TotalQuantity") var TotalQuantity: Int? = null,
    @ColumnInfo(name = "Comment") @SerializedName("Comment") var Comment: String? = null,
    @ColumnInfo(name = "CreatedAt") @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo(name = "UpdatedAt") @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo(name = "__v") @SerializedName("__v") var _v: Int? = null
)