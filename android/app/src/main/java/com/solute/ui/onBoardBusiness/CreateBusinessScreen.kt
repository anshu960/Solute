package com.solute.ui.onBoardBusiness

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CreateBusinessAddressForm(modifier: Modifier = Modifier, numberOfSteps: Int, currentStep: Int) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        
            for (step in 0..numberOfSteps) {
                Step(
                    modifier = Modifier.weight(1F),
                    isCompete = step < currentStep,
                    isCurrent = step == currentStep
                )
            }
        
     
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)

        ) {
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "House Name") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "Street Locality") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "City") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "Pin Code / Zip Code") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "State") }
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                OutlinedTextField(
                    value = "", // Add the value for the text field
                    onValueChange = { /* Handle value change */ },
                    label = { Text(text = "Landmark") }
                )
            }
            item { 
                Spacer(modifier = Modifier.height(20.dp))
            }
            item { 
                Button(
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomStart = 10.dp, bottomEnd = 10.dp),
                    onClick = { /* Handle click action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(51.dp),
                ) {
                    
                }
            }
        }
   
    }
}

@Composable
fun Step(modifier: Modifier = Modifier, isCompete: Boolean, isCurrent: Boolean) {
    val color = if (isCompete || isCurrent) Color.Red else Color.LightGray
    val innerCircleColor = if (isCompete) Color.Red else Color.LightGray

    Box(modifier = modifier) {

        //Line
        Divider(
            modifier = Modifier.align(Alignment.CenterStart),
            color = color,
            thickness = 2.dp
        )
    }
}

@Preview
@Composable
fun StepsProgressBarPreview() {
    val currentStep = remember { mutableStateOf(1) }
    CreateBusinessAddressForm(modifier = Modifier.fillMaxWidth(), numberOfSteps = 5, currentStep = currentStep.value)
}








