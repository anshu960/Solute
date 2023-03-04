package com.solute.utility

import android.os.*
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import com.solute.app.App
import java.io.File
import java.io.File.separator
import java.io.FileOutputStream
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
class LocalFileManager
{
    
//    suspend fun fetchImage(fileUrl: String): String
//    {
//        return GlobalScope.async(Dispatchers.IO) {
//            val extension: String = fileUrl.substring(fileUrl.lastIndexOf("."))
//            val directory =
//                File(App.applicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + separator + "test_pictures")
//            if (!directory.exists()) {
//                directory.mkdirs()
//            }
//            val fileName =  "friendly_" + SystemClock.uptimeMillis().toString() + "." + extension
//            val file = File(directory, fileName)
//            var lastProgress : Int = 0
//            val filePath = file.absolutePath
//            val url = URL(fileUrl)
//            val connection = url.openConnection()
//            connection.connect()
//            val length = if (VERSION.SDK_INT >= VERSION_CODES.N) connection.contentLengthLong else connection.contentLength.toLong()
//            url.openStream().use { input ->
//                FileOutputStream(File(filePath)).use { output ->
//                    val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
//                    var bytesRead = input.read(buffer)
//                    var bytesCopied = 0L
//                    while (bytesRead >= 0) {
//                        output.write(buffer, 0, bytesRead)
//                        bytesCopied += bytesRead
////                        progress?.invoke(bytesCopied, length)
//                        bytesRead = input.read(buffer)
//                    }
//                    if(bytesRead == bytesCopied){
//                        return@use bytesCopied
//                    }
//                }
//            }
//        }.await()
//    }
    
    fun getLocalFileUrl(url:String,completion:(Double,String)->Unit){
//        val localUrl = Database.shared().retriveFileUrl(url)
//        if(localUrl !=  ""){
//            completion(100.0,localUrl)
//        }else{
//            downloadFile(url){progress,localUrl->
//                if(progress >= 100){
//                    Database.shared().storeFileUrl(localUrl,url)
//                }
//                completion(progress,localUrl)
//            }
//        }
    }
    
    fun getLocalFileUrl(url:String,completion:(String)->Unit){
//        val localUrl = Database.shared().retriveFileUrl(url)
//        if(localUrl !=  ""){
//            completion(localUrl)
//        }else{
//            downloadFile(url){profress,localUrl->
//                if(profress >= 100){
//                    Database.shared().storeFileUrl(localUrl,url)
//                    completion(localUrl)
//                }
//            }
//        }
       
    }
    
    
    
    fun downloadFile(url:String,completion:(Double,String)->Unit){
        val extension: String = url.substring(url.lastIndexOf("."))
        val directory =
            File(App.shared().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + separator + "test_pictures")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        val fileName =  "friendly_" + SystemClock.uptimeMillis().toString() + "." + extension
        val file = File(directory, fileName)
        var lastProgress : Int = 0
        val filePath = file.absolutePath
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val executor: ExecutorService = Executors.newSingleThreadExecutor()
            val handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    // Length is the total size of file and progress is how much file have been downloaded, now you can show progress according to this
                    val (progress, length) = msg.obj as Pair<Long, Long>
                    Log.v("mTAG", "Progress: $progress, Length: $length")
                    val percentage = 100.0 * progress / length
                    val numPrec = percentage.toInt()
                    if(numPrec > 0 && numPrec !=  lastProgress && numPrec % 10 == 0){
                        lastProgress = numPrec
                        completion(percentage,filePath)
                    }else{
                        lastProgress = numPrec
                    }
                }
            }
            executor.execute {
                //Background work here
                download(url, filePath) { progress, length ->
                    // handling the result on main thread
                    handler.sendMessage(handler.obtainMessage(0, progress to length))
                }
                handler.post {
                    //UI Thread work here
                }
            }
        
    }
    
    fun download(link: String, path: String, progress: ((Long, Long) -> Unit)? = null): Long {
        val url = URL(link)
        val connection = url.openConnection()
        connection.connect()
        val length = if (VERSION.SDK_INT >= VERSION_CODES.N) connection.contentLengthLong else connection.contentLength.toLong()
        url.openStream().use { input ->
            FileOutputStream(File(path)).use { output ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                var bytesRead = input.read(buffer)
                var bytesCopied = 0L
                while (bytesRead >= 0) {
                    output.write(buffer, 0, bytesRead)
                    bytesCopied += bytesRead
                    progress?.invoke(bytesCopied, length)
                    bytesRead = input.read(buffer)
                }
                
                return bytesCopied
            }
        }
    }
}

