package com.utilitykit.feature.commonModel

import com.google.gson.annotations.SerializedName

data class Profile(

    @SerializedName("_id"            ) var Id             : String? = null,
    @SerializedName("UserID"         ) var UserID         : String? = null,
    @SerializedName("RoleType"       ) var RoleType       : String? = null,
    @SerializedName("Name"           ) var Name           : String? = null,
    @SerializedName("EmailID"        ) var EmailID        : String? = null,
    @SerializedName("MobileNumber"   ) var MobileNumber   : String? = null,
    @SerializedName("DialCode"       ) var DialCode       : String? = null,
    @SerializedName("Gender"         ) var Gender         : Int?    = null,
    @SerializedName("Password"       ) var Password       : String? = null,
    @SerializedName("DeviceID"       ) var DeviceID       : String? = null,
    @SerializedName("FCMToken"       ) var FCMToken       : String? = null,
    @SerializedName("ProfilePicture" ) var ProfilePicture : String? = null,
    @SerializedName("LastSeen"       ) var LastSeen       : String? = null,
    @SerializedName("CreatedAt"      ) var CreatedAt      : String? = null,
    @SerializedName("UpdatedAt"      ) var UpdatedAt      : String? = null,
    @SerializedName("__v"            ) var _v             : Int?    = null

)