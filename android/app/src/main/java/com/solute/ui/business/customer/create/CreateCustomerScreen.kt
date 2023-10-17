package com.solute.ui.business.customer.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.solute.R

@Composable
fun CreateCustomerScreen(
    // onSaveClick: () -> Unit,
) {
     Column(
          modifier = Modifier
               .fillMaxSize()
               .padding(16.dp)
     ) {
          OutlinedTextField(
               value = "",
               onValueChange = { /* Handle name input */ },
               label = { Text(text = stringResource(id = R.string.name)) },
               singleLine = true,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
          )

          // You can add a CountryCodePicker component here if needed

          OutlinedTextField(
               value = "",
               onValueChange = { /* Handle mobile input */ },
               label = { Text(text = stringResource(id = R.string.mobile_number)) },
               keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
               singleLine = true,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
          )

          OutlinedTextField(
               value = "",
               onValueChange = { /* Handle email input */ },
               label = { Text(text = stringResource(id = R.string.email_optional)) },
               singleLine = true,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
          )

          OutlinedTextField(
               value = "",
               onValueChange = { /* Handle barcode input */ },
               label = { Text(text = stringResource(id = R.string.barcode_optional)) },
               keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
               singleLine = true,
               modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
          )

          Spacer(modifier = Modifier.height(16.dp))

          Button(
               onClick = {},
               modifier = Modifier.fillMaxWidth(),
          ) {
               Text(text = stringResource(id = R.string.save))
          }
     }
}

@Preview
@Composable
fun CreateCustomer() {
     CreateCustomerScreen ()
}

