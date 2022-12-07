package com.solute.ui

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.solute.R

class CurrentDayDecorator(context: Activity?) : DayViewDecorator {
    private val presentDot: Drawable?
    private val absentDot: Drawable?
    var isPresent : ((currentDay: String)->Boolean?)? = null
    var status : Boolean? = null
    override fun shouldDecorate(day: CalendarDay): Boolean {
        status = this.isPresent?.let { it(day.date.toString()) }

        return status != null
    }

    override fun decorate(view: DayViewFacade) {
        if(status != null && status == true){
            if (presentDot != null) {
                view.setSelectionDrawable(presentDot)
            }
        }else{
            if (absentDot != null) {
                view.setSelectionDrawable(absentDot)
            }
        }
    }

    init {
        // You can set background for Decorator via drawable here
        presentDot = ContextCompat.getDrawable(context!!, R.drawable.calender_dot_present)
        absentDot = ContextCompat.getDrawable(context!!, R.drawable.calender_dot_absent)
    }
}