package ru.aramazanov.todolist.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddItemScreen(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFAAE6FD),
                unfocusedContainerColor = Color(0xFFAAE6FD),
                focusedTextColor = Color(0xFF0000FF),
                unfocusedTextColor = Color(0xFF3389F5),
                focusedLabelColor = Color(0xFF0000FF),
                unfocusedLabelColor = Color(0xFF3389F5)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description")},
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFAAE6FD),
                unfocusedContainerColor = Color(0xFFAAE6FD),
                focusedTextColor = Color(0xFF0000FF),
                unfocusedTextColor = Color(0xFF3389F5),
                focusedLabelColor = Color(0xFF0000FF),
                unfocusedLabelColor = Color(0xFF3389F5)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val newItem = TodoItem(
                    id = (todoItems.maxOfOrNull { it.id } ?: 0) + 1,
                    title = title,
                    description = description,
                    isChecked = false
                )
                todoItems.add(newItem)
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1C94F9),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Item")
        }
    }
}