package com.solute.ui.business.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MyComposeScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth(),
            content = {
                val (cardBtnToday, cardBtnWeek, cardBtnMonth, cardBtnYear) = createRefs()

                Card(
                    modifier = Modifier
                        .size(60.dp, 34.dp)
                        .constrainAs(cardBtnToday) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(parent.start, margin = 8.dp)
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        },
                    //backgroundColor = Color(0xFF0000FF), // Replace with your color
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (todayBtn) = createRefs()
                            Text(
                                text = "Today",
                                modifier = Modifier.constrainAs(todayBtn) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                },
                                color = Color.White, // Replace with your color
                                fontSize = 14.sp
                            )
                        }
                    )
                }

                Card(
                    modifier = Modifier
                        .size(60.dp, 34.dp)
                        .constrainAs(cardBtnWeek) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(cardBtnToday.end, margin = 8.dp)
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        },
                   // backgroundColor = Color(0xFFCCCCCC), // Replace with your color
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (weekBtn) = createRefs()
                            Text(
                                text = "Week",
                                modifier = Modifier.constrainAs(weekBtn) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                },
                                color = Color.Black, // Replace with your color
                                fontSize = 14.sp
                            )
                        }
                    )
                }

                Card(
                    modifier = Modifier
                        .size(60.dp, 34.dp)
                        .constrainAs(cardBtnMonth) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(cardBtnWeek.end, margin = 8.dp)
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        },
                    //backgroundColor = Color(0xFFCCCCCC), // Replace with your color
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (monthBtn) = createRefs()
                            Text(
                                text = "Month",
                                modifier = Modifier.constrainAs(monthBtn) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                },
                                color = Color.Black, // Replace with your color
                                fontSize = 14.sp
                            )
                        }
                    )
                }

                Card(
                    modifier = Modifier
                        .size(60.dp, 34.dp)
                        .constrainAs(cardBtnYear) {
                            top.linkTo(parent.top, margin = 8.dp)
                            start.linkTo(cardBtnMonth.end, margin = 8.dp)
                            bottom.linkTo(parent.bottom, margin = 8.dp)
                        },
                    // backgroundColor = Color(0xFFCCCCCC), // Replace with your color
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (yearBtn) = createRefs()
                            Text(
                                text = "Year",
                                modifier = Modifier.constrainAs(yearBtn) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                },
                                color = Color.Black, // Replace with your color
                                fontSize = 14.sp
                            )
                        }
                    )
                }
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (saleQuantityLabel, saleQuantity, saleQuantityCV) = createRefs()

                            Text(
                                text = "Sale Quantity",
                                modifier = Modifier.constrainAs(saleQuantityLabel) {
                                    top.linkTo(parent.top, margin = 8.dp)
                                    start.linkTo(parent.start, margin = 8.dp)
                                }
                            )

                            Text(
                                text = "0",
                                modifier = Modifier.constrainAs(saleQuantity) {
                                    top.linkTo(saleQuantityLabel.bottom, margin = 8.dp)
                                    start.linkTo(parent.start, margin = 8.dp)
                                },
                                color = Color.Black // Replace with your color
                            )

                            // You can add your Composable here for sale_quantity_cv
                        }
                    )
                }
            }

            item {
                // Repeat a similar structure for other cards
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            val (saleQuantityLabel, saleQuantity, saleQuantityCV) = createRefs()

                            Text(
                                text = "Sale Value",
                                modifier = Modifier.constrainAs(saleQuantityLabel) {
                                    top.linkTo(parent.top, margin = 8.dp)
                                    start.linkTo(parent.start, margin = 8.dp)
                                }
                            )

                            Text(
                                text = "0",
                                modifier = Modifier.constrainAs(saleQuantity) {
                                    top.linkTo(saleQuantityLabel.bottom, margin = 8.dp)
                                    start.linkTo(parent.start, margin = 8.dp)
                                },
                                color = Color.Black // Replace with your color
                            )

                            // You can add your Composable here for sale_quantity_cv
                        }
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun PreviewMyComposeScreen() {
    MyComposeScreen()
}
