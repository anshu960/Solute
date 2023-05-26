package com.friendly.framework.feature.businessMenu.modal

import com.google.gson.annotations.SerializedName

enum class MenuType{
     HEADER, FOOTER, MENU
}
data class BusinessMenu (
    @SerializedName("id") var id: String? = null,
    @SerializedName("type") var menuType: MenuType = MenuType.MENU,
    @SerializedName("title") var title: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("target_screen") var target_screen: String? = null,
    @SerializedName("background_color") var background_color: String? = null,
    @SerializedName("sub_menu") var sub_menu: ArrayList<BusinessMenu> = arrayListOf(),
)