package com.solute.app

import com.solute.navigation.AppNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToastService {
    init {
        instance = this
    }
    companion object{
        private var instance: ToastService? = null
        fun shared() : ToastService {
            if(instance != null){
                return instance as ToastService
            }else{
                return ToastService()
            }
        }
    }
    fun toast(msg:String){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            App.shared().mainActivity?.toast(msg)
        }
    }
    fun toastLong(msg:String){
        CoroutineScope(Job() + Dispatchers.Main).launch {
            App.shared().mainActivity?.toastLong(msg)
        }
    }
}