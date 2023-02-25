package com.solute.ui.profile

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.friendly.framework.UtilityActivity
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.squareup.picasso.Picasso

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

class ProfileActivity : UtilityActivity() {
    var user = FriendlyUser()
    val picasso = Picasso.get()
    var isProfilePicChanged = false
    var fileUri : Uri? = null
    var profileImage : ImageView? = null
    var nameFiled : TextInputEditText? = null
    var statusFiled : TextInputEditText? = null
    var mobileFiled : TextInputEditText? = null
    var emailFiled : TextInputEditText? = null
    var editProfilePictureBtn : MaterialButton? = null
    var updateButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        profileImage = findViewById(R.id.image_my_profile)
        if(user.profilePic != ""){
            val picasso = Picasso.get()
            picasso.load(user.profilePic).into(profileImage)
        }else{
            profileImage?.setImageResource(R.drawable.account_circle)
        }
        nameFiled = findViewById(R.id.username_field_my_profile)
        statusFiled = findViewById(R.id.status_field_my_profile)
        mobileFiled = findViewById(R.id.mobile_field_my_profile)
        emailFiled = findViewById(R.id.email_field_my_profile)
        nameFiled?.setText(user.name)
        statusFiled?.setText(user.status)
        mobileFiled?.setText(user.mobile)
        emailFiled?.setText(user.email)
        editProfilePictureBtn = findViewById(R.id.edit_profile_pic_btn_my_profile)
        editProfilePictureBtn?.setOnClickListener { onClickEditProfileImage() }
        updateButton = findViewById(R.id.update_btn_profile)
        updateButton?.setOnClickListener { onClickUpdateFriendlyFriendlyProfile() }
        MediaFileHandler.shared().viewModel?.loadFor(user._id){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                picasso.load(it.first().FileURL).into(profileImage)
            }
        }
    }

    fun onClickEditProfileImage(){
        getImageUrl {
            fileUri = it
            picasso.load(it).into(this.profileImage)
            MediaFileHandler.shared().viewModel?.activity = this
            MediaFileHandler.shared().onCreateNew={
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    toastLong("Image Uploaded")
                }
            }
            MediaFileHandler.shared().viewModel?.uploadImage(it,user._id)
        }
    }

    fun onClickUpdateFriendlyFriendlyProfile(){
        val request = JSONObject()
        request.put(KeyConstant.name,nameFiled?.text.toString())
        request.put(KeyConstant.status,statusFiled?.text.toString())
        request.put(KeyConstant.emailId,emailFiled?.text.toString())
        request.put(KeyConstant._id,user._id)
        SocketService.shared().onEvent={ event, response->
            if(response.has(KeyConstant.success) && response.getBoolean(KeyConstant.success)){
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    user.name = nameFiled?.text.toString()
                    user.status = statusFiled?.text.toString()
                    user.email = emailFiled?.text.toString()
                    user.updateInLocalStorage()
                    toastLong("Profile Updated")
                }
            }else{
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    toastLong("Unable to update Profile at the moment,Please try after some time!")
                }
            }
        }
        SocketService.shared().send(SocketEvent.updateFriendlyProfile,request)
    }


}