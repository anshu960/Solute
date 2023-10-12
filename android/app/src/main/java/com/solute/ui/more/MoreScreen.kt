package com.solute.ui.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solute.R


@Composable
fun UserDetails() {
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
                    .padding(start = 24.dp,top = 24.dp)
            ) {

                // User's name
                Text(
                    text = "Victoria Steele",
                    style = TextStyle(
                        fontSize = 22.sp,

                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // User's email
                Text(
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
//                Icon(
//                    modifier = Modifier.size(24.dp),
//                    imageVector = Icons.Outlined.Edit,
//                    contentDescription = "Edit Details",
//                    tint = MaterialTheme.colors.primary
//                )
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


@Preview
@Composable
fun User() {
    UserDetails()
}