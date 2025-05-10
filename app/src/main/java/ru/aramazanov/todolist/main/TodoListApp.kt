package ru.aramazanov.todolist.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

object NavControllerHolder {
    lateinit var navController: NavHostController
}
@Composable
fun TodoListApp() {
    NavControllerHolder.navController = rememberNavController()
    NavHost(
        navController = NavControllerHolder.navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") { TodoListScreen() }
        composable("item_details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ItemDetailsScreen(id)
        }
        composable("add_item") { AddItemScreen() }
    }
}