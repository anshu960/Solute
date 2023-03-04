package com.solute.ui.onboarding.appStory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jaeger.library.StatusBarUtil
import com.solute.R
import com.solute.app.App
import com.solute.ui.onboarding.login.FirebaseAuthHelper

class AppStoryFragment : Fragment() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var textSkip: TextView
    private lateinit var textEnd: TextView
    private lateinit var btnNextStep: ImageButton
    private lateinit var pageIndicator : TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_app_story, container, false)
        pageIndicator = view.findViewById(R.id.pageIndicator)
        mViewPager = view.findViewById(R.id.viewPager)
        textSkip = view.findViewById(R.id.text_skip)
        textEnd = view.findViewById(R.id.text_end)
        btnNextStep = view.findViewById(R.id.btn_next_step)
        mViewPager.adapter = OnboardingPagerAdapter(requireActivity(), requireContext())
        mViewPager.offscreenPageLimit = 1
        pageIndicator.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark))
        TabLayoutMediator(pageIndicator, mViewPager) { _, _ -> }.attach()
        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 2) {
                    btnNextStep.visibility = View.GONE
                    textEnd.visibility = View.VISIBLE
                    textSkip.visibility = View.GONE
                } else {
                    btnNextStep.visibility = View.VISIBLE
                    textEnd.visibility = View.GONE
                    textSkip.visibility = View.VISIBLE
                }
            }

            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        })


        StatusBarUtil.setTranslucentForImageViewInFragment(requireActivity(), null)

        textSkip.setOnClickListener {
            Defaults.shared().store(KeyConstant.appStorySeen,"true")
            App.shared().mainActivity?.gotToLogin()
        }

        textEnd.setOnClickListener {
            Defaults.shared().store(KeyConstant.appStorySeen,"true")
            App.shared().mainActivity?.gotToLogin()
        }

        val btnNextStep: ImageButton = btnNextStep
        btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                Defaults.shared().store(KeyConstant.appStorySeen,"true")
                App.shared().mainActivity?.gotToLogin()
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }
        return view
    }


    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}