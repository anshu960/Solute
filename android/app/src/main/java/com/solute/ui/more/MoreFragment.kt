package com.solute.ui.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.solute.App
import com.solute.R
import com.solute.ui.login.LoginActivity
import com.solute.ui.profile.ProfileActivity
import com.solute.utility.LocalFileManager
import com.squareup.picasso.Picasso
import com.utilitykit.Constants.Key
import com.utilitykit.Defaults
import com.utilitykit.dataclass.User

class MoreFragment : Fragment() {

    val user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val picasso = Picasso.get()
        val view = inflater.inflate(R.layout.fragment_more, container, false)
        if (this.user.profilePic != "") {
            LocalFileManager().getLocalFileUrl(this.user.profilePic) { url ->
                val profileImageView: ImageView = view.findViewById(R.id.more_profile_name)
                picasso.load(this.user.profilePic)?.into(profileImageView)
            }
        }
        val profileLayout : ConstraintLayout = view.findViewById(R.id.more_profile_layout)
        profileLayout.setOnClickListener { presentProfile() }
        val profileNameTextView: TextView = view.findViewById(R.id.more_profile_name)
        profileNameTextView?.text = this.user.name
        val logoutButton : Button = view.findViewById(R.id.more_logout_btn)
        logoutButton.setOnClickListener { onClickLogout() }
        return view
    }

    fun presentProfile() {
        val intent = Intent(context, ProfileActivity::class.java)
        requireContext().startActivity(intent)
    }



    fun onClickLogout() {
        FirebaseAuth.getInstance().signOut()
        Defaults.remove(Key.loginDetails)
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }

}
