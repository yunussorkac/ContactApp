package com.app.contact

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.app.contact.screen.AddContactScreen
import com.app.contact.screen.DetailScreen
import com.app.contact.screen.HomeScreen
import com.app.contact.ui.Screens
import com.app.contact.ui.theme.ContactAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactAppTheme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    val navController = rememberNavController()


    Box(
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screens.HomeScreen) {

        composable<Screens.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Screens.DetailScreen> {
            val args = it.toRoute<Screens.DetailScreen>()
            DetailScreen(navController,args.id)


        }
        composable<Screens.AddContactScreen> {
            AddContactScreen(navController)
        }

    }
}
