package com.solute.ui.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.solute.R


@Composable
fun ProfileLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(103.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            val (profileImageCard, profileName, profileStatus, disclosureImg) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.baseline_person),
                modifier = Modifier.constrainAs(profileImageCard) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.value(70.dp)
                    height = Dimension.value(70.dp)
                },
                contentDescription = null
            )

            Text(
                text = "TextView",
                modifier = Modifier.constrainAs(profileName) {
                    top.linkTo(parent.top)
                    start.linkTo(profileImageCard.end)
                    bottom.linkTo(parent.bottom)
                },
                fontSize = 24.sp,
            )

            Text(
                text = "TextView",
                modifier = Modifier.constrainAs(profileStatus) {
                    top.linkTo(profileName.bottom)
                    start.linkTo(profileImageCard.end)
                }
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right),
                contentDescription = null,
                modifier = Modifier.constrainAs(disclosureImg) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
           // val (powerImg, logoutBtn) = createGuidelineFromStart(0.1f).createReferences()
              val (powerImg, logoutBtn) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.baseline_power_settings_new),
                contentDescription = null,
                modifier = Modifier.constrainAs(powerImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
            )

            Button(
                onClick = { /* Handle logout button click */ },
                modifier = Modifier.constrainAs(logoutBtn) {
                    top.linkTo(parent.top)
                    start.linkTo(powerImg.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                },
                colors = ButtonDefaults.buttonColors(
                    //backgroundColor = Color.White,
                    contentColor = Color.Black

                )
            ) {
                Text(text = "Logout")
            }
        }
    }
}

@Composable
fun YourComposable() {
    ProfileLayout()
}

@Preview
@Composable
fun YourComposablePreview() {
    YourComposable()
}
