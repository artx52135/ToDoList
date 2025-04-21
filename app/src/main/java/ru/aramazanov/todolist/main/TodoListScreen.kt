package ru.aramazanov.todolist.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("UnrememberedMutableState")
@Composable
fun TodoListScreen(navController: NavHostController) {
    val checkedCount by derivedStateOf { todoItems.count { it.isChecked } }

    Column(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF1C94F9))
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
                {
                Text(
                    text = "To do list",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Total: ${todoItems.size} - Checked: $checkedCount",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todoItems) { item ->
                TodoItemRow(
                    item = item,
                    onCheckChange = { isChecked ->
                        val index = todoItems.indexOfFirst { it.id == item.id }
                        if (index != -1) {
                            todoItems[index] = todoItems[index].copy(isChecked = isChecked)
                        }
                    },
                    onDelete = {
                        val index = todoItems.indexOfFirst { it.id == item.id }
                        if (index != -1) {
                            todoItems.removeAt(index)
                        }
                    },
                    onClick = {
                        navController.navigate("item_details/${item.id}")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            onClick = { navController.navigate("add_item") },
            containerColor = Color(0xFF1C94F9),
            contentColor = Color.White,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

data class TodoItem(val id: Int, val title: String, val description: String, var isChecked: Boolean)

val todoItems = mutableStateListOf(
    TodoItem(1, "Wash the dishes", "Description wash the dishes", true),
    TodoItem(2, "Clone this repo", "Description clone this repo", false),
    TodoItem(3, "cd the folder", "description cd the folder", false),
    TodoItem(4, "npm install", "Description npm install", false),
    TodoItem(5, "npm run dev", "Description npm run dev", false)
)