package com.tizzone.getaround.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tizzone.getaround.presentation.route.Screen
import com.tizzone.getaround.presentation.theme.GetaroundTheme
import com.tizzone.getaround.presentation.ui.CarsViewModel
import com.tizzone.getaround.presentation.ui.screens.CarListScreen
import com.tizzone.getaround.presentation.ui.screens.CardDetailsScreen
import com.tizzone.getaround.utils.CARS_VIEWMODEL
import com.tizzone.getaround.utils.CAR_INDEX
import com.tizzone.getaround.utils.CAR_INDEX_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GetaroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CarsList.route
                    ) {
                        composable(route = Screen.CarsList.route) { navBackStackEntry ->
                            val factory =
                                HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                            val viewModel: CarsViewModel =
                                viewModel(key = CARS_VIEWMODEL, factory = factory)
                            CarListScreen(
                                viewModel = viewModel,
                                onNavigateToCarDetail = navController::navigate
                            )
                        }
                        composable(
                            route = Screen.CarDetail.route + CAR_INDEX_URL,
                            arguments = listOf(
                                navArgument(CAR_INDEX) {
                                    type = NavType.IntType
                                }
                            )
                        ) { navBackStackEntry ->
                            val factory =
                                HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                            val viewModel: CarsViewModel =
                                viewModel(key = CARS_VIEWMODEL, factory = factory)
                            CardDetailsScreen(
                                viewModel = viewModel,
                                index = navBackStackEntry.arguments?.getInt(CAR_INDEX),
                                navigateUp = { navController.popBackStack() },
                            )
                        }
                    }
                }
            }
        }
    }
}
