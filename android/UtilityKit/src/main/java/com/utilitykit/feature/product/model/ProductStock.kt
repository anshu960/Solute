package com.utilitykit.feature.product.model

import com.google.gson.annotations.SerializedName

data class ProductStock (

    @SerializedName("_id"              ) var Id               : String? = null,
    @SerializedName("UserID"           ) var UserID           : String? = null,
    @SerializedName("BusinessID"       ) var BusinessID       : String? = null,
    @SerializedName("ProductID"        ) var ProductID        : String? = null,
    @SerializedName("ActionID"         ) var ActionID         : String? = null,
    @SerializedName("IncreaseQuantity" ) var IncreaseQuantity : Int?    = null,
    @SerializedName("DecreaseQuantity" ) var DecreaseQuantity : Int?    = null,
    @SerializedName("TotalQuantity"    ) var TotalQuantity    : Int?    = null,
    @SerializedName("Comment"          ) var Comment          : String? = null,
    @SerializedName("CreatedAt"        ) var CreatedAt        : String? = null,
    @SerializedName("UpdatedAt"        ) var UpdatedAt        : String? = null,
    @SerializedName("__v"              ) var _v               : Int?    = null

)