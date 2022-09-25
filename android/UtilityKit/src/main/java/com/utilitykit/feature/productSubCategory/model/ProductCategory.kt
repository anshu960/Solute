package com.utilitykit.feature.productSubCategory.model

import com.google.gson.annotations.SerializedName

data class ProductSubCategory(
    @SerializedName("ProductCategoryID") var ProductCategoryID: Long? = null,
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("UserID") var UserID: String? = null,
    @SerializedName("BusinessID") var BusinessID: String? = null,
    @SerializedName("CategoryID") var CategoryID: String? = null,
    @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Image") var Image: String? = null,
    @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null
)