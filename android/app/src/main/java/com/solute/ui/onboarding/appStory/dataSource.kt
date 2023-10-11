package com.solute.ui.onboarding.appStory

import androidx.annotation.DrawableRes
import com.solute.R

data class Pager(
   @DrawableRes val image:Int,
    val des:String
)
val dataList = listOf(
    Pager(R.drawable.default_button,"PAGE ONE"),
    Pager(R.drawable.onboarding_viewpager_selector_white,"PAGE ONE"),
)