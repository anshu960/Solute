package com.friendly.framework.feature.businessType.model

import com.google.gson.annotations.SerializedName

data class BusinessType(
    @SerializedName("_id") var Id: String? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Description") var Description: String? = null,
    @SerializedName("Image") var Image: ArrayList<String> = arrayListOf(),
    @SerializedName("BusinessTypeID") var BusinessTypeID: Long = 0,
    @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null
)