package ru.aramazanov.todolist.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun TodoListApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") { TodoListScreen(navController) }
        composable("item_details/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            ItemDetailsScreen(navController, id)
        }
        composable("add_item") { AddItemScreen(navController) }
    }
}