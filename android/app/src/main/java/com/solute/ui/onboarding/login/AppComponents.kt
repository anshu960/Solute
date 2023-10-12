package com.solute.ui.onboarding.login

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.solute.R

@Composable
fun TextComponent(value:String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic
        ),
         color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )

}
@Composable
fun HeadingTextComponent(value:String) {
    Text(
        text = "Please enter the OTP received on phone",
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 38.dp, vertical = 16.dp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun RegisterTextComponent(value:String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 38.dp, vertical = 16.dp),
        textAlign = TextAlign.Center
    )

}

@Composable
fun MoreTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp, vertical = 15.dp),

    )
}

@Composable
fun MoreText(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxHeight()
            .width(0.dp)
            .padding(horizontal = 20.dp, vertical = 15.dp)
    )
}