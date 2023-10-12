package com.solute.ui.onboarding.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solute.R
import com.solute.ui.onboarding.login.HeadingTextComponent
import com.solute.ui.onboarding.login.RegisterTextComponent

@Preview
@Composable
fun RegisterScreen() {
    Surface()
    {
        Column(modifier = Modifier.fillMaxSize()) {
            RegisterTextComponent(value = stringResource(id = R.string.display_name))
            RegisterTextComponent(value = stringResource(id = R.string.can_you_please_provide_your_name))
            TextInput()
            LoginButton()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput() {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, end = 8.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.End
        ) {
            // Add the CountryCodePicker composable here

        }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(end = 8.dp)
        )
    }
}



@Composable
fun LoginButton() {
    Button(
        onClick = {
            // Handle button click here
        },
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 32.dp, top = 16.dp, end = 32.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF082287), // Replace with your color
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp),
        content = {
            Text(
                text = "Enter OTP",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                style = MaterialTheme.typography.button,
                textAlign = TextAlign.Center
            )
        }
    )
}
@Preview
@Composable
fun otp() {
    RegisterScreen()

}





