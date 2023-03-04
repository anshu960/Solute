package com.friendly.framework.database

import android.content.Context
import androidx.room.*
import com.friendly.framework.feature.address.model.Address
import com.friendly.framework.feature.address.model.AddressDao
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.business.model.BusinessDao
import com.friendly.framework.feature.cart.model.Sale
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.model.CustomerDao
import com.friendly.framework.feature.employee.dao.EmployeeAttendanceDao
import com.friendly.framework.feature.employee.dao.EmployeeDao
import com.friendly.framework.feature.employee.model.Employee
import com.friendly.framework.feature.employee.model.EmployeeAttendance
import com.friendly.framework.feature.invocie.model.CustomerInvoiceDao
import com.friendly.framework.feature.invocie.model.SaleDao
import com.friendly.framework.feature.invoice.model.CustomerInvoice
import com.friendly.framework.feature.mediaFile.model.MediaFile
import com.friendly.framework.feature.mediaFile.model.MediaFileDao
import com.friendly.framework.feature.product.model.*
import com.friendly.framework.feature.productCategory.model.ProductCategory
import com.friendly.framework.feature.productCategory.model.ProductCategoryDao
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategory
import com.friendly.framework.feature.productSubCategory.model.ProductSubCategoryDao
import com.google.gson.Gson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.json.JSONArray
import org.json.JSONObject


class Converters {
    @TypeConverter
    fun fromList(value: ArrayList<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toArrayList(value: String) = Json.decodeFromString<ArrayList<String>>(value)

    @TypeConverter
    fun toJson(value: String?): JSONObject {
        if(value.isNullOrEmpty() || value == "null"){
            return JSONObject()
        }else{
            return JSONObject(value)
        }
    }

    @TypeConverter
    fun toJsonString(value: JSONObject?): String {
        try {
            return value.toString()
        }
        catch (error:java.lang.Error){
            return ""
        }
    }

    @TypeConverter
    fun toJsonArray(value: String?): JSONArray {
        if(value.isNullOrEmpty() || value == "null"){
            return JSONArray()
        }else{
            return JSONArray(value)
        }
    }

    @TypeConverter
    fun toJsonArrayString(value: JSONArray?): String {
        try {
            return value.toString()
        }
        catch (error:java.lang.Error){
            return ""
        }
    }

    @TypeConverter
    fun toJsonArrayList(value: String?): ArrayList<JSONObject> {
        val output = ArrayList<JSONObject>()
        if(value.isNullOrEmpty() || value == "null"){
            val jsonArray = JSONArray(value)
            for (i in 0 until jsonArray.length()) {
                val singleObj = jsonArray.get(i)
                output.add(singleObj as JSONObject)
            }
        }
        return output
    }

    @TypeConverter
    fun saleArrayListToString(value: ArrayList<Sale>): String {
        val jsonArray = JSONArray()
        value.forEach {
            jsonArray.put(JSONObject(Gson().toJson(it)))
        }
        return try {
            jsonArray.toString()
        } catch (error:java.lang.Error){
            ""
        }
    }
    @TypeConverter
    fun jsonArrayListToSaleArray(value: ArrayList<JSONObject>): ArrayList<Sale> {
        val salesArray : ArrayList<Sale> = arrayListOf()
        value.forEach {
            val sale = Gson().fromJson(it.toString(),Sale::class.java)
            salesArray.add(sale)
        }
        return  salesArray
    }

    @TypeConverter
    fun stringToSalesArray(value:String): ArrayList<Sale> {
        val jsonArray = JSONArray(value)
        val salesArray : ArrayList<Sale> = arrayListOf()
        for (i in 0 until jsonArray.length()) {
            val singleObj = jsonArray.get(i)
            val sale = Gson().fromJson(singleObj.toString(),Sale::class.java)
            salesArray.add(sale)
        }
       return  salesArray
    }
}


// Annotates class to be a Room Database with a table (entity) of the Word class
@TypeConverters(Converters::class)
@Database(
    entities = [
        Business::class,
        Sale::class,
        ProductStock::class,
        Product::class,
        Customer::class,
        CustomerInvoice::class,
        ProductCategory::class,
        ProductSubCategory::class,
        Employee::class,
        EmployeeAttendance::class,
        MediaFile::class,
        ProductBarCode::class,
        Address::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class FriendlyDatabase : RoomDatabase() {

    abstract fun businessDao(): BusinessDao
    abstract fun saleDao(): SaleDao
    abstract fun productDao(): ProductDao
    abstract fun productStockDao(): ProductStockDao
    abstract fun productBarCodeDao(): ProductBarCodeDao

    abstract fun customerDao(): CustomerDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun employeeAttendanceDao(): EmployeeAttendanceDao
    abstract fun customerInvoiceDao(): CustomerInvoiceDao
    abstract fun productCategoryDao(): ProductCategoryDao
    abstract fun productSubCategoryDao(): ProductSubCategoryDao
    abstract fun mediaFileDao(): MediaFileDao
    abstract fun addressDao(): AddressDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FriendlyDatabase? = null

        fun getDatabase(context: Context): FriendlyDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): FriendlyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                FriendlyDatabase::class.java,
                "FriendlyDatabase"
            ).fallbackToDestructiveMigration().build()
        }
    }
}