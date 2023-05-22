package com.friendly.framework.rest

import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.constants.Server
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.customer.event.CustomerEvent
import com.friendly.framework.feature.customer.model.Customer
import com.friendly.framework.feature.customer.network.CustomerNetwork
import com.friendly.framework.feature.customer.network.CustomerNetworkInterface
import com.google.android.gms.common.api.Api
import kotlinx.coroutines.flow.callbackFlow
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
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class RestService {
    init {
        instance = this
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(Server.getRestApiEndPoint()) // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(getUnsafeOkHttpClient())
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    companion object{
        private var instance: RestService? = null
        fun shared() : RestService {
            if(instance != null){
                return instance as RestService
            }else{
                return RestService()
            }
        }
    }
    private fun getUnsafeOkHttpClient(): OkHttpClient? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts =
                arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate>,
                            authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
                )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory)
            builder.hostnameVerifier { hostname, session -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun <T> post(request: JSONObject,onResult: (JSONObject?) -> Unit,apiInterface:RestServiceInterface,api: Class<T>){
        val retrofit = buildService(apiInterface::class.java)
        val bodyRequest: RequestBody =
            RequestBody.create(MediaType.parse("application/json"), request.toString())
//        retrofit.api(bodyRequest).enqueue(
//            object : Callback<ResponseBody> {
//                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                    print(t.message)
//                    onResult(null)
//                }
//                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                    try {
//                        if (response.isSuccessful) {
//                            val obj = JSONObject(response.body()!!.string())
//                            onResult(obj)
//                        } else {
//                            val obj = JSONObject(response.errorBody()!!.string())
//                            onResult(null)
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                        onResult(null)
//                    }
//                }
//            }
//        )

    }
}