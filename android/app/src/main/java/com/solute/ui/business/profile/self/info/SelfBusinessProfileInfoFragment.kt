package com.solute.ui.business.profile.self.info

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.solute.App.Companion.user
import com.solute.R
import com.solute.ui.business.BusinessActivity
import com.squareup.picasso.Picasso
import com.utilitykit.feature.business.handler.BusinessHandler
import com.utilitykit.feature.mediaFile.handler.MediaFileHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SelfBusinessProfileInfoFragment : Fragment() {
    val activity = BusinessHandler.shared().activity as? BusinessActivity
    var fileUri : Uri? = null
    val picasso = Picasso.get()

    var profileImage: ImageView? = null
    var editProfileImgCard: CardView? = null

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
        loadProfileData()
        return view
    }

    fun loadProfileData(){
        if(BusinessHandler.shared().repository.business.value?.Id != null){
            MediaFileHandler.shared().viewModel?.loadFor(BusinessHandler.shared().repository.business.value!!.Id){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    picasso.load(it.first().FileURL).into(profileImage)
                }
            }
        }
    }

    fun onClickEditProfileImg() {
        activity?.getImageUrl {
            fileUri = it
            picasso.load(it).into(this.profileImage)
            MediaFileHandler.shared().viewModel?.activity = this.activity
            MediaFileHandler.shared().onCreateNew={
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    activity?.toastLong("Image Uploaded")
                }
            }
            if(BusinessHandler.shared().repository.business.value?.Id != null){
                MediaFileHandler.shared().viewModel?.uploadImage(it,BusinessHandler.shared().repository.business.value!!.Id)
            }
        }
    }


}