package com.solute.ui.businessList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.model.Business
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.feature.sync.SyncHandler
import com.friendly.framework.qr.QRCodeUtill
import com.solute.app.App
import com.solute.R
import com.solute.navigation.AppNavigator
import com.squareup.picasso.Picasso

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BusinessListAdapter(val context: Context, val allBusiness: List<Business>) :
    RecyclerView.Adapter<BusinessViewHolder>() {

    override fun getItemCount(): Int {
        return allBusiness.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BusinessViewHolder, position: Int) {
        val item = allBusiness[position]
        holder.itemView.setOnClickListener {
                BusinessHandler.shared().repository.businessLiveData.postValue(item)
                SyncHandler.shared().clearBusinessAnalytics()
                SyncHandler.shared().syncAllBusinessData()
                AppNavigator.shared().goToBusinessHome()
                App.shared().mainActivity?.setBusinessMenu()
        }
        holder.bind(item,position)
    }
}

class BusinessViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.recycler_item_business, parent, false
    )
) {
    private var businessName: TextView? = null
    private var businessMobile: TextView? = null
    private var businessAddress: TextView? = null
    var cardLyout : ConstraintLayout? = null
    var qrImage : ImageView? = null
    var logoImage : ImageView? = null
    val picasso = Picasso.get()

    init {
        businessName = itemView.findViewById(R.id.recycler_item_business_name_txt)
        businessMobile = itemView.findViewById(R.id.recycler_item_business_mobile_txt)
        businessAddress = itemView.findViewById(R.id.recycler_item_business_location_txt)
        cardLyout = itemView.findViewById(R.id.recycler_item_business_card_layout)
        qrImage = itemView.findViewById(R.id.recycler_item_business_qr_img)
        logoImage = itemView.findViewById(R.id.recycler_item_business_logo)
    }

    fun bind(business: Business,index:Int) {
        businessName?.text = business.Name
        businessMobile?.text = business.MobileNumber
        businessAddress?.text = business.Address
        when(index.mod(4)){
            0-> cardLyout?.background =  getDrawable(App.shared(), R.drawable.business_card1)
            1-> cardLyout?.background =  getDrawable(App.shared(), R.drawable.business_card2)
            2-> cardLyout?.background =  getDrawable(App.shared(), R.drawable.business_card3)
            3-> cardLyout?.background = getDrawable(App.shared(), R.drawable.business_card4)
        }
        qrImage?.setImageBitmap(QRCodeUtill().getQRImage("https://solute.app"))
            MediaFileHandler.shared().viewModel?.loadFor(business.Id){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    picasso.load(it.first().FileURL).into(logoImage)
                }
            }
    }
}