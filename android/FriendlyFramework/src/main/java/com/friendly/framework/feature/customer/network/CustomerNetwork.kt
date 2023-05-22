package com.friendly.framework.feature.customer.network

import com.friendly.framework.constants.Server
import com.friendly.framework.feature.customer.event.CustomerEvent
import com.friendly.framework.feature.customer.handler.CustomerHandler
import com.friendly.framework.socket.SocketService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class CustomerNetwork {
    init {
        instance = this
    }
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(Server.getRestApiEndPoint()) // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(getUnsafeOkHttpClient())
        .build()
    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    companion object{
        private var instance: CustomerNetwork? = null
        fun shared() : CustomerNetwork {
            if(instance != null){
                return instance as CustomerNetwork
            }else{
                return CustomerNetwork()
            }
        }
    }
    fun connectScoket(){
        SocketService.shared().mSocket?.on(CustomerEvent.CREATE.value, CustomerHandler.shared().onCreateCustomer)
        SocketService.shared().mSocket?.on(CustomerEvent.RETRIEVE.value, CustomerHandler.shared().onFetchAllCustomer)
    }

    fun disconnectSocket(){
        SocketService.shared().mSocket?.off(CustomerEvent.CREATE.value)
        SocketService.shared().mSocket?.off(CustomerEvent.RETRIEVE.value)
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
}