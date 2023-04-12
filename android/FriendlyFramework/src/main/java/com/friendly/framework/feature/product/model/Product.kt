package com.friendly.framework.feature.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.friendly.framework.dataclass.ProductPrice
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
    @ColumnInfo (name =  "ManageInventory" ) @SerializedName("ManageInventory") var ManageInventory: Boolean? = null,
    @ColumnInfo (name =  "ProductPrice" ) @SerializedName("ProductPrice") var ProductPrice: ProductPrice? = null,
    @ColumnInfo (name =  "CreatedAt" ) @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo (name =  "UpdatedAt" ) @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo (name =  "__v" ) @SerializedName("__v") var _v: Int? = null
)