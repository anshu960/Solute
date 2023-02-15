package com.utilitykit.feature.address.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "Address")
data class Address(
    @PrimaryKey @ColumnInfo(name="UniqueID")  @SerializedName("UniqueID") var UniqueID: Long = 0,
    @ColumnInfo(name="_id") @SerializedName("_id") var _id: String? = null,
    @ColumnInfo(name="UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name="FeatureObjectID") @SerializedName("FeatureObjectID") var FeatureObjectID: String? = null,
    @ColumnInfo(name="Name") @SerializedName("Name") var Name: String? = null,
    @ColumnInfo(name="ZipCode") @SerializedName("ZipCode") var ZipCode: String? = null,
    @ColumnInfo(name="Country") @SerializedName("Country") var Country: String? = null,
    @ColumnInfo(name="State") @SerializedName("State") var State: String? = null,
    @ColumnInfo(name="City") @SerializedName("City") var City: String? = null,
    @ColumnInfo(name="Area") @SerializedName("Area") var Area: String? = null,
    @ColumnInfo(name="LandMark") @SerializedName("LandMark") var LandMark: String? = null,
    @ColumnInfo(name="House") @SerializedName("House") var House: String? = null,
    @ColumnInfo(name="FlatNumber") @SerializedName("FlatNumber") var FlatNumber: String? = null,
    @ColumnInfo(name="Floor") @SerializedName("Floor") var Floor: String? = null,
    @ColumnInfo(name="MobileNumber") @SerializedName("MobileNumber") var MobileNumber: String? = null,
    @ColumnInfo(name="Type") @SerializedName("Type") var Type: String? = null,
    @ColumnInfo(name="Location") @SerializedName("Location") var Location: ArrayList<String> = arrayListOf(),
    @ColumnInfo(name="IsPrimary") @SerializedName("IsPrimary") var IsPrimary: Boolean? = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo(name="__v") @SerializedName("__v") var _v: Int? = null
)
