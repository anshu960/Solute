package com.friendly.framework.network

import com.friendly.framework.constants.KeyConstant
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiResponseCallBack {
    val errorMessage = "Oops! Something went wrong,Please try after some time"
    var onResult: ((Any?) -> Unit)? = null
    var onError: ((String?) -> Unit)? = null
    val callback = object : Callback<ResponseBody>  {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            try {
                if (response.isSuccessful) {
                    val responseString = response.body()?.string()
                    val obj = JSONObject(responseString)
                    if(obj.has(KeyConstant.payload)){
                        if(obj.get(KeyConstant.payload) is JSONObject){
                            onResult?.let { it(obj.getJSONObject(KeyConstant.payload)) }
                        }else if(obj.get(KeyConstant.payload) is JSONArray){
                            onResult?.let { it(obj.getJSONObject(KeyConstant.payload)) }
                        }else{
                            onError?.let { it(errorMessage) }
                        }
                    }else{
                        onError?.let { it(errorMessage) }
                    }
                } else {
                    onError?.let { it(errorMessage) }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError?.let { it(errorMessage) }
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            onError?.let { it(errorMessage) }
        }
    }
}