package com.solute.ui.businessList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.solute.R
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.business.viewModel.BusinessViewModalFactory
import com.utilitykit.feature.business.viewModel.BusinessViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [BusinessListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BusinessListFragment : Fragment() {
    var recyclerView: RecyclerView? = null
    private lateinit var businessViewModel: BusinessViewModel
    private var adapter: BusinessListAdapter? = null
    var allBusiness: ArrayList<Business> = ArrayList()
    lateinit var mAdView : AdView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = this.context?.let { BusinessListAdapter(it, allBusiness) }
        businessViewModel = ViewModelProvider(
            this,
            BusinessViewModalFactory(BusinessHandler.shared().repository)
        ).get(
            BusinessViewModel::class.java
        )
        businessViewModel.allBusiness.observe(this) {
            if(!it.isNullOrEmpty()){
                allBusiness = it
            }
            this.reload()
        }
        BusinessHandler.shared().setup(businessViewModel)
        BusinessHandler.shared().fetchAllBusiness()
        initAdmob()
    }

    fun initAdmob(){

    }

    fun reload() {
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
        this.recyclerView = view.findViewById(R.id.fragment_business_list_recycler)
        businessViewModel.loadBusiness()
        reload()
        MobileAds.initialize(this.requireContext()) {}
        mAdView = view.findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        return view
    }
}