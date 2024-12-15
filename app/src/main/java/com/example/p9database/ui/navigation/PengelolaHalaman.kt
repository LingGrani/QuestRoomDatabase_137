package com.example.p9database.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.p9database.ui.view.mahasiswa.DestinationInsert
import com.example.p9database.ui.view.mahasiswa.DetailMhsView
import com.example.p9database.ui.view.mahasiswa.HomeMhsView
import com.example.p9database.ui.view.mahasiswa.InsertMhsView
import com.example.p9database.ui.view.mahasiswa.UpdateMhsView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route // Starting screen is HomeMhsView
    ) {
        composable(
            route = DestinasiHome.route
        ) {
            HomeMhsView(
                onDetailClick = { nim ->
                    navController.navigate("${DestinasiDetail.route}/$nim")
                    println("PengelolaHalaman = $nim")
                },
                onAddMhs = {
                    navController.navigate(DestinasiInsert.route) // Navigate to InsertMhsView
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiInsert.route
        ) {
            InsertMhsView(
                onBack = {
                    navController.popBackStack() // Return to previous screen
                },
                onNavigate = {
                    navController.popBackStack() // Go back after insert
                },
                modifier = modifier
            )
        }

        composable(
            DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.nimArg) {
                    type = NavType.StringType
                }
            )
        ) {
            val nim = it.arguments?.getString(DestinasiDetail.nimArg)
            nim?.let { nim ->
                DetailMhsView(
                    onBack = {
                        navController.popBackStack() // Return to previous screen
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdate.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack() // Go back after deletion
                    }
                )
            }
        }

        composable(
            DestinasiUpdate.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiUpdate.nimArg) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateMhsView(
                onBack = {
                    navController.popBackStack() // Return to previous screen
                },
                onNavigate = {
                    navController.popBackStack() // Go back after update
                },
                modifier = modifier
            )
        }
    }
}