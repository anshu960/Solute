package com.friendly.framework.feature.productInventory.network

enum class ProductInventoryEvent(val value: String) {
    CREATE("CREATE_PRODUCT_INVENTORY"),
    UPDATE("UPDATE_PRODUCT_INVENTORY"),
    RETRIEVE("RETRIEVE_PRODUCT_INVENTORY"),
    DELETE("DELETE_PRODUCT_INVENTORY"),

}