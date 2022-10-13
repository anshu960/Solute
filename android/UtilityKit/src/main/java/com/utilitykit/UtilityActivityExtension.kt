package com.utilitykit

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.utilitykit.socket.SocketService
import java.util.*


open class UtilityActivity :UtilityViewController(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        connectSocket()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

   public fun connectSocket(){
       SocketService.shared().currentActivity = this
       SocketService.shared().connect()
    }
    
    fun captureDate(callback:(String)->Unit){
        val c = Calendar.getInstance()
        val yr = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val display = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, monthOfYear, dayOfMonth ->
            var monthInput = (monthOfYear + 1).toString()
            if (monthInput.toInt() == 1) {
                monthInput = "Jan"
            } else if (monthInput.toInt() == 2) {
                monthInput = "Feb"
            } else if (monthInput.toInt() == 3) {
                monthInput = "March"
            } else if (monthInput.toInt() == 4) {
                monthInput = "April"
            } else if (monthInput.toInt() == 5) {
                monthInput = "May"
            } else if (monthInput.toInt() == 6) {
                monthInput = "June"
            } else if (monthInput.toInt() == 7) {
                monthInput = "July"
            } else if (monthInput.toInt() == 8) {
                monthInput = "Aug"
            } else if (monthInput.toInt() == 9) {
                monthInput = "Sept"
            } else if (monthInput.toInt() == 10) {
                monthInput = "Oct"
            } else if (monthInput.toInt() == 11) {
                monthInput = "Nov"
            } else if (monthInput.toInt() == 12) {
                monthInput = "Dec"
            }
            callback("$dayOfMonth $monthInput, $year")
        }, yr, month, day)
        display.datePicker.minDate = System.currentTimeMillis()
        display.show()
    }

    private val keyboardLayoutListener = OnGlobalLayoutListener {
        val heightDiff = rootLayout!!.rootView.height - rootLayout!!.height
        val contentViewTop = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        val broadcastManager = LocalBroadcastManager.getInstance(this@UtilityActivity)
        if (heightDiff <= contentViewTop) {
            onHideKeyboard()
            val intent = Intent("KeyboardWillHide")
            broadcastManager.sendBroadcast(intent)
        } else {
            val keyboardHeight = heightDiff - contentViewTop
            onShowKeyboard(keyboardHeight)
            val intent = Intent("KeyboardWillShow")
            intent.putExtra("KeyboardHeight", keyboardHeight)
            broadcastManager.sendBroadcast(intent)
        }
    }

    private var keyboardListenersAttached = false
    private var rootLayout: ViewGroup? = null

    protected open fun onShowKeyboard(keyboardHeight: Int) {}
    protected open fun onHideKeyboard() {}

    fun attachKeyboardListeners(id:Int) {
        if (keyboardListenersAttached) {
            return
        }
        rootLayout = findViewById<View>(id) as ViewGroup
        rootLayout!!.viewTreeObserver.addOnGlobalLayoutListener(keyboardLayoutListener)
        keyboardListenersAttached = true
    }



}