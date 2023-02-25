package com.friendly.framework.feature.customer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey @ColumnInfo(name="_id") @SerializedName("_id"            ) var Id             : String           = "",
    @ColumnInfo(name="UserID") @SerializedName("UserID"         ) var UserID         : String?           = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID"     ) var BusinessID     : String?           = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted"      ) var IsDeleted      : Boolean?          = null,
    @ColumnInfo(name="Name") @SerializedName("Name"           ) var Name           : String?           = null,
    @ColumnInfo(name="Address") @SerializedName("Address"        ) var Address        : String?           = null,
    @ColumnInfo(name="GSTNumber") @SerializedName("GSTNumber"      ) var GSTNumber      : String?           = null,
    @ColumnInfo(name="Barcode") @SerializedName("Barcode"        ) var Barcode        : String?           = null,
    @ColumnInfo(name="Status") @SerializedName("Status"         ) var Status         : String?           = null,
    @ColumnInfo(name="EmailID") @SerializedName("EmailID"        ) var EmailID        : String?           = null,
    @ColumnInfo(name="MobileNumber") @SerializedName("MobileNumber"   ) var MobileNumber   : String?           = null,
    @ColumnInfo(name="Gender") @SerializedName("Gender"         ) var Gender         : Int?              = null,
    @ColumnInfo(name="DeviceID") @SerializedName("DeviceID"       ) var DeviceID       : String?           = null,
    @ColumnInfo(name="FCMToken") @SerializedName("FCMToken"       ) var FCMToken       : String?           = null,
    @ColumnInfo(name="ProfilePicture") @SerializedName("ProfilePicture" ) var ProfilePicture : ArrayList<String> = arrayListOf(),
    @ColumnInfo(name="TotalSale") @SerializedName("TotalSale"      ) var TodalSale      : Int?              = null,
    @ColumnInfo(name="TotalPayment") @SerializedName("TotalPayment"   ) var TotalPayment   : Int?              = null,
    @ColumnInfo(name="LastSeen") @SerializedName("LastSeen"       ) var LastSeen       : String?           = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt"      ) var CreatedAt      : String?           = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt"      ) var UpdatedAt      : String?           = null,
    @ColumnInfo(name="__v") @SerializedName("__v"            ) var _v             : Int?              = null

)
