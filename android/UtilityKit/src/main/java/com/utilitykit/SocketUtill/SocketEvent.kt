package com.utilitykit.SocketUtill

enum class SocketEvent(val value: String)
{
    connect("CONNECT"),
    connection("CONNECTION"),
    disconnect("DISCONNECT"),
    joinRoom("JOIN_ROOM"),
    getEncryptionKeys("GET_ENCRYPTION_KEYS"),
    isOnline("IS_ONLINE"),
    onEvent("ON_EVENT"),
    authenticate("AUHTENTICATE"),
    updateProfile("UPDATE_PROFILE"),
    sendMessage("SEND_MESSAGE"),
    sendTaskMessage("SEND_TASK_MESSAGE"),
    getAllMessage("GET_ALL_MESSAGE"),
    onMessage("ON_MESSAGE"),
    onTaskMessage("ON_TASK_MESSAGE"),
    updateMessage("UPDATE_MESSAGE"),
    messageReceived("MESSAGE_RECEIVED"),
    messageRead("MESSAGE_READ"),
    typing("TYPING"),
    previousChats("PREVIOUS_CHATS"),
    onPreviousChats("ON_PREVIOUS_CHATS"),
    newChats("NEW_CHATS"),
    updateDeliveryStatus("UPDATE_DELIVERY_STATUS"),
    updateReadStatus("UPDATE_READ_STATUS"),
    findUsers("FIND_USERS"),
    getAllConversation("GET_ALL_CONVERSATION"),
    createConversation("CREATE_CONVERSATION"),
    createGroup("CREATE_GROUP"),
    deleteGroup("DELETE_GROUP"),
    updateGroup("UPDATE_GROUP"),
    updateGroupName("UPDATE_GROUP_NAME"),
    updateGroupDescription("UPDATE_GROUP_DESCRIPTION"),
    updateGroupImage("UPDATE_GROUP_IMAGE"),
    findCompanyByName("FIND_COMPANY_BY_NAME"),
    findDesignationByName("FIND_DESIGNATION_BY_NAME"),
    findTechnologyByName("FIND_TECHNOLOGY_BY_NAME"),
    createExperience("CREATE_EXPERIENCE"),
    getAllExperience("GET_ALL_EXPERIENCE"),
    updateContacts("UPDATE_CONTACTS"),
    retriveContacts("RETRIVE_CONTACTS"),
    createTask("CREATE_TASK"),
    updateTask("UPDATE_TASK"),
    updateTaskStatus("UPDATE_TASK_STATUS"),
    updateTaskPriority("UPDATE_TASK_PRIORITY"),
    getAllTask("GET_ALL_TASK"),
    getAllTaskMessage("GET_ALL_TASK_MESSAGE"),
    attachDocumentTask("ATTACH_DOCUMENT_TASK"),
    getAllAttachedDocumentTask("GET_ALL_ATTACH_DOCUMENT_TASK"),
    findCustomerByMobile("FIND_CUSTOMER_BY_MOBILE"),
    //Solute
    JOIN_WHATSAPP_ROOM("JOIN_WHATSAPP_ROOM"),
    RETRIVE_PLATFORM_STATISTICS("RETRIVE_PLATFORM_STATISTICS"),
    WHATSAPP_QR_RECEIVED("WHATSAPP_QR_RECEIVED"),
    IS_ONLINE("IS_ONLINE"),
    LOAD_TESTING("LOAD_TESTING"),
    FIND_USERS("FIND_USERS"),
    AUHTENTICATE("AUHTENTICATE"),
    REGISTER_NEW_USER("REGISTER_NEW_USER"),
    FIND_USER("FIND_USER"),
    RETRIEVE_USER_NOTIFICATION("RETRIEVE_USER_NOTIFICATION"),
    CREATE_BUSINESS("CREATE_BUSINESS"),
    RETRIVE_BUSINESS("RETRIVE_BUSINESS"),
    UPDATE_BUSINESS("UPDATE_BUSINESS"),
    UPDATE_BUSINESS_PROFILE_IMAGE("UPDATE_BUSINESS_PROFILE_IMAGE"),
    UPDATE_BUSINESS_IMAGE("UPDATE_BUSINESS_IMAGE"),
    DELETE_BUSINESS("DELETE_BUSINESS"),
    CREATE_BUSINESS_TYPE("CREATE_BUSINESS_TYPE"),
    RETRIVE_BUSINESS_TYPE("RETRIVE_BUSINESS_TYPE"),
    UPDATE_BUSINESS_TYPE("UPDATE_BUSINESS_TYPE"),
    DELETE_BUSINESS_TYPE("DELETE_BUSINESS_TYPE"),
    RETRIVE_ROLE_TYPE("RETRIVE_ROLE_TYPE"),
    CREATE_CUSTOMER("CREATE_CUSTOMER"),
    UPDATE_CUSTOMER("UPDATE_CUSTOMER"),
    RETRIVE_CUSTOMER("RETRIVE_CUSTOMER"),
    FIND_CUSTOMER_BY_MOBILE("FIND_CUSTOMER_BY_MOBILE"),
    DELETE_CUSTOMER("DELETE_CUSTOMER"),
    CREATE_EMPLOYEE("CREATE_EMPLOYEE"),
    UPDATE_EMPLOYEE("UPDATE_EMPLOYEE"),
    RETRIVE_EMPLOYEE("RETRIVE_EMPLOYEE"),
    DELETE_EMPLOYEE("DELETE_EMPLOYEE"),
    CREATE_EMPLOYEE_CONNECTION_REQUEST("CREATE_EMPLOYEE_CONNECTION_REQUEST"),
    ACCEPT_EMPLOYEE_CONNECTION_REQUEST("ACCEPT_EMPLOYEE_CONNECTION_REQUEST"),
    DELETE_EMPLOYEE_CONNECTION_REQUEST("DELETE_EMPLOYEE_CONNECTION_REQUEST"),
    ADD_EMPLOYEE_PAYMENT("ADD_EMPLOYEE_PAYMENT"),
    RETRIVE_EMPLOYEE_PAYMENT("RETRIVE_EMPLOYEE_PAYMENT"),
    ADD_EMPLOYEE_ATTENDACE("ADD_EMPLOYEE_ATTENDACE"),
    RETRIVE_EMPLOYEE_ATTENDANCE("RETRIVE_EMPLOYEE_ATTENDACE"),
    CREATE_PRODUCT("CREATE_PRODUCT"),
    UPDATE_PRODUCT("UPDATE_PRODUCT"),
    UPDATE_PRODUCT_IMAGE("UPDATE_PRODUCT_IMAGE"),
    RETRIVE_PRODUCT("RETRIVE_PRODUCT"),
    CREATE_PRODUCT_PRICE("CREATE_PRODUCT_PRICE"),
    UPDATE_PRODUCT_PRICE("UPDATE_PRODUCT_PRICE"),
    DELETE_PRODUCT("DELETE_PRODUCT"),
    CREATE_PRODUCT_CATEGORY("CREATE_PRODUCT_CATEGORY"),
    UPDATE_PRODUCT_CATEGORY("UPDATE_PRODUCT_CATEGORY"),
    UPDATE_PRODUCT__CATEGORY_IMAGE("UPDATE_PRODUCT__CATEGORY_IMAGE"),
    RETRIVE_PRODUCT_CATEGORY("RETRIVE_PRODUCT_CATEGORY"),
    DELETE_PRODUCT_CATEGORY("DELETE_PRODUCT_CATEGORY"),
    UPDATE_PRODUCT_SUB_CATEGORY("UPDATE_PRODUCT_SUB_CATEGORY"),
    UPDATE_PRODUCT_SUB_CATEGORY_IMAGE("UPDATE_PRODUCT_SUB_CATEGORY_IMAGE"),
    RETRIVE_PRODUCT_SUB_CATEGORY("RETRIVE_PRODUCT_SUB_CATEGORY"),
    CREATE_PRODUCT_SUB_CATEGORY("CREATE_PRODUCT_SUB_CATEGORY"),
    DELETE_PRODUCT_SUB_CATEGORY("DELETE_PRODUCT_SUB_CATEGORY"),
    CREATE_STOCK_ENTRY("CREATE_STOCK_ENTRY"),
    RETRIVE_STOCK_ENTRY("RETRIVE_STOCK_ENTRY"),
    CREATE_SALE("CREATE_SALE"),
    RETRIVE_SALE("RETRIVE_SALE"),
    RETRIVE_SALES("RETRIVE_SALES"),
    UPDATE_SALE("UPDATE_SALE"),
    DELETE_SALE("DELETE_SALE"),
    CREATE_BUSINESS_SALE("CREATE_BUSINESS_SALE"),
    RETRIVE_BUSINESS_SALE("RETRIVE_BUSINESS_SALE"),
    UPDATE_BUSINESS_SALE("UPDATE_BUSINESS_SALE"),
    DELETE_BUSINESS_SALE("DELETE_BUSINESS_SALE"),
    UPDATE_USER_LOCATION("UPDATE_USER_LOCATION"),
    ADD_CUSTOMER_PAYMENT("ADD_CUSTOMER_PAYMENT"),
    RETRIVE_CUSTOMER_PAYMENT("RETRIVE_CUSTOMER_PAYMENT"),
    GENERATE_CUSTOMER_INVOICE("GENERATE_CUSTOMER_INVOICE"),
    RETRIVE_INVOICE("RETRIVE_INVOICE"),
    RETRIVE_SINGLE_INVOICE("RETRIVE_SINGLE_INVOICE"),
    CREATE_HSN("CREATE_HSN"),
    UPDATE_HSN("UPDATE_HSN"),
    RETRIVE_HSN("RETRIVE_HSN"),
    SHIPMENT_CREATE("SHIPMENT_CREATE"),
    SHIPMENT_UPDATE("SHIPMENT_UPDATE"),
    SHIPMENT_DELETE("SHIPMENT_UPDATE"),
    SHIPMENT_RETRIVE("SHIPMENT_RETRIVE"),
    SHIPMENT_STATUS_CREATE("SHIPMENT_STATUS_CREATE"),
    SHIPMENT_STATUS_RETRIVE("SHIPMENT_STATUS_RETRIVE"),
    BUSINESS_ANALYTICS_BUSINESS_SALE("BUSINESS_ANALYTICS_BUSINESS_SALE"),
    CREATE_PRODUCT_STOCK("CREATE_PRODUCT_STOCK"),
    RETRIVE_ALL_STOCK_ENTRY("RETRIVE_ALL_STOCK_ENTRY"),
    CREATE_SUBSCRIPTION_ORDER("CREATE_SUBSCRIPTION_ORDER"),
    UPDATE_SUBSCRIPTION_PAYMENT_RESPONSE("UPDATE_SUBSCRIPTION_PAYMENT_RESPONSE"),
    RETRIVE_ACTIVE_SUBSCRIPTION_PLAN("RETRIVE_ACTIVE_SUBSCRIPTION_PLAN"),
}