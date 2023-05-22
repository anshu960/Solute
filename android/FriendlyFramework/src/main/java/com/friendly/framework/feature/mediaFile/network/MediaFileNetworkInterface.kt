package com.friendly.framework.feature.mediaFile.network

import com.friendly.framework.feature.mediaFile.event.MediaFileEvent
import com.friendly.framework.feature.product.event.ProductEvent
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MediaFileNetworkInterface{
    @Headers("Content-Type:application/json")
    @POST(MediaFileEvent.CREATE)
    fun create(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST(MediaFileEvent.UPDATE)
    fun update(@Body params: RequestBody): Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST(MediaFileEvent.RETRIEVE)
    fun retrieve(@Body params: RequestBody): Call<ResponseBody>
}