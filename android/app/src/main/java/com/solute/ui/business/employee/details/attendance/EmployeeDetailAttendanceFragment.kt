package com.solute.ui.business.employee.details.attendance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.Switch
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.solute.R
import com.solute.ui.CurrentDayDecorator
import com.solute.ui.business.BusinessActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.employee.handler.EmployeeHandler


/**
 * A simple [Fragment] subclass.
 * Use the [EmployeeDetailAttendanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EmployeeDetailAttendanceFragment : Fragment() {

    val activity = BusinessHandler.shared().activity as? BusinessActivity
    var calenderView : MaterialCalendarView? = null
    var presentSwitch : Switch? = null
    var hours : TextInputEditText? = null
    var comment : TextInputEditText? = null
    var saveButton : Button? = null
    val employee = EmployeeHandler.shared().repository.employee.value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_employee_detail_attendance, container, false)
        calenderView = view.findViewById(R.id.employee_details_attendance_calender_view)
        presentSwitch = view.findViewById(R.id.employee_details_attendance_present_switch)
        hours = view.findViewById(R.id.employee_details_attendance_hours_tiet)
        comment = view.findViewById(R.id.employee_details_attendance_comment_tiet)
        saveButton = view.findViewById(R.id.employee_details_attendance_save_btn)
        saveButton?.setOnClickListener { onClickSave() }
        presentSwitch?.setOnCheckedChangeListener { compoundButton, b ->

        }
        calenderView?.keepScreenOn = true
        calenderView?.addOnLayoutChangeListener { view, i, i2, i3, i4, i5, i6, i7, i8 ->
            onCalenderSwap()
        }
        calenderView?.setOnMonthChangedListener { widget, date ->
            activity?.toast("Start ${date}  ${calenderView?.selectedDate}")
        }
        return view
    }

    fun onCalenderSwap(){
        addDecorator()
    }
    fun addDecorator(){
        val mydate= CalendarDay.from(2022,  12, 31) // year, month, date
        val decorator = CurrentDayDecorator(activity, mydate)
        calenderView?.addDecorator(decorator)
    }

    fun onClickSave(){
        var hoursSpent = 0F
        val isPresent = presentSwitch!!.isActivated
        if(hours?.text.isNullOrEmpty() && presentSwitch?.isActivated == false){
            activity?.toast("Pleased enter hours spent and you have marked present")
            return
        }else if(!hours?.text.isNullOrEmpty()){
            hoursSpent =  hours?.text.toString().toFloat()
        }
        val comment = comment?.text.toString()
        if(comment.isEmpty() && !isPresent){
            activity?.toast("Pleased enter reason for not being present")
        }
        if (employee != null) {
            EmployeeHandler.shared().onCreateNewAttendance={
                if(!it.Id.isEmpty()){
                    activity?.runOnUiThread {
                        activity?.toast("Attendance entry saved")
                    }
                }
            }
            EmployeeHandler.shared().viewModel?.createNewAttendance(employee,calenderView!!.selectedDate.toString(),hoursSpent,comment)
        }
    }


}