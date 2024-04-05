package com.manoj.meals.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.manoj.meals.ui.details.MealsDetailsScreen
import com.manoj.meals.ui.details.MealsDetailsViewModel
import com.manoj.meals.ui.meals.MealsCategoryScreen
import com.manoj.meals.ui.meals.MealsCategoryViewModel
import com.manoj.meals.ui.theme.MealsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealsApp()
                }
            }
        }
    }

}


@Composable
fun MealsApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "meals_category_screen") {
        composable(route = "meals_category_screen") {
            MealsCategoryScreen {
                navController.navigate("meals_details_screen/$it")
            }
        }
        composable(route = "meals_details_screen/{mealCategoryId}", arguments = listOf(
            navArgument("mealCategoryId") {
                type = NavType.StringType
            }
        )) {
            val viewModel: MealsDetailsViewModel = hiltViewModel()
            MealsDetailsScreen(mealsCategory = viewModel.mealsState.value)
        }
    }


}