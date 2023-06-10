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
import androidx.compose.material.Colors
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.friendly.framework.feature.business.handler.BusinessHandler
import com.friendly.framework.feature.business.viewModel.BusinessViewModel
import com.friendly.framework.feature.businessMenu.modal.BusinessMenu
import com.friendly.framework.feature.businessMenu.modal.MenuType
import com.solute.MainActivity
import com.solute.R
import com.solute.navigation.AppNavigator

class BusinessHomeFragment : Fragment() {
    var allMenu = BusinessMenuConfig().getMenu()
    var menuView1: ComposeView? = null
    var menuView2: ComposeView? = null
    var menuView3: ComposeView? = null
    var menuView4: ComposeView? = null
    var menuView5: ComposeView? = null
    var menuView6: ComposeView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         BusinessHandler.shared().viewModal?.selectedBusiness?.observe(this){
             allMenu = BusinessMenuConfig().getMenu()
             loadMenuInUI()
         }
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
        menuView4 = view.findViewById(R.id.menu_view4)
        menuView5 = view.findViewById(R.id.menu_view5)
        menuView6 = view.findViewById(R.id.menu_view6)
        (context as? MainActivity)?.setBusinessMenu()
        loadMenuInUI()
        return view
    }

    fun loadMenuInUI(){
        menuView1?.setContent {
            subMenu(menu = allMenu[0])
        }
        menuView2?.setContent {
            subMenu(menu = allMenu[1])
        }
        menuView3?.setContent {
            subMenu(menu = allMenu[2])
        }
        menuView4?.setContent {
            subMenu(menu = allMenu[3])
        }
        menuView5?.setContent {
            subMenu(menu = allMenu[4])
        }
        menuView6?.setContent {
            subMenu(menu = allMenu[5])
        }
    }


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun subMenu(menu: BusinessMenu) {
        var height = 0
        if(menu.sub_menu.count()>0){
            height = menu.sub_menu.count() * 50
        }
        if(menu.sub_menu.isNotEmpty()){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(4.dp),
                modifier = Modifier
                    .height(height.dp)
                    .width(180.dp)
                    .padding(8.dp),
                ) {
                items(menu.sub_menu) { subMenu ->
                    if(subMenu.menuType == MenuType.HEADER){
                        headerCard(menu = subMenu)
                    }else{
                        menuCard(menu = subMenu)
                    }

                }
            }
        }else{
            headerCard(menu = menu)
        }

    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun menuCard(menu: BusinessMenu) {
        Card(
            modifier = Modifier
                .height(80.dp)
                .width(180.dp)
                .padding(4.dp), // margin
            backgroundColor = menu.background_color!!.toColor(),
            onClick = { onClickMenu(menu) }
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
                            menu.icon!!, "drawable",
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
                        text = menu.title!!,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun headerCard(menu: BusinessMenu) {
            Row(
                modifier = Modifier
                    .height(32.dp)
                    .padding(horizontal = 8.dp, vertical = 0.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = menu.title!!,
                        color = "#0F172A".toColor(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
    }

    fun onClickMenu(menu:BusinessMenu){
        BusinessHandler.shared().viewModal?.setUpDefaultBusiness()
        (context as? MainActivity)?.setBusinessMenu()
        when(menu.target_screen){
            "fragment_business_sale"-> AppNavigator.shared().navigateToSale()
            "business_invoice" -> AppNavigator.shared().navigateToInvoices()
            "inventory_product" -> AppNavigator.shared().navigateToInventoryProduct()
            "inventory_category" -> AppNavigator.shared().navigateToInventoryCategory()
            "inventory_sub_category" -> AppNavigator.shared().navigateToInventorySubCategory()
            "business_stock" -> AppNavigator.shared().navigateToBusinessStock()
            "my_business_profile" -> AppNavigator.shared().navigateToMyBusinessProfile()
            "business_dashboard" -> AppNavigator.shared().navigateToBusinessDashboard()
            "business_employee" -> AppNavigator.shared().navigateToEmployeeList()
            "business_customers" -> AppNavigator.shared().navigateToCustomerList()
            "business_list" -> AppNavigator.shared().navigateToBusinessList()

        }
    }


}

fun String.toColor() = Color(android.graphics.Color.parseColor(this))