package com.solute.ui.onBoardBusiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.businessType.model.BusinessType
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModalFactory
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModel
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.business.create.adapter.BusinessTypeAdapter

class SelectBusinessTypeFragment : Fragment() {
    var recycler : RecyclerView? = null
    var viewModal : BusinessTypeViewModel? = null
    var allBusinessType : ArrayList<BusinessType> = arrayListOf()
    var adapter : BusinessTypeAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_business_type, container, false)
        this.recycler = view.findViewById(R.id.select_business_type_recycler)
        viewModal  = ViewModelProvider(
            this,
            BusinessTypeViewModalFactory(BusinessTypeHandler.shared().repository)
        )[BusinessTypeViewModel::class.java]
        BusinessTypeHandler.shared().setup(this.viewModal!!)
        BusinessTypeHandler.shared().repository.businessTypeLiveData.observe(this.context as MainActivity){
            if(it != null){
                allBusinessType = it
                loadBusinessTypesInUI()
            }
        }
        if(BusinessTypeHandler.shared().repository.allBusinessType.value != null){
            allBusinessType = BusinessTypeHandler.shared().repository.allBusinessType.value!!
            loadBusinessTypesInUI()
        }
        BusinessTypeHandler.shared().fetchAllBusinessType()
        return view
    }

    fun loadBusinessTypesInUI(){
        recycler?.layoutManager = LinearLayoutManager(this.context)
        this.adapter = this.context?.let { BusinessTypeAdapter(it,this.allBusinessType) }
        recycler?.adapter = this.adapter
    }

}