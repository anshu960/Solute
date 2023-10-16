package com.solute.ui.onBoardBusiness

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton

import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.solute.R
import de.hdodenhof.circleimageview.CircleImageView


@Composable
fun UserDetail() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
       // expandform(Alignment.Top)
    ) {


        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            // User's image
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = R.drawable.baseline_person),
                contentDescription = "Your Image"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 3f, fill = false)
                        .padding(start = 24.dp, top = 24.dp)
                ) {

                    // User's name
                    androidx.compose.material.Text(
                        text = "Victoria Steele",
                        style = TextStyle(
                            fontSize = 22.sp,

                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // User's email
                    androidx.compose.material.Text(
                        text = "email123@email.com",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray,
                            letterSpacing = (0.8).sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Edit button
                IconButton(
                    modifier = Modifier
                        .weight(weight = 1f, fill = false),
                    onClick = {
                        // Toast.makeText(context, "Edit Button", Toast.LENGTH_SHORT).show()
                    }) {
                    Image(
                        modifier = Modifier
                            .weight(weight = 1f, fill = false),
                        painter = painterResource(id = R.drawable.baseline_keyboard_arrow_right),
                        contentDescription = "arrow"
                    )
                }

            }
        }
    }
    Box {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {

        }
    }
}

@Preview
@Composable
fun DefaultUser() {
    UserDetail()
}