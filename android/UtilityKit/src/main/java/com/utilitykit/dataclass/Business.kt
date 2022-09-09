package com.utilitykit.dataclass

import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("_id"            ) var Id             : String?           = null,
    @SerializedName("BusinessNumber" ) var BusinessNumber : Int?              = null,
    @SerializedName("UserID"         ) var UserID         : String?           = null,
    @SerializedName("BusinessTypeID" ) var BusinessTypeID : String?           = null,
    @SerializedName("IsDeleted"      ) var IsDeleted      : Boolean?          = null,
    @SerializedName("Name"           ) var Name           : String?           = null,
    @SerializedName("Address"        ) var Address        : String?           = null,
    @SerializedName("DealerName"     ) var DealerName     : String?           = null,
    @SerializedName("ProductTypes"   ) var ProductTypes   : ArrayList<String> = arrayListOf(),
    @SerializedName("GSTNumber"      ) var GSTNumber      : String?           = null,
    @SerializedName("Status"         ) var Status         : String?           = null,
    @SerializedName("EmailID"        ) var EmailID        : String?           = null,
    @SerializedName("MobileNumber"   ) var MobileNumber   : String?           = null,
    @SerializedName("DialCode"       ) var DialCode       : String?           = null,
    @SerializedName("Gender"         ) var Gender         : Int?              = null,
    @SerializedName("DeviceID"       ) var DeviceID       : String?           = null,
    @SerializedName("FCMToken"       ) var FCMToken       : String?           = null,
    @SerializedName("ProfilePicture" ) var ProfilePicture : ArrayList<String> = arrayListOf(),
    @SerializedName("LastSeen"       ) var LastSeen       : String?           = null,
    @SerializedName("CreatedAt"      ) var CreatedAt      : String?           = null,
    @SerializedName("UpdatedAt"      ) var UpdatedAt      : String?           = null,
    @SerializedName("__v"            ) var _v             : Int?              = null
)