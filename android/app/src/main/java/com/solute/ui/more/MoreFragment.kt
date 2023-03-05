package com.solute.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.friendly.framework.Defaults
import com.friendly.framework.constants.KeyConstant
import com.friendly.framework.dataclass.FriendlyUser
import com.friendly.framework.feature.mediaFile.handler.MediaFileHandler
import com.google.firebase.auth.FirebaseAuth
import com.solute.MainActivity
import com.solute.R
import com.solute.navigation.AppNavigator
import com.solute.ui.onboarding.OnBoardingActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MoreFragment : Fragment() {

    var user = FriendlyUser()
    val picasso = Picasso.get()
    var profileImageView : ImageView? = null
    var profileNameTextView : TextView? = null
    var profileStatusTextView : TextView? = null
    var activity : MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val picasso = Picasso.get()
        val view = inflater.inflate(R.layout.fragment_more, container, false)
        val profileLayout : ConstraintLayout = view.findViewById(R.id.more_profile_layout)
        profileLayout.setOnClickListener { presentFriendlyFriendlyProfile() }
        profileNameTextView = view.findViewById(R.id.more_profile_name)
        profileNameTextView?.text = this.user.name
        profileStatusTextView = view.findViewById(R.id.more_profile_status)
        if(this.user.status != ""){
            profileStatusTextView?.text = this.user.status
        }else{
            profileStatusTextView?.text = this.user.mobile
        }
        profileImageView = view.findViewById(R.id.more_profile_image)
        MediaFileHandler.shared().viewModel?.loadFor(user._id){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                picasso.load(it.first().FileURL).into(profileImageView)
            }
        }
        val logoutButton : Button = view.findViewById(R.id.more_logout_btn)
        logoutButton.setOnClickListener { onClickLogout() }
        if(context is MainActivity){
            activity = context as MainActivity
        }
        return view
    }

    fun presentFriendlyFriendlyProfile() {
        AppNavigator.shared().gotToProfile()
    }



    fun onClickLogout() {
        FirebaseAuth.getInstance().signOut()
        Defaults.shared().remove(KeyConstant.loginDetails)
        val intent = Intent(context, OnBoardingActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        user = FriendlyUser()
        profileNameTextView?.text = this.user.name
        if(this.user.status != ""){
            profileStatusTextView?.text = this.user.status
        }else{
            profileStatusTextView?.text = this.user.mobile
        }
        MediaFileHandler.shared().viewModel?.loadFor(user._id){
            CoroutineScope(Job() + Dispatchers.Main).launch {
                picasso.load(it.first().FileURL).into(profileImageView)
            }
        }
    }


}
