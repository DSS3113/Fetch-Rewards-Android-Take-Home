package com.example.fetchTakeHome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun HeaderText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Color.White,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun TableText(text: String, modifier: Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = Color.Black,
            fontWeight = FontWeight.Bold
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun ItemList(viewModel: MainViewModel) {
    val items by viewModel.items.collectAsState()
    val groupedItems = items.groupBy { item -> item.listId }

    Column(
        Modifier.padding(5.dp, 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {

            HeaderText("id", Modifier.weight(1f))
            HeaderText("listId", Modifier.weight(1f))
            HeaderText("name", Modifier.weight(1f))
        }
    }
    LazyColumn(
        Modifier.padding(5.dp, 5.dp)
    ) {
        items(groupedItems.keys.sorted()) { listId ->
            val itemSortedByName = groupedItems[listId]!!.sortedBy { item -> item.name }

            itemSortedByName.forEach { item ->
                if(!item.name.isNullOrBlank()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        TableText(item.id.toString(), Modifier.weight(1f))
                        TableText(item.listId.toString(), Modifier.weight(1f))
                        TableText(item.name, Modifier.weight(1f))
                    }
                }
            }
        }
    }
    DisposableEffect(Unit) {
        viewModel.getItems()
        onDispose {}
    }
}
