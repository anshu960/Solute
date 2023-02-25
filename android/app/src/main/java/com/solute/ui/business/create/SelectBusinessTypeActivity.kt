package com.solute.ui.business.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.businessType.handler.BusinessTypeHandler
import com.friendly.framework.feature.businessType.model.BusinessType
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModalFactory
import com.friendly.framework.feature.businessType.viewModel.BusinessTypeViewModel
import com.solute.R
import com.solute.ui.business.create.adapter.BusinessTypeAdapter

import java.util.ArrayList

class SelectBusinessTypeActivity : AppCompatActivity() {
    var backButton : ImageButton? = null
    var recycler : RecyclerView? = null
    var viewModal : BusinessTypeViewModel? = null
    var allBusinessType : ArrayList<BusinessType> = arrayListOf()
    var adapter : BusinessTypeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_business_type)
        this.backButton = findViewById(R.id.select_business_type_header_back)
        this.backButton?.setOnClickListener { onBackPressed() }
        this.recycler = findViewById(R.id.select_business_type_recycler)
        viewModal  = ViewModelProvider(
            this,
            BusinessTypeViewModalFactory(BusinessTypeHandler.shared().repository)
        )[BusinessTypeViewModel::class.java]
        BusinessTypeHandler.shared().setup(this.viewModal!!)
        BusinessTypeHandler.shared().repository.businessTypeLiveData.observe(this){
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
    }

    fun loadBusinessTypesInUI(){
        recycler?.layoutManager = LinearLayoutManager(this)
        this.adapter = BusinessTypeAdapter(this,this.allBusinessType)
        recycler?.adapter = this.adapter
    }


}