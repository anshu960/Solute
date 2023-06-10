package com.friendly.framework.analytics.event

class AnalyticEvent {
    companion object{
        const val AUTH_ON_BOARDING = "AUTH_ON_BOARDING"
        const val AUTH_SEND_OTP = "AUTH_SEND_OTP"
        const val AUTH_VERIFY_OTP = "AUTH_VERIFY_OTP"
        const val AUTH_OTP_VERIFIED = "AUTH_OTP_VERIFIED"
        const val AUTH_LOGIN = "AUTH_LOGIN"
        const val AUTH_REGISTER_USER = "AUTH_REGISTER_USER"
        const val AUTH_REGISTER_BUSINESS = "AUTH_REGISTER_BUSINESS"

        const val BUSINESS_HOME = "BUSINESS_HOME"

        const val CREATE_INVOICE = "CREATE_INVOICE"
    }
}