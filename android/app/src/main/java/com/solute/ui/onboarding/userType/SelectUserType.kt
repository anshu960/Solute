package com.solute.ui.onboarding.userType

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import com.solute.R
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun UserTypeBusinessCard() {
    Surface()
    {
        Column(modifier = Modifier.fillMaxSize()) {
             Professional()
            Customer()
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp),
                backgroundColor = Color.White
            ) {

                Image(
                    painter = painterResource(id = R.drawable.background_user_type),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()

                )

                Text(
                    text = "Business Man",
                    color = Color.Black,
                    fontSize = 24.sp,
                    modifier = Modifier
                )

                Image(
                    painter = painterResource(id = R.drawable.user_type_business_man),
                    contentDescription = null,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun Professional() {
   Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        backgroundColor = Color.White
    ) {

        Image(
            painter = painterResource(id = R.drawable.background_user_type),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()

        )

        Text(
            text = "Professional",
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier
        )

        Image(
            painter = painterResource(id = R.drawable.user_type_employee),
            contentDescription = null,
            modifier = Modifier
        )
    }

}

@Composable
fun Customer() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        backgroundColor = Color.White
    ) {

        Image(
            painter = painterResource(id = R.drawable.background_user_type),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()

        )

        Text(
            text = "Customer",
            color = Color.Black,
            fontSize = 24.sp,
            modifier = Modifier
        )

        Image(
            painter = painterResource(id = R.drawable.user_type_customer),
            contentDescription = null,
            modifier = Modifier
        )
    }
}
@Preview
@Composable
fun UserTypeBusinessCardPreview() {
    UserTypeBusinessCard()
}

