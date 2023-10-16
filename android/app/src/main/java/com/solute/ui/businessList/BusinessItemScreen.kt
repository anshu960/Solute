package com.solute.ui.businessList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.solute.R

@Composable
fun RecyclerItemBusinessCard() {
    var text by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(8.dp),
       // elevation = 4.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (cardLayout, name, mobileImg, mobileTxt, locationImg, locationTxt, analyticsImg, analyticsTxt, logo, qrImg) = createRefs()

            Surface(
                modifier = Modifier.constrainAs(cardLayout) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                color = Color.Gray,
                contentColor = Color.White,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                )

                // Replace the background color with your custom drawable
                // using painterResource(R.drawable.business_card1) or Image() composable
            }

            // Business Name Text
            Text(
                text = "Business Name", // Replace with your text
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(logo.end, margin = 8.dp)
                }
            )

            // Mobile Image
            Icon(
                imageVector = MediumIcons.Phone,
                contentDescription = null, // Provide a proper content description
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(mobileImg) {
                        top.linkTo(name.bottom, margin = 8.dp)
                        start.linkTo(logo.end, margin = 8.dp)
                    }
            )

            // Mobile Text
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the done action if needed */ }
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                ),
                modifier = Modifier
                    .constrainAs(mobileTxt) {
                        top.linkTo(name.bottom, margin = 8.dp)
                        start.linkTo(mobileImg.end, margin = 8.dp)
                    }
            )

            // Location Image
            Icon(
                imageVector = MediumIcons.LocationOn,
                contentDescription = null, // Provide a proper content description
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(locationImg) {
                        top.linkTo(mobileImg.bottom, margin = 12.dp)
                        start.linkTo(logo.end, margin = 8.dp)
                    }
            )

            // Location Text
            Text(
                text = "Location", // Replace with your text
                color = Color.Black,
                modifier = Modifier.constrainAs(locationTxt) {
                    top.linkTo(mobileTxt.bottom, margin = 8.dp)
                    start.linkTo(locationImg.end, margin = 8.dp)
                }
            )

            // Analytics Image
            Icon(
                imageVector = MediumIcons.PieChart,
                contentDescription = null, // Provide a proper content description
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(analyticsImg) {
                        top.linkTo(locationImg.bottom, margin = 12.dp)
                        start.linkTo(logo.end, margin = 8.dp)
                    }
            )

            // Analytics Text
            Text(
                text = "0", // Replace with your text
                color = Color.Black,
                modifier = Modifier.constrainAs(analyticsTxt) {
                    top.linkTo(locationTxt.bottom, margin = 8.dp)
                    start.linkTo(analyticsImg.end, margin = 8.dp)
                }
            )

            // Business Logo
            CoilImage(
                data = R.mipmap.ic_launcher, // Replace with your image resource
                contentDescription = "Business Logo", // Provide a proper content description
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            // QR Image
            Icon(
                imageVector = MediumIcons.QrCode,
                contentDescription = null, // Provide a proper content description
                tint = Color.Black,
                modifier = Modifier
                    .size(40.dp)
                    .constrainAs(qrImg) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

@Composable
fun CoilImage(data: Any, contentDescription: String, contentScale: ContentScale, modifier: Modifier) {

}
