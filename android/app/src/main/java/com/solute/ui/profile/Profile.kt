package com.solute.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solute.R

@Composable
fun MyProfileLayout() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            // Profile Picture
            Image(
                painter = painterResource(id = R.drawable.account_circle), // Set the image resource
                contentDescription = null, // Provide content description if needed
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            )
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 0.dp)
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Display Name Text Field
            TextField(
                value = "", // Add the value for the text field
                onValueChange = { /* Handle value change */ },
                label = { Text("Display Name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Mobile Number Text Field
            TextField(
                value = "", // Add the value for the text field
                onValueChange = { /* Handle value change */ },
                label = { Text("Mobile Number") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            // Status Text Field
            TextField(
                value = "", // Add the value for the text field
                onValueChange = { /* Handle value change */ },
                label = { Text("Status") },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {

            Button(
                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp),
                onClick = { /* Handle click action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(51.dp),
            )

            {

            }



        }
    }
}


@Preview
@Composable
fun Profile() {
    MyProfileLayout()
}