package com.friendly.framework.feature.customer.network

import com.friendly.framework.feature.customer.event.CustomerEvent
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CustomerNetworkInterface {
    @Headers("Content-Type:application/json")
    @POST("FIND_CUSTOMER_BY_MOBILE")
    fun findCustomerByMobile(@Body params: RequestBody): Call<ResponseBody>
}