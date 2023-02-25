package com.friendly.framework.feature.mediaFile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "MediaFile")
data class MediaFile(
    @PrimaryKey @ColumnInfo(name="UniqueID")  @SerializedName("UniqueID") var UniqueID: Long = 0,
    @ColumnInfo(name="_id") @SerializedName("_id") var _id: String? = null,
    @ColumnInfo(name="UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name="BusinessID") @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo(name="FeatureObjectID") @SerializedName("FeatureObjectID") var FeatureObjectID: String? = null,
    @ColumnInfo(name="IsDeleted") @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @ColumnInfo(name="FileURL") @SerializedName("FileURL") var FileURL: String? = null,
    @ColumnInfo(name="CreatedAt") @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @ColumnInfo(name="UpdatedAt") @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @ColumnInfo(name="__v") @SerializedName("__v") var _v: Int? = null
)