package com.friendly.framework.app

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.friendly.framework.UtilityActivity
import com.friendly.framework.feature.address.handler.AddressHandler
import com.friendly.framework.feature.address.viewModel.AddressViewModalFactory
import com.friendly.framework.feature.address.viewModel.AddressViewModel
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.viewModel.AuthViewModalFactory
import com.friendly.framework.feature.business.viewModel.AuthViewModel
import com.friendly.framework.feature.business.viewModel.BusinessViewModalFactory
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModalFactory
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModel
import com.friendly.framework.feature.cart.handler.CartHandler
import com.friendly.framework.feature.cart.viewModel.CartViewModalFactory
import com.friendly.framework.feature.cart.viewModel.CartViewModel
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.feature.customer.viewModel.CustomerViewModalFactory
import com.friendly.framework.feature.customer.viewModel.CustomerViewModel
import com.friendly.framework.feature.customer.viewModel.EmployeeViewModel
import com.friendly.framework.feature.employee.handler.EmployeeHandler
import com.friendly.framework.feature.employee.viewModel.EmployeeViewModalFactory
import com.friendly.framework.feature.invoice.handler.InvoiceHandler
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModalFactory
import com.friendly.framework.feature.invoice.viewModel.InvoiceViewModel
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.mediaFile.viewModel.MediaFileViewModalFactory
import com.friendly.framework.feature.mediaFile.viewModel.MediaFileViewModel
import com.friendly.framework.feature.product.handler.ProductHandler
import com.friendly.framework.feature.product.viewModel.ProductViewModalFactory
import com.friendly.framework.feature.product.viewModel.ProductViewModel
import com.friendly.framework.feature.productCategory.handler.ProductCategoryHandler
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModalFactory
import com.friendly.framework.feature.productCategory.viewModel.ProductCategoryViewModel
import com.friendly.framework.feature.productSubCategory.handler.ProductSubCategoryHandler
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModalFactory
import com.friendly.framework.feature.productSubCategory.viewModel.ProductSubCategoryViewModel
import com.friendly.frameworkt.feature.business.handler.AuthHandler

class FriendlyFrameworkApp{

    private lateinit var addressViewModal: AddressViewModel
    private lateinit var authViewModal: AuthViewModel
    private lateinit var businessViewModal: BusinessViewModel
    private lateinit var businessTypeViewModal: BusinessTypeViewModel
    private lateinit var cartViewModal: CartViewModel
    private lateinit var customerViewModal: CustomerViewModel
    private lateinit var employeeViewModal: EmployeeViewModel
    private lateinit var invoiceViewModal: InvoiceViewModel
    private lateinit var mediaFileViewModal: MediaFileViewModel
    private lateinit var productViewModal: ProductViewModel
    private lateinit var productCategoryViewModel: ProductCategoryViewModel
    private lateinit var productSubCategoryViewModel: ProductSubCategoryViewModel

    companion object{
        var fragment : Fragment? = null
        private var instance: FriendlyFrameworkApp? = null
        fun shared() : FriendlyFrameworkApp {
            if(instance != null){
                return instance!!
            }else{
                instance = FriendlyFrameworkApp()
                return instance!!
            }
        }
    }

    fun setUp(activity: UtilityActivity){
        //Address
        addressViewModal = ViewModelProvider(activity,
            AddressViewModalFactory(AddressHandler.shared().repository)
        )[AddressViewModel::class.java]
        AddressHandler.shared().setup(addressViewModal)
        //Auth
        authViewModal = ViewModelProvider(activity,
            AuthViewModalFactory(AuthHandler.shared().repository)
        )[AuthViewModel::class.java]
        AuthHandler.shared().setup(authViewModal)
        //Business
        businessViewModal = ViewModelProvider(activity,
            BusinessViewModalFactory(BusinessHandler.shared().repository)
        )[BusinessViewModel::class.java]
        BusinessHandler.shared().setup(businessViewModal)
        //Business Type
        businessTypeViewModal = ViewModelProvider(activity,
            BusinessTypeViewModalFactory(BusinessTypeHandler.shared().repository)
        )[BusinessTypeViewModel::class.java]
        BusinessTypeHandler.shared().setup(businessTypeViewModal)
        //Cart
        cartViewModal = ViewModelProvider(
            activity,
            CartViewModalFactory(CartHandler.shared().repository)
        )[CartViewModel::class.java]
        CartHandler.shared().setup(cartViewModal)
        CartHandler.shared().activity = activity
        CartHandler.shared().viewModel?.resetCart()
        //Customer
        customerViewModal = ViewModelProvider(
            activity,
            CustomerViewModalFactory(CustomerHandler.shared().repository)
        )[CustomerViewModel::class.java]
        CustomerHandler.shared().setup(customerViewModal)
        //Employee
        employeeViewModal = ViewModelProvider(
            activity,
            EmployeeViewModalFactory(EmployeeHandler.shared().repository)
        )[EmployeeViewModel::class.java]
        EmployeeHandler.shared().setup(employeeViewModal)
        //Invoice
        invoiceViewModal = ViewModelProvider(
            activity,
            InvoiceViewModalFactory(InvoiceHandler.shared().repository)
        )[InvoiceViewModel::class.java]
        InvoiceHandler.shared().setup(invoiceViewModal)
        //MediaFile
        mediaFileViewModal = ViewModelProvider(
            activity,
            MediaFileViewModalFactory(MediaFileHandler.shared().repository)
        )[MediaFileViewModel::class.java]
        MediaFileHandler.shared().setup(mediaFileViewModal)
        //Product
        productViewModal = ViewModelProvider(activity,
            ProductViewModalFactory(ProductHandler.shared().repository)
        )[ProductViewModel::class.java]
        productViewModal.fetchAllProduct()
        ProductHandler.shared().setup(productViewModal)
        //category
        productCategoryViewModel = ViewModelProvider(
            activity,
            ProductCategoryViewModalFactory(ProductCategoryHandler.shared().repository)
        )[ProductCategoryViewModel::class.java]
        ProductCategoryHandler.shared().setup(productCategoryViewModel)
        //sub category
        productSubCategoryViewModel = ViewModelProvider(
            activity,
            ProductSubCategoryViewModalFactory(ProductSubCategoryHandler.shared().repository)
        )[ProductSubCategoryViewModel::class.java]
        ProductSubCategoryHandler.shared().setup(productSubCategoryViewModel)

    }
}