package com.friendly.framework
import android.util.Base64
import com.friendly.framework.dataclass.Conversation
import com.friendly.framework.dataclass.Task
import com.friendly.framework.socket.SocketService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class Encryption {
    
  suspend  fun encrypt(input: String,conversation: Conversation):String =
      withContext(Dispatchers.Default) {
          var secretKey = conversation._id + conversation.ownerUserID
          try
          {
              val ivParameterSpec = IvParameterSpec(Base64.decode(SocketService.shared().iv, Base64.DEFAULT))
              val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
              val spec = PBEKeySpec(secretKey.toCharArray(), Base64.decode(SocketService.shared().salt, Base64.DEFAULT), 10000, 256)
              val tmp = factory.generateSecret(spec)
              val secretKey = SecretKeySpec(tmp.encoded, "AES")
              val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
              cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
              val encrypted = Base64.encodeToString(cipher.doFinal(input.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
              return@withContext encrypted
          } catch (e: Exception)
          {
              println("Error while encrypting: $e")
              return@withContext input
          }
      }

    suspend  fun encrypt(input: String,task: Task):String =
        withContext(Dispatchers.Default) {
            var secretKey = task._id + task._id
            try
            {
                val ivParameterSpec = IvParameterSpec(Base64.decode(SocketService.shared().iv, Base64.DEFAULT))
                val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                val spec = PBEKeySpec(secretKey.toCharArray(), Base64.decode(SocketService.shared().salt, Base64.DEFAULT), 10000, 256)
                val tmp = factory.generateSecret(spec)
                val secretKey = SecretKeySpec(tmp.encoded, "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
                val encrypted = Base64.encodeToString(cipher.doFinal(input.toByteArray(Charsets.UTF_8)), Base64.DEFAULT)
                return@withContext encrypted
            } catch (e: Exception)
            {
                println("Error while encrypting: $e")
                return@withContext input
            }
        }
//
    
    suspend fun decrypt(input : String,conversation:Conversation): String =
        withContext(Dispatchers.Default) {
            var secretKey = conversation._id + conversation.ownerUserID
            try
            {
                val ivParameterSpec =  IvParameterSpec(Base64.decode(SocketService.shared().iv, Base64.DEFAULT))
                val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                val spec =  PBEKeySpec(secretKey.toCharArray(), Base64.decode(SocketService.shared().salt, Base64.DEFAULT), 10000, 256)
                val tmp = factory.generateSecret(spec);
                val secretKey =  SecretKeySpec(tmp.encoded, "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
                val decrypted = String(cipher.doFinal(Base64.decode(input, Base64.DEFAULT)))
                return@withContext decrypted
            }
            catch (e : Exception) {
                println("Error while decrypting: $e");
                return@withContext input
            }
            
        }
    
    suspend fun decrypt(input : String,task: Task): String =
        withContext(Dispatchers.Default) {
            var secretKey = task._id + task._id
            try
            {
                val ivParameterSpec =  IvParameterSpec(Base64.decode(SocketService.shared().iv, Base64.DEFAULT))
                val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                val spec =  PBEKeySpec(secretKey.toCharArray(), Base64.decode(SocketService.shared().salt, Base64.DEFAULT), 10000, 256)
                val tmp = factory.generateSecret(spec);
                val secretKey =  SecretKeySpec(tmp.encoded, "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
                cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
                val decrypted = String(cipher.doFinal(Base64.decode(input, Base64.DEFAULT)))
                return@withContext decrypted
            }
            catch (e : Exception) {
                println("Error while decrypting: $e");
                return@withContext input
            }
            
        }
    
     fun decryptMessage(input : String,conversation:Conversation):String{
         var secretKey = conversation._id + conversation.ownerUserID
         try
         {
             val ivParameterSpec =  IvParameterSpec(Base64.decode(SocketService.shared().iv, Base64.DEFAULT))
             val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
             val spec =  PBEKeySpec(secretKey.toCharArray(), Base64.decode(SocketService.shared().salt, Base64.DEFAULT), 10000, 256)
             val tmp = factory.generateSecret(spec);
             val secretKey =  SecretKeySpec(tmp.encoded, "AES")
             val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
             cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
             val decrypted = String(cipher.doFinal(Base64.decode(input, Base64.DEFAULT)))
             return decrypted
         }
         catch (e : Exception) {
             println("Error while decrypting: $e");
             return input
         }
     }
    
     
}