package ru.aramazanov.todolist.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TodoListScreen() {
    var checkedCount by remember { mutableStateOf(todoItems.count { it.isChecked }) }

    fun updateCheckedCount() {
        checkedCount = todoItems.count { it.isChecked }
    }


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
                            updateCheckedCount()
                        }
                    },
                    onDelete = {
                        val index = todoItems.indexOfFirst { it.id == item.id }
                        if (index != -1) {
                            todoItems.removeAt(index)
                            updateCheckedCount()
                        }
                    },
                    onClick = {
                        NavControllerHolder.navController.navigate("item_details/${item.id}")
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            onClick = { NavControllerHolder.navController.navigate("add_item") },
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
    TodoItem(1, "Wash the dishes", "Description wash the dishes", false),
    TodoItem(2, "Clone this repo", "Description clone this repo", false),
    TodoItem(3, "cd the folder", "description cd the folder", false),
    TodoItem(4, "npm install", "Description npm install", false),
    TodoItem(5, "npm run dev", "Description npm run dev", false)
) //View Model