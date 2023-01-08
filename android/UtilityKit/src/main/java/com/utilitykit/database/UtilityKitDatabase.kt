package com.utilitykit.database

import android.content.Context
import androidx.room.*
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.model.BusinessDao
import com.utilitykit.feature.cart.model.Sale
import com.utilitykit.feature.customer.model.Customer
import com.utilitykit.feature.customer.model.CustomerDao
import com.utilitykit.feature.employee.dao.EmployeeAttendanceDao
import com.utilitykit.feature.employee.dao.EmployeeDao
import com.utilitykit.feature.employee.model.Employee
import com.utilitykit.feature.employee.model.EmployeeAttendance
import com.utilitykit.feature.invocie.model.CustomerInvoiceDao
import com.utilitykit.feature.invocie.model.SaleDao
import com.utilitykit.feature.invoice.model.CustomerInvoice
import com.utilitykit.feature.mediaFile.model.MediaFile
import com.utilitykit.feature.mediaFile.model.MediaFileDao
import com.utilitykit.feature.product.model.*
import com.utilitykit.feature.productCategory.model.ProductCategory
import com.utilitykit.feature.productCategory.model.ProductCategoryDao
import com.utilitykit.feature.productSubCategory.model.ProductSubCategory
import com.utilitykit.feature.productSubCategory.model.ProductSubCategoryDao
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromList(value: ArrayList<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toArrayList(value: String) = Json.decodeFromString<ArrayList<String>>(value)
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
    ],
    version = 25,
    exportSchema = false
)
abstract class UtilityKitDatabase : RoomDatabase() {

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
    abstract fun mediaFileDao():MediaFileDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UtilityKitDatabase? = null

        fun getDatabase(context: Context): UtilityKitDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): UtilityKitDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                UtilityKitDatabase::class.java,
                "UtilityKitDatabase"
            ).fallbackToDestructiveMigration().build()
        }
    }
}