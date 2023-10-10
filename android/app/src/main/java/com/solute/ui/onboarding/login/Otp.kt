package com.solute.ui.onboarding.login

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.solute.R

@Composable
fun Otp() {
    HeadingTextComponent(value = stringResource(id = R.string.please_enter_the_otp_received_on_phone))
}

@Preview
@Composable
fun OtpScreen() {
    Otp()
}