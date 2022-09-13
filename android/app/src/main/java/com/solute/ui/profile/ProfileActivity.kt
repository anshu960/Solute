package com.solute.ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import com.solute.R
import com.squareup.picasso.Picasso
import com.utilitykit.dataclass.User

class ProfileActivity : AppCompatActivity() {
    var user = User()
    var isProfilePicChanged = false
    var profileImage : ImageView? = null
    var nameFiled : TextInputEditText? = null
    var statusFiled : TextInputEditText? = null
    var mobileFiled : TextInputEditText? = null
    var emailFiled : TextInputEditText? = null
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
    }
}