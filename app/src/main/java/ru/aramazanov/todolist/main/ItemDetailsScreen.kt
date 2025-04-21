package ru.aramazanov.todolist.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ItemDetailsScreen(navController: NavHostController, id: Int?) {
    val item = todoItems.find { it.id == id }

    if (item != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = "Title: ${item.title}", fontSize = 20.sp)
            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )
            Text(text = "Description: ${item.description}", fontSize = 16.sp)
            Spacer(
                modifier = Modifier
                    .height(16.dp)
            )
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C94F9),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to List")
            }
        }
    } else {
        Text("Item not found")
    }
}