package com.solute.ui.onboarding.appStory

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.solute.R

class OnboardingPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context
) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_invoice),
                context.resources.getString(R.string.description_onboarding_invoice),
                R.raw.lottie_invoice,
                "#ffffff"
            )
            1 -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_reports),
                context.resources.getString(R.string.description_onboarding_reports),
                R.raw.lottie_reports,
                "#ffffff"
            )
            else -> OnBoardingFragment.newInstance(
                context.resources.getString(R.string.title_onboarding_inventory),
                context.resources.getString(R.string.description_onboarding_inventory),
                R.raw.lottie_stock,
                "#ffffff"
            )
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}