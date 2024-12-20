package com.example.ucp2_075.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ucp2_075.ui.view.HomeAppView
import com.example.ucp2_075.ui.view.supplier.InsertSupView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController, startDestination = DestinasiHome.route
    ) {
        composable(
            route = DestinasiHome.route
        ) { HomeAppView(
            modifier = modifier,
            onNavigateAddSup ={ navController.navigate(DestinasiInsertSup.route)
            }
        )
        }
        composable(
            route = DestinasiInsertSup.route
        ) {
            InsertSupView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                modifier = modifier
            )
        }
    }
}