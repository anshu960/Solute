package com.friendly.framework
import android.content.Context
import android.content.SharedPreferences
import org.json.JSONObject

class Defaults {
    private  val NAME = "com.utilitykit"
    private  val MODE = Context.MODE_PRIVATE
    private lateinit  var sharedPreference: SharedPreferences

    init {
        instance = this
    }

    fun setup(context: Context){
        sharedPreference = context.getSharedPreferences(NAME, MODE)
    }

    companion object {
        private var instance: Defaults? = null
        fun shared(): Defaults {
            if (instance != null) {
                return instance as Defaults
            } else {
                return Defaults()
            }
        }
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    fun store(key:String,boolean:Boolean){
        sharedPreference.edit{
            it.putBoolean(key,boolean)
        }
    }
    fun store(key:String,float:Float){
        sharedPreference.edit{
            it.putFloat(key,float)
        }
    }
    fun store(key:String,string:String){
        sharedPreference.edit{
            it.putString(key,string)
        }
    }
    fun store(key:String,json:JSONObject){
        sharedPreference.edit{
            it.putString(key,json.toString())
        }
    }
    fun store(key:String,int:Int){
        sharedPreference.edit{
            it.putInt(key,int)
        }
    }
    fun store(key:String,long:Long){
        sharedPreference.edit{
            it.putLong(key,long)
        }
    }

    fun boolean(key:String):Boolean{
        return sharedPreference.getBoolean(key,false)
    }
    fun string(key:String):String{
        return sharedPreference.getString(key,"")!!
    }
    fun json(key:String):JSONObject{
        try {
            val jsonString = sharedPreference.getString(key,"{}")
            return JSONObject(jsonString)
        }catch (e:Exception){
            return  JSONObject("{}")
        }
    }
    fun float(key:String):Float{
        return sharedPreference.getFloat(key, Float.MIN_VALUE)
    }
    fun int(key:String):Int{
        return sharedPreference.getInt(key, Int.MIN_VALUE)
    }
    fun long(key:String):Long{
        return sharedPreference.getLong(key, Long.MIN_VALUE)
    }

    fun remove(key: String){
        sharedPreference.edit {
            it.remove(key)
            it.commit()
        }
    }



}