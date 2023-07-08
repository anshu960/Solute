package com.friendly.framework.feature.business.network

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NetworkInterface {
    @Headers("Content-Type:application/json")
    @POST("CREATE_BUSINESS")
    fun createBusiness(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("RETRIEVE_BUSINESS")
    fun retrieveBusiness(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("UPDATE_BUSINESS")
    fun updateBusiness(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("UPDATE_BUSINESS_ADDRESS")
    fun updateAddress(@Body params: RequestBody): Call<ResponseBody>
}