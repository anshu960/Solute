package com.friendly.framework.feature.businessMenu

import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.businessMenu.modal.BusinessMenu
import com.friendly.framework.feature.businessMenu.modal.MenuType

class BusinessMenuConfig {
    fun getMenu(): List<BusinessMenu>{
        var allMenuItems = listOf<BusinessMenu>()
        val firstMenu : ArrayList<BusinessMenu> = arrayListOf()
        firstMenu.add(BusinessMenu("sale",MenuType.MENU,"Sale","sale_menu_icon","fragment_business_sale","#F5F5D5"))
        firstMenu.add(BusinessMenu("invoice",MenuType.MENU,"invoice","receipt_menu_icon","business_invoice","#E3F5E2"))

        val secondMenu: ArrayList<BusinessMenu> = arrayListOf()
        secondMenu.add(BusinessMenu("product",MenuType.MENU,"Products","product_menu_icon","inventory_product","#DEF4F8"))
        secondMenu.add(BusinessMenu("category",MenuType.MENU,"Category","category_menu_icon","inventory_category","#E7E7E7"))
        secondMenu.add(BusinessMenu("sub_category",MenuType.MENU,"Sub Category","sub_category_menu_icon","inventory_sub_category","#F9E0F5"))
        secondMenu.add(BusinessMenu("stock",MenuType.MENU,"Stock","stock_menu_icon","business_stock","#FEF7F3"))


        val thirdMenu: ArrayList<BusinessMenu> = arrayListOf()
        if(BusinessHandler.shared().viewModal?.selectedBusiness?.value != null){
            BusinessHandler.shared().viewModal?.selectedBusiness?.value
            thirdMenu.add(BusinessMenu("business",MenuType.MENU,BusinessHandler.shared().viewModal?.selectedBusiness?.value?.Name,"business_menu_icon","my_business_profile","#BCDBFF"))
        }else{
            thirdMenu.add(BusinessMenu("business",MenuType.MENU,"Business","business_menu_icon","my_business_profile","#BCDBFF"))
        }

        thirdMenu.add(BusinessMenu("dashboard",MenuType.MENU,"Dashboard","dashboard_menu_icon","business_dashboard","#E2F1E9"))
        thirdMenu.add(BusinessMenu("employee",MenuType.MENU,"Employee","employee_menu_icon","business_employee","#FFF3E0"))
        thirdMenu.add(BusinessMenu("customer",MenuType.MENU,"Customer","customer_menu_icon","business_customers","#FDE0DD"))
        thirdMenu.add(BusinessMenu("all_business",MenuType.MENU,"All Business","business_menu_icon","business_list","#E7E7E7"))

        allMenuItems = listOf(
            BusinessMenu("sale_header",MenuType.HEADER,"Sale","sale_menu_icon","fragment_business_sale","#F5F5D5"),
            BusinessMenu("sale",MenuType.MENU,"","","","#ffffff",firstMenu),
            BusinessMenu("inventory_header",MenuType.HEADER,"Inventory","product_menu_icon","inventory_product","#DEF4F8"),
            BusinessMenu("inventory",MenuType.MENU,"","","","#ffffff",secondMenu),
            BusinessMenu("store_header",MenuType.HEADER,"Store","product_menu_icon","inventory_product","#DEF4F8"),
            BusinessMenu("store",MenuType.MENU,"","","","#ffffff",thirdMenu)
        )
        return allMenuItems
    }
}