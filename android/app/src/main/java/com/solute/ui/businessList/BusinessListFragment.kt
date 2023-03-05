package com.solute.ui.businessList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.business.viewModel.BusinessViewModalFactory
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.app.App
import com.solute.navigation.AppNavigator


/**
 * A simple [Fragment] subclass.
 * Use the [BusinessListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessListFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private var adapter: BusinessListAdapter? = null
    var allBusiness: ArrayList<Business> = ArrayList()
    var fabButton  :FloatingActionButton? = null
    var emptyCardView : CardView? = null
    var emptyImageView : LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessListAdapter(it, allBusiness) }
        BusinessHandler.shared().viewModal?.allBusiness?.observe(this) {
            allBusiness = it
            this.reload()
        }
        BusinessHandler.shared().viewModal?.loadBusiness()
        BusinessHandler.shared().fetchAllBusiness()
    }

    fun reload() {
        if(allBusiness.isNullOrEmpty()){
            emptyImageView?.setAnimation(R.raw.lottie_business_front)
            emptyCardView?.visibility = View.VISIBLE
        }else{
            emptyCardView?.visibility = View.GONE
        }
        this.recyclerView!!.layoutManager = LinearLayoutManager(this.context)
        adapter = this.context?.let { BusinessListAdapter(it, allBusiness) }
        this.recyclerView!!.adapter = this.adapter
        if(allBusiness.isEmpty()){
            BusinessHandler.shared().fetchAllBusiness()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_list, container, false)
        emptyCardView = view.findViewById(R.id.fragment_business_list_empty_card)
        emptyCardView?.setOnClickListener { AppNavigator.shared().navigateToSelectBusinessType()  }
        emptyImageView = view.findViewById(R.id.fragment_business_list_empty_img_view)
        this.recyclerView = view.findViewById(R.id.fragment_business_list_recycler)
        MobileAds.initialize(this.requireContext()) {}
        fabButton = view.findViewById(R.id.fragment_business_list_add_btn)
        fabButton?.setOnClickListener { AppNavigator.shared().navigateToSelectBusinessType() }
        reload()
        BusinessHandler.shared().viewModal?.loadBusiness()
        App.shared().mainActivity?.setMainMenu()
        return view
    }
}