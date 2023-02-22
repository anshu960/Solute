package com.solute.ui.business.profile.self.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.solute.R
import com.solute.ui.business.profile.self.address.adapter.AddressAdapter
import com.utilitykit.feature.address.handler.AddressHandler
import com.utilitykit.feature.address.model.Address
import com.utilitykit.feature.address.viewModel.AddressViewModalFactory
import com.utilitykit.feature.address.viewModel.AddressViewModel

class SelfBusinessProfileAddressFragment : Fragment() {


    var addBtn : FloatingActionButton? = null
    private var viewModal: AddressViewModel? = null
    var recycler:RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModal = ViewModelProvider(
            this,
            AddressViewModalFactory(AddressHandler.shared().repository)
        )[AddressViewModel::class.java]
        AddressHandler.shared().setup(viewModal!!)
        viewModal!!.allData.observe(this){ it ->
            if(recycler != null && it!= null){
                val adapter = AddressAdapter(this.requireContext(),it as ArrayList<Address>){address->
                    AddressHandler.shared().repository.selectedLiveData.postValue(address)
                    findNavController().navigate(R.id.navigate_to_create_address)
                }
                recycler!!.layoutManager = LinearLayoutManager(this.requireContext())
                recycler?.adapter = adapter
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_self_business_profile_address, container, false)
        addBtn = view.findViewById(R.id.address_list_create_fab_btn)
        addBtn?.setOnClickListener { onClickAdd() }
        this.recycler = view.findViewById(R.id.self_business_profile_address_recycler)
        viewModal?.retrieve()
        return view
    }

    fun onClickAdd(){
        AddressHandler.shared().repository.selectedLiveData.postValue(null)
        findNavController().navigate(R.id.navigate_to_create_address)
    }
}
