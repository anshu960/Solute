package com.friendly.framework.feature.businessMenu

import com.friendly.framework.feature.businessMenu.modal.BusinessMenu

class BusinessMenuConfig {
    fun getMenu(): List<BusinessMenu>{
        var allMenuItems = listOf<BusinessMenu>()
        var firstMenu : ArrayList<BusinessMenu> = arrayListOf()
        firstMenu.add(BusinessMenu("sale","sale","sale_menu_icon","fragment_business_sale","#F5F5D5"))
        firstMenu.add(BusinessMenu("invoice","invoice","receipt_menu_icon","business_invoice","#E3F5E2"))

        var secondMenu: ArrayList<BusinessMenu> = arrayListOf()
        secondMenu.add(BusinessMenu("product","Products","product_menu_icon","inventory_product","#DEF4F8"))
        secondMenu.add(BusinessMenu("category","Category","category_menu_icon","inventory_category","#E7E7E7"))
        secondMenu.add(BusinessMenu("sub_category","Sub Category","sub_category_menu_icon","inventory_sub_category","#F9E0F5"))
        secondMenu.add(BusinessMenu("stock","Stock","stock_menu_icon","business_stock","#FEF7F3"))


        val thirdMenu: ArrayList<BusinessMenu> = arrayListOf()
        thirdMenu.add(BusinessMenu("business","Business","business_menu_icon","my_business_profile","#BCDBFF"))
        thirdMenu.add(BusinessMenu("dashboard","Dashboard","dashboard_menu_icon","business_dashboard","#E2F1E9"))
        thirdMenu.add(BusinessMenu("employee","Employee","employee_menu_icon","business_employee","#FFF3E0"))
        thirdMenu.add(BusinessMenu("customer","Customer","customer_menu_icon","business_customers","#FDE0DD"))

        allMenuItems = listOf(
            BusinessMenu("sale","Sale","","","#ffffff",firstMenu),
            BusinessMenu("inventory","Inventory","","","#ffffff",secondMenu),
            BusinessMenu("business","Business","","","#ffffff",thirdMenu)
        )
        return allMenuItems
    }
}