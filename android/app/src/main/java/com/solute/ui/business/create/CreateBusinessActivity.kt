package com.solute.ui.business.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.solute.R

class CreateBusinessActivity : AppCompatActivity() {
    var backButton : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_business)
        this.backButton = findViewById(R.id.create_business_header_back)
        backButton?.setOnClickListener { onBackPressed() }
    }
}