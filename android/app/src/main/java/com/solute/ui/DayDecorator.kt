package com.solute.ui

import android.app.Activity
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.solute.R
import com.utilitykit.feature.employee.model.EmployeeAttendance

class CurrentDayDecorator(context: Activity?, currentDay: CalendarDay) : DayViewDecorator {
    private val presentDot: Drawable?
    private val absentDot: Drawable?
    var attendance: EmployeeAttendance? = null
    var myDay = currentDay
    override fun shouldDecorate(day: CalendarDay): Boolean {
        return day == myDay
    }

    override fun decorate(view: DayViewFacade) {
        if(attendance?.IsPresent == true){
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