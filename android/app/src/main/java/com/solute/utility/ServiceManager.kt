//package com.friendly.utility
//
//import android.content.ContentValues
//import android.os.AsyncTask
//import android.util.Log
//import com.friendly.constants.Server
//import com.utilitykit.Constants.Constants
//import com.utilitykit.Constants.Key
//import com.utilitykit.json
//import org.json.JSONObject
//import java.io.*
//import java.net.HttpURLConnection
//import java.net.URL
//import java.nio.charset.StandardCharsets
//import javax.net.ssl.HttpsURLConnection
//
//class ServiceManager: AsyncTask<String, Void, String>(){
//    var requestMsg = ""
//    var responseMsg = ""
//    var serverURL = ""
//    var response = Response(404,Result.Unknown,"Somehting Went Wrong")
//    var onComplete : (Response)->Unit = fun(response : Response){
//        Log.d("response",""+response)
//    }
//
//    var header = ContentValues()
//
//    fun config(server:String){
//        Server.host = server + ":" + Server.port + "/"
//    }
//
//    override fun onPostExecute(httpResponseMsg: String) {
//        super.onPostExecute(httpResponseMsg)
//        this.onComplete(this.response)
//    }
//
//    override fun doInBackground(vararg params: String?): String  {
//        try{
//            Log.d("Service Call","URL = "+this.serverURL)
//            Log.d("Service Call","Header = "+this.header.json())
//            Log.d("Service Call","Request = "+this.requestMsg)
//
//            val url = URL(serverURL)
//            var connection = url.openConnection() as HttpURLConnection
//            if(serverURL.contains("https")){
//                var connection = url.openConnection() as HttpsURLConnection
//            }
//            connection.requestMethod = "POST"
//            connection.connectTimeout = 300000
//            connection.doOutput = true
//
//            val postData: ByteArray = requestMsg.toByteArray(StandardCharsets.UTF_8)
//            connection.setRequestProperty("charset", "utf-8")
//            connection.setRequestProperty("Content-lenght", postData.size.toString())
//            connection.setRequestProperty("Content-Type", "application/json")
//            for (item in this.header.valueSet()) {
//                if(item.value is String){
//                    connection.setRequestProperty(item.key,item.value as String)
//                }
//            }
//            try {
//                val outputStream = DataOutputStream(connection.outputStream)
//                outputStream.write(postData)
//                outputStream.flush()
//            } catch (exception: Exception) {
//                exception.printStackTrace()
//                Log.d("Inside flush","failed")
//            }
//            try {
//                this.response.responseCode = connection.responseCode
//                val inputStream = DataInputStream(connection.inputStream)
//                val reader = BufferedReader(InputStreamReader(inputStream))
//                val output = reader.readLine()
//                Log.d("Response Output",output)
//                this.response.responseMessage = connection.responseMessage
//                this.response.responseValue = output
//                Log.d("Response Value",output)
//                val json = JSONObject(output)
//                if(json.length() > 0){
//                    this.response.json = json
//                }
//            } catch (exception: Exception) {
//                Log.d("Printing Log Trace","Line 71")
//                exception.printStackTrace()
//            }
//            connection.disconnect()
//        }
//        catch (ex: Exception){
//            Log.d("Printing Log Trace","Line 79")
//            ex.printStackTrace()
//        }
//        return "final result"
//    }
//
//    fun makeServiceCall(api:String,request:String,completion:(Response)->Unit){
//        this.onComplete = completion
//        this.serverURL = Server.host + api
//        this.requestMsg = request
//        this.execute()
//    }
//    fun makeServiceCall(api:String, request: JSONObject, completion:(Response)->Unit){
//        this.onComplete = completion
//        this.serverURL = Server.host + api
//        this.requestMsg = request.toString()
//        this.execute()
//    }
//
//    fun makeServiceCall(url:String, request: JSONObject, header: ContentValues, completion:(Response)->Unit){
//        this.onComplete = completion
//        this.serverURL = url
//        this.requestMsg = request.toString()
//        this.header = header
//        this.execute()
//    }
//}
//
//class Response(code: Int,result: Result,value: String){
//    var responseMessage : String
//    var responseCode : Int
//    var responseResult : Result
//    var responseValue : String
//    var json : JSONObject
//    init{
//        this.responseMessage = Constants.defaultErrorMessage
//        this.responseCode = code
//        this.responseResult = result
//        this.responseValue = value
//        try{
//            this.json = JSONObject(value)
//            this.responseMessage = json.getString(KeyConstant.message)
//        }catch (e : java.lang.Exception){
//            var jsonObject = JSONObject()
//            jsonObject.put(KeyConstant.message, Constants.defaultErrorMessage)
//            this.json = jsonObject
//
//        }
//
//    }
//}
//enum class Result{
//    Success,Failed,TimeOut,Error,Unknown
//}
