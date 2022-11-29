package com.utilitykit.feature.employee.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "EmployeeAttendance")
data class EmployeeAttendance(
    @PrimaryKey @ColumnInfo(name = "_id") @SerializedName("_id") var Id: String = "",
    @ColumnInfo(name = "UserID") @SerializedName("UserID") var UserID: String? = null,
    @ColumnInfo(name = "EmployeeID") @SerializedName("EmployeeID") var EmployeeID: String? = null,
    @ColumnInfo(name = "EmployeeUserID") @SerializedName("EmployeeUserID") var EmployeeUserID: String? = null,
    @ColumnInfo(name = "BusinessID") @SerializedName("BusinessID") var BusinessID: String? = null,
    @ColumnInfo(name = "IsDeleted") @SerializedName("IsDeleted") var IsDeleted: Boolean? = null,
    @ColumnInfo(name = "IsApproved") @SerializedName("IsApproved") var IsApproved: Boolean? = null,
    @ColumnInfo(name = "IsPresent") @SerializedName("IsPresent") var Address: Boolean? = null,
    @ColumnInfo(name = "Hours") @SerializedName("Hours") var Hours: Int? = null,
    @ColumnInfo(name = "Comment") @SerializedName("Comment") var Comment: String? = null,
    @ColumnInfo(name = "AttendanceDate") @SerializedName("AttendanceDate") var AttendanceDate: String? = null,
    @SerializedName("CreatedAt") var CreatedAt: String? = null,
    @SerializedName("UpdatedAt") var UpdatedAt: String? = null,
    @SerializedName("__v") var _v: Int? = null
)
