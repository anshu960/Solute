package com.utilitykit.feature.employee.model

import com.google.gson.annotations.SerializedName

data class Employee(
    @SerializedName("_id"            ) var Id             : String?           = null,
    @SerializedName("UserID"         ) var UserID         : String?           = null,
    @SerializedName("BusinessID"     ) var BusinessID     : String?           = null,
    @SerializedName("IsDeleted"      ) var IsDeleted      : Boolean?          = null,
    @SerializedName("Name"           ) var Name           : String?           = null,
    @SerializedName("Address"        ) var Address        : String?           = null,
    @SerializedName("GSTNumber"      ) var GSTNumber      : String?           = null,
    @SerializedName("Barcode"        ) var Barcode        : String?           = null,
    @SerializedName("Status"         ) var Status         : String?           = null,
    @SerializedName("EmailID"        ) var EmailID        : String?           = null,
    @SerializedName("MobileNumber"   ) var MobileNumber   : String?           = null,
    @SerializedName("Gender"         ) var Gender         : Int?              = null,
    @SerializedName("DeviceID"       ) var DeviceID       : String?           = null,
    @SerializedName("FCMToken"       ) var FCMToken       : String?           = null,
    @SerializedName("ProfilePicture" ) var ProfilePicture : ArrayList<String> = arrayListOf(),
    @SerializedName("TodalSale"      ) var TodalSale      : Int?              = null,
    @SerializedName("TotalPayment"   ) var TotalPayment   : Int?              = null,
    @SerializedName("LastSeen"       ) var LastSeen       : String?           = null,
    @SerializedName("CreatedAt"      ) var CreatedAt      : String?           = null,
    @SerializedName("UpdatedAt"      ) var UpdatedAt      : String?           = null,
    @SerializedName("__v"            ) var _v             : Int?              = null

)
