package com.example.navigatiowithargument

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navigatiowithargument.ui.theme.NavigatioWithArgumentTheme
import com.example.navigatiowithargument.ui.theme.HomeScreen
import com.example.navigatiowithargument.ui.theme.OrderDetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigatioWithArgumentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen(
                onNavigateToDetails = { productName, quantity ->
                    navController.navigate(
                        OrderDetails(productName, quantity)
                    )
                }
            )
        }
        composable<OrderDetails> { backStackEntry ->
            val args = backStackEntry.toRoute<OrderDetails>()
            OrderDetailsScreen(
                navController = navController,
                productName = args.productName,
                quantity = args.quantity
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    NavigatioWithArgumentTheme {
        MainScreen()
    }
}
