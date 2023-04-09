package com.solute.ui.business.dashboard

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import com.friendly.framework.feature.sale.handler.SaleHandler
import com.mahmoud.composecharts.barchart.BarChart
import com.mahmoud.composecharts.barchart.BarChartEntity
import com.solute.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessDashboardFragment : Fragment() {


    var todayButton : TextView? = null
    var weekButton : TextView? = null
    var monthButton : TextView? = null
    var yearButton : TextView? = null
    var todayBtnCard : CardView? = null
    var weekBtnCard : CardView? = null
    var monthBtnCard : CardView? = null
    var yearBtnCard : CardView? = null

    var saleQuantityComposeView : ComposeView? = null
    var saleValueComposeView : ComposeView? = null
    var saleCustomerComposeView : ComposeView? = null

    var saleQuantityValueTV : TextView? = null
    var saleTotalValueTV : TextView? = null
//    val barChartData = DataUtils.getBarChartData(2, 100)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_dashboard, container, false)
        todayButton = view.findViewById(R.id.today_btn)
        weekButton = view.findViewById(R.id.week_btn)
        monthButton = view.findViewById(R.id.month_btn)
        yearButton = view.findViewById(R.id.year_btn)
        todayBtnCard = view.findViewById(R.id.card_btn_today)
        weekBtnCard = view.findViewById(R.id.card_btn_week)
        monthBtnCard = view.findViewById(R.id.card_btn_month)
        yearBtnCard = view.findViewById(R.id.card_btn_year)
        saleQuantityComposeView = view.findViewById(R.id.sale_quantity_cv)
        saleValueComposeView = view.findViewById(R.id.sale_value_cv)
        saleCustomerComposeView= view.findViewById(R.id.sale_customer_cv)
        saleQuantityComposeView = view.findViewById(R.id.sale_quantity_cv)
        todayBtnCard?.setOnClickListener {onClickToday()}
        weekBtnCard?.setOnClickListener {onClickWeek()}
        monthBtnCard?.setOnClickListener {onClickMonth()}
        yearBtnCard?.setOnClickListener {onClickYear()}
        saleQuantityValueTV = view.findViewById(R.id.sale_quantity_tv)
        saleTotalValueTV = view.findViewById(R.id.sale_value_tv)
        saleTotalValueTV
        onClickToday()
        return view
    }

    fun onClickToday(){
        todayBtnCard?.setCardBackgroundColor(resources.getColor(R.color.colorThemePrimary))
        todayButton?.setTextColor(resources.getColor(R.color.white))
        weekBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        weekButton?.setTextColor(resources.getColor(R.color.btn_title))
        monthBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        monthButton?.setTextColor(resources.getColor(R.color.btn_title))
        yearBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        yearButton?.setTextColor(resources.getColor(R.color.btn_title))
        SaleHandler.shared().getTodaySaleQuantityData { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f, (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), MaxValue.toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleQuantityComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleQuantityValueTV?.text = totalValue.toString()
            }
        }

        SaleHandler.shared().getTodaySaleValueData { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleValueComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleTotalValueTV?.text = "₹ " + totalValue.toString()
            }
        }
    }
    fun onClickWeek(){
        todayBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        todayButton?.setTextColor(resources.getColor(R.color.btn_title))
        weekBtnCard?.setCardBackgroundColor(resources.getColor(R.color.colorThemePrimary))
        weekButton?.setTextColor(resources.getColor(R.color.white))
        monthBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        monthButton?.setTextColor(resources.getColor(R.color.btn_title))
        yearBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        yearButton?.setTextColor(resources.getColor(R.color.btn_title))

        SaleHandler.shared().getWeekSaleQuantityData { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleQuantityComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleTotalValueTV?.text = "₹ " + totalValue.toString()
            }
        }

        SaleHandler.shared().getWeekSaleValueData { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleValueComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleTotalValueTV?.text = "₹ " + totalValue.toString()
            }
        }
    }
    fun onClickMonth(){
        todayBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        todayButton?.setTextColor(resources.getColor(R.color.btn_title))
        weekBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        weekButton?.setTextColor(resources.getColor(R.color.btn_title))
        monthBtnCard?.setCardBackgroundColor(resources.getColor(R.color.colorThemePrimary))
        monthButton?.setTextColor(resources.getColor(R.color.white))
        yearBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        yearButton?.setTextColor(resources.getColor(R.color.btn_title))
        SaleHandler.shared().getLastMonthSaleQuantityData { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleQuantityComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleQuantityValueTV?.text = totalValue.toString()
            }
        }

        SaleHandler.shared().getLastMonthSaleValueData() { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleValueComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleTotalValueTV?.text = "₹ " + totalValue.toString()
            }
        }
    }
    fun onClickYear(){
        todayBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        todayButton?.setTextColor(resources.getColor(R.color.btn_title))
        weekBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        weekButton?.setTextColor(resources.getColor(R.color.btn_title))
        monthBtnCard?.setCardBackgroundColor(resources.getColor(R.color.stepper_background))
        monthButton?.setTextColor(resources.getColor(R.color.btn_title))
        yearBtnCard?.setCardBackgroundColor(resources.getColor(R.color.colorThemePrimary))
        yearButton?.setTextColor(resources.getColor(R.color.white))
        SaleHandler.shared().getYearSaleQuantityData() { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleQuantityComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleQuantityValueTV?.text = totalValue.toString()
            }
        }

        SaleHandler.shared().getYearSaleValueData() { ints, MaxValue ->
            var totalValue = 0
            CoroutineScope(Job() + Dispatchers.Main).launch {
                val verticalAxisValues = listOf(0.0f,  (MaxValue/ 5).toFloat(), (MaxValue/4).toFloat(), (MaxValue/3).toFloat(), (MaxValue/2).toFloat(), (MaxValue).toFloat())
                var barData = arrayListOf<BarChartEntity>()
                var index = 0
                ints.forEach {
                    totalValue += it
                    when (index) {
                        0 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        1 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFFC32A33), it.toString()))
                        2 -> barData.add(BarChartEntity(it.toFloat(), Color.Blue, it.toString()))
                        3 -> barData.add(BarChartEntity(it.toFloat(), Color.Cyan, it.toString()))
                        4 -> barData.add(BarChartEntity(it.toFloat(), Color.Magenta, it.toString()))
                        5 -> barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        else -> { // Note the block
                            barData.add(BarChartEntity(it.toFloat(), Color(0xFF618A32), it.toString()))
                        }
                    }
                    index+=1
                }
                saleValueComposeView?.setContent {
                    BarChart(
                        barChartData = barData,
                        verticalAxisValues = verticalAxisValues
                    )
                }
                saleTotalValueTV?.text = "₹ " + totalValue.toString()
            }
        }
    }

    fun prepareChart(){

    }
}