package com.solute.ui.business.profile.self.info

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.solute.app.App
import com.squareup.picasso.Picasso

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SelfBusinessProfileInfoFragment : Fragment() {
    val business = BusinessHandler.shared().repository.business.value
    var fileUri : Uri? = null
    val picasso = Picasso.get()

    var profileImage: ImageView? = null
    var editProfileImgCard: CardView? = null
    var nameField : TextInputEditText? = null
    var mobileField : TextInputEditText? = null
    var emailField : TextInputEditText? = null

    var deleteBtn : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_self_business_profile_info, container, false)
        profileImage = view.findViewById(R.id.self_business_profile_info_img)
        profileImage?.setImageResource(R.drawable.storefront)
        editProfileImgCard =
            view.findViewById(R.id.self_business_profile_info_edit_profile_img_card)
        editProfileImgCard?.setOnClickListener { onClickEditProfileImg() }

        nameField = view.findViewById(R.id.self_business_profile_info_name_tiet)
        mobileField = view.findViewById(R.id.self_business_profile_info_mobile_tiet)
        emailField = view.findViewById(R.id.self_business_profile_info_email_tiet)

        deleteBtn = view.findViewById(R.id.self_business_profile_delete_btn)
        deleteBtn?.setOnClickListener { onClickDelete() }

        loadProfileData()
        return view
    }

    fun loadProfileData(){
        if(business != null){
            MediaFileHandler.shared().viewModel?.loadFor(business.Id){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    picasso.load(it.first().FileURL).into(profileImage)
                }
            }
            nameField?.setText(business.Name)
            mobileField?.setText(business.MobileNumber)
            emailField?.setText(business.EmailID)
        }
    }

    fun onClickEditProfileImg() {
        App.shared().mainActivity?.getImageUrl {
            fileUri = it
            picasso.load(it).into(this.profileImage)
            MediaFileHandler.shared().viewModel?.activity = App.shared().mainActivity
            MediaFileHandler.shared().onCreateNew={
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    App.shared().mainActivity?.toastLong("Image Uploaded")
                }
            }
            if(BusinessHandler.shared().repository.business.value?.Id != null){
                MediaFileHandler.shared().viewModel?.uploadImage(it,BusinessHandler.shared().repository.business.value!!.Id)
            }
        }
    }

    fun onClickDelete(){
        if (business != null) {
            BusinessHandler.shared().onDeleteBusinessResponse={
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    App.shared().mainActivity?.onBackPressed()
                    App.shared().mainActivity?.onBackPressed()
                    App.shared().mainActivity?.onBackPressed()
                    App.shared().mainActivity?.onBackPressed()
                }
            }
            BusinessHandler.shared().viewModal?.deleteBusiness(business)
        }
    }
}
