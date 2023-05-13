package com.solute.ui.business.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.friendly.framework.feature.businessMenu.BusinessMenuConfig
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.friendly.framework.feature.businessMenu.modal.BusinessMenu
import com.solute.R
import com.solute.navigation.AppNavigator

class BusinessHomeFragment : Fragment() {
    var allMenu = BusinessMenuConfig().getMenu()
    var menuView1: ComposeView? = null
    var menuView2: ComposeView? = null
    var menuView3: ComposeView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_business_home, container, false)
        menuView1 = view.findViewById(R.id.menu_view1)
        menuView2 = view.findViewById(R.id.menu_view2)
        menuView3 = view.findViewById(R.id.menu_view3)
        menuView1?.setContent {
            subMenu(menu = allMenu[0])
        }
        menuView2?.setContent {
            subMenu(menu = allMenu[1])
        }
        menuView3?.setContent {
            subMenu(menu = allMenu[2])
        }
        return view
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun subMenu(menu: BusinessMenu) {
        Row(
            modifier = Modifier
                .padding(horizontal = 46.dp, vertical = 48.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,


        ) {
            Text(
                text = menu.title!!,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .padding(8.dp) // margin
                    .padding(8.dp) // space between the borders
                    .padding(8.dp)
            )

        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),

            ) {


            items(menu.sub_menu) { subMenu ->
                Card(
                    modifier = Modifier
                        .height(80.dp)
                        .width(180.dp)
                        .padding(8.dp), // margin
                    backgroundColor = subMenu.background_color!!.toColor(),
                            onClick = { onClickMenu(subMenu) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painterResource(
                                resources.getIdentifier(
                                    subMenu.icon!!, "drawable",
                                    requireContext().packageName
                                )
                            ),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = subMenu.title!!,
                                fontSize = 15.sp,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onClickMenu(menu:BusinessMenu){
        when(menu.target_screen){
            "fragment_business_sale"-> AppNavigator.shared().navigateToSale()
            "business_invoice" -> AppNavigator.shared().navigateToInvoices()
        }
    }
}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))