package com.solute.ui.businessList

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.solute.App
import com.solute.MainActivity
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.business.model.Business
import com.utilitykit.feature.sync.SyncHandler
import com.utilitykit.qr.QRCodeUtill

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
            if(context is MainActivity){
                val mainActivty = context as MainActivity
                val intent = Intent(mainActivty, BusinessActivity::class.java)
                BusinessHandler.shared().repository.business = item
                SyncHandler.shared().clearBusinessAnalytics()
                SyncHandler.shared().syncAllBusinessData()
                mainActivty.startActivity(intent)
            }
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

    init {
        businessName = itemView.findViewById(R.id.recycler_item_business_name_txt)
        businessMobile = itemView.findViewById(R.id.recycler_item_business_mobile_txt)
        businessAddress = itemView.findViewById(R.id.recycler_item_business_location_txt)
        cardLyout = itemView.findViewById(R.id.recycler_item_business_card_layout)
        qrImage = itemView.findViewById(R.id.recycler_item_business_qr_img)
    }

    fun bind(business: Business,index:Int) {
        businessName?.text = business.Name
        businessMobile?.text = business.MobileNumber
        businessAddress?.text = business.Address
        when(index.mod(4)){
            0-> cardLyout?.background =  getDrawable(App.applicationContext(), R.drawable.business_card1)
            1-> cardLyout?.background =  getDrawable(App.applicationContext(), R.drawable.business_card2)
            2-> cardLyout?.background =  getDrawable(App.applicationContext(), R.drawable.business_card3)
            3-> cardLyout?.background = getDrawable(App.applicationContext(), R.drawable.business_card4)
        }
        qrImage?.setImageBitmap(QRCodeUtill().getQRImage("https://solute.app"))
    }
}