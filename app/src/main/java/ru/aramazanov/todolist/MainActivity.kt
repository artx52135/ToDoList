package ru.aramazanov.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListApp()
        }
    }
}

data class TodoItem(val id: Int, val title: String, var isChecked: Boolean)

@SuppressLint("UnrememberedMutableState")
@Composable
fun TodoListApp() {
    val todoItems = remember {
        mutableStateListOf(
            TodoItem(1, "Wash the dishes", true),
            TodoItem(2, "Clone this repo", false),
            TodoItem(3, "cd the folder", false),
            TodoItem(4, "npm install", false),
            TodoItem(5, "npm run dev", false)
        )
    }

    val checkedCount by derivedStateOf { todoItems.count { it.isChecked } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "To do list",
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = "Total: ${todoItems.size} - Checked: $checkedCount",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary
            )
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
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD")
        }
    }
}

@Composable
fun TodoItemRow(item: TodoItem, onCheckChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isChecked,
            onCheckedChange = { isChecked ->
                onCheckChange(isChecked)
            }
        )

        Text(
            text = item.title,
            modifier = Modifier.weight(1f),
            textDecoration = if (item.isChecked) TextDecoration.LineThrough else TextDecoration.None
        )

        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}