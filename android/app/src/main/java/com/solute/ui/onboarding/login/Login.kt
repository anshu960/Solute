package com.solute.ui.onboarding.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solute.R

@Preview
@Composable
fun LoginScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.solute), // Replace R.drawable.solute with your actual image resource
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 32.dp, top = 60.dp, end = 32.dp),
            )
            TextComponent(value = stringResource(id = R.string.login_title))
            TextComponent(value = stringResource(id = R.string.login_sub_title))
            CountryCodePickerLayout()
            TextInputLayoutWithTextField()

        }
    }
}



@Composable
fun CountryCodePickerLayout() {
    var selectedCountry by remember { mutableStateOf("IN") }
    var phoneNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        CountryCodePicker(
            modifier = Modifier
                .wrapContentWidth()
                .height(0.dp)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
            selectedCountry = selectedCountry,
            onCountrySelected = { selectedCountry = it }
        )

    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputLayoutWithTextField() {
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
            CountryCodePicker(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .wrapContentWidth()
                    .height(0.dp)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),

            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Mobile Number") },
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

fun CountryCodePicker(modifier: Modifier) {

}

@Composable
fun CountryCodePicker(
    modifier: Modifier = Modifier,
    selectedCountry: String,
    onCountrySelected: (String) -> Unit
) {

}
        

@Preview
@Composable
fun OtpScreen() {
    // SignUpScreen()
    //LoginScreen()
    LoginScreen()
}





