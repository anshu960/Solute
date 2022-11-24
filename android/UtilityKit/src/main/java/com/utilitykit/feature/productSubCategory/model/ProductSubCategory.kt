package com.utilitykit.feature.productSubCategory.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ProductSubCategory")
data class ProductSubCategory(
    @PrimaryKey @ColumnInfo(name="_id")  @SerializedName("_id") var Id: String = "",
    @ColumnInfo(name="ProductSubCategoryID")  @SerializedName("ProductSubCategoryID") var ProductSubCategoryID: Long? = null,
    @ColumnInfo(name="UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo(name="CategoryID") @SerializedName("CategoryID") var CategoryID: String? = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @ColumnInfo(name="Name") @SerializedName("Name") var Name: String? = null,
    @ColumnInfo(name="Image") @SerializedName("Image") var Image: String? = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo(name="__v") @SerializedName("__v") var _v: Int? = null
)