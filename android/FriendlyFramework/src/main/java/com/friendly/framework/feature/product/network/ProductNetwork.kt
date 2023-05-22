package com.friendly.framework.feature.product.network

import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.constants.Server
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.event.CustomerEvent
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.network.CustomerNetwork
import com.friendly.framework.feature.customer.network.CustomerNetworkInterface
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductNetwork {
    init {
        instance = this
    }
    companion object{
        private var instance: ProductNetwork? = null
        fun shared() : ProductNetwork {
            if(instance != null){
                return instance as ProductNetwork
            }else{
                return ProductNetwork()
            }
        }
    }
    fun retrieve(request:JSONObject, onResult: (JSONObject?) -> Unit){
        val retrofit = CustomerNetwork.shared().buildService(ProductNetworkInterface::class.java)
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), request.toString())
        retrofit.retrieve(bodyRequest).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    print(t.message)
                    onResult(null)
                }
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    try {
                        if (response.isSuccessful) {
                            val obj = JSONObject(response.body()!!.string())
                            if(obj.has(KeyConstant.payload)){
                                onResult(obj)
                            }else{
                                onResult(null)
                            }
                        } else {
                            onResult(null)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        onResult(null)
                    }
                }
            }
        )
    }
}