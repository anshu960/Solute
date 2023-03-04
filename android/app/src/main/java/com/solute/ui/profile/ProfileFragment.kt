package com.solute.ui.profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.friendly.framework.socket.SocketEvent
import com.friendly.framework.socket.SocketService
import com.google.android.material.textfield.TextInputEditText
import com.solute.MainActivity
import com.solute.R
import com.solute.app.App
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    var user = FriendlyUser()
    val picasso = Picasso.get()
    var isProfilePicChanged = false
    var fileUri : Uri? = null
    var profileImage : ImageView? = null
    var nameFiled : TextInputEditText? = null
    var statusFiled : TextInputEditText? = null
    var mobileFiled : TextInputEditText? = null
    var emailFiled : TextInputEditText? = null
    var editProfilePictureBtn : AppCompatButton? = null
    var updateButton : Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        profileImage = view.findViewById(R.id.image_my_profile)
        if(user.profilePic != ""){
            val picasso = Picasso.get()
            picasso.load(user.profilePic).into(profileImage)
        }else{
            profileImage?.setImageResource(R.drawable.account_circle)
        }
        nameFiled = view.findViewById(R.id.username_field_my_profile)
        statusFiled = view.findViewById(R.id.status_field_my_profile)
        mobileFiled = view.findViewById(R.id.mobile_field_my_profile)
        emailFiled = view.findViewById(R.id.email_field_my_profile)
        nameFiled?.setText(user.name)
        statusFiled?.setText(user.status)
        mobileFiled?.setText(user.mobile)
        emailFiled?.setText(user.email)
        editProfilePictureBtn = view.findViewById(R.id.edit_profile_pic_btn_my_profile)
        editProfilePictureBtn?.setOnClickListener { onClickEditProfileImage() }
        updateButton = view.findViewById(R.id.update_btn_profile)
        updateButton?.setOnClickListener { onClickUpdateFriendlyFriendlyProfile() }
        MediaFileHandler.shared().viewModel?.loadFor(user._id){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                picasso.load(it.first().FileURL).into(profileImage)
            }
        }
        return view
    }

    fun onClickEditProfileImage(){
        App.shared().mainActivity?.getImageUrl {
            fileUri = it
            picasso.load(it).into(this.profileImage)
            MediaFileHandler.shared().viewModel?.activity = App.shared().mainActivity
            MediaFileHandler.shared().onCreateNew={
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    App.shared().mainActivity?.toastLong("Image Uploaded")
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
                    App.shared().mainActivity?.toastLong("Profile Updated")
                }
            }else{
                CoroutineScope(Job() + Dispatchers.Main).launch {
                    App.shared().mainActivity?.toastLong("Unable to update Profile at the moment,Please try after some time!")
                }
            }
        }
        SocketService.shared().send(SocketEvent.updateFriendlyProfile,request)
    }
}