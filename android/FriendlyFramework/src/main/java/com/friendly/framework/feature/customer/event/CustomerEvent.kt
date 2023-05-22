package com.friendly.framework.feature.customer.event

enum class CustomerEvent (val value: String) {
    CREATE("CREATE_CUSTOMER"),
    UPDATE("UPDATE_CUSTOMER"),
    RETRIEVE("RETRIVE_CUSTOMER"),
    DELETE("DELETE_CUSTOMER"),
    FIND_BY_MOBILE("FIND_CUSTOMER_BY_MOBILE")
}