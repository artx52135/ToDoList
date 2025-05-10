package ru.aramazanov.todolist.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDetailsScreen(id: Int?) {
    val item = todoItems.find { it.id == id }

    if (item != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = item.isChecked,
                    onCheckedChange = null,
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(0xFF1C94F9)
                    )
                )
                Text(
                    text = "Status: ${if (item.isChecked) "Completed" else "Unfinished"}",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Title: ${item.title}",
                fontSize = 20.sp,
                textDecoration = if (item.isChecked) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description: ${item.description}",
                fontSize = 16.sp,
                textDecoration = if (item.isChecked) TextDecoration.LineThrough else TextDecoration.None
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { NavControllerHolder.navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1C94F9),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Back to List")
            }
        }
    }
}