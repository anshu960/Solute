package com.solute.ui.onboarding.userType

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.friendly.framework.constants.KeyConstant
import com.solute.R
import com.solute.navigation.AppNavigator
import com.solute.ui.onboarding.login.FirebaseAuthHelper
import org.json.JSONObject


class SelectUserTypeFragment : Fragment() {

    var businessManCard : CardView? = null
    var professionalCard : CardView? = null
    var customerCard : CardView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_select_user_type, container, false)
        businessManCard = view.findViewById(R.id.user_type_business_card)
        professionalCard = view.findViewById(R.id.user_type_professional_card)
        customerCard = view.findViewById(R.id.user_type_customer_card)
        businessManCard?.setOnClickListener {
            val roleType = JSONObject()
            roleType.put(KeyConstant._id,"6470a9898a292dd59a0dcfdf")
            roleType.put(KeyConstant.name,"BusinessMan")
            FirebaseAuthHelper.shared().roleType = roleType
            AppNavigator.shared().gotToRegister()
        }
        professionalCard?.setOnClickListener {
            val roleType = JSONObject()
            roleType.put(KeyConstant._id,"6470a9dbc6dfe157a29d62f6")
            roleType.put(KeyConstant.name,"Professional")
            FirebaseAuthHelper.shared().roleType = roleType
            AppNavigator.shared().gotToRegister()
        }
        customerCard?.setOnClickListener {
            val roleType = JSONObject()
            roleType.put(KeyConstant._id,"61acee7871a83e09a12a1668")
            roleType.put(KeyConstant.name,"User")
            FirebaseAuthHelper.shared().roleType = roleType
            AppNavigator.shared().gotToRegister()
        }
        return view
    }
}