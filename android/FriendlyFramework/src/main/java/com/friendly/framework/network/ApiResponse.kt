package com.friendly.framework.network

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

open class ApiResponse {

//    TAG:"PRODUCT_INVENTORY",
//    MESSAGE_CALLED:"CALLED",
//    MESSAGE_ERROR:"ERROR",
//    MESSAGE_MISSING_FIELDS:"MESSAGE_MISSING_FIELDS",
//    MESSAGE_SUCCESS:"SUCCESS",
//    response: {
//        Payload: [],
//        Status:true,
//        ResponseCode:200,
//        Message: "INITIATED",
//        Errror: "",
//    }

    @SerializedName("TAG")
    var tag: String = "ApiResponseTag"

    @SerializedName("MESSAGE_MISSING_FIELDS")
    var missingField: String = ""

    @SerializedName("MESSAGE_ERROR")
    var error: String = ""

    @SerializedName("MESSAGE_SUCCESS")
    var successMessage: String = ""

    @SerializedName("Message")
    var message:String = ""

    @SerializedName("Payload")
    var payload:Any = JSONObject()


}