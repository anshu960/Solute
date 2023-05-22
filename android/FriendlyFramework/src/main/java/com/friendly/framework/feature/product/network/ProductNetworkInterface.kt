package com.friendly.framework.feature.product.network

import com.friendly.framework.feature.product.event.ProductEvent
import com.friendly.framework.rest.RestServiceInterface
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ProductNetworkInterface {
    @Headers("Content-Type:application/json")
    @POST(ProductEvent.CREATE)
    fun create(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST(ProductEvent.UPDATE)
    fun update(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST(ProductEvent.RETRIEVE)
    fun retrieve(@Body params: RequestBody): Call<ResponseBody>
}