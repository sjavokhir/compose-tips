package com.compose.tips.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.neverEqualPolicy
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun DerivedStateOfExample1() {
    var otherSnapshotInt by remember { mutableIntStateOf(0) }
    val constantInt: Int by remember {
        derivedStateOf(
            calculation = {
                // Read some other snapshot state that changes
                println("OTHER SNAPSHOT UPDATED to $otherSnapshotInt")
                0
            },
        )
    }

    OtherComposable(constantInt)

    LaunchedEffect(Unit) {
        while(true) {
            otherSnapshotInt++
            delay(1000)
        }
    }
}

@Composable
private fun OtherComposable(constantInt: Int) {
    Text(text = constantInt.toString())
}

@Composable
private fun DirectComputationExample() {
    val items = remember { mutableStateListOf(1, 2, 3) }

    // Wrapping the lambda with remember
    val addItem = remember {
        { items.add((1..10).random()) }
    }

    Column {
        Button(onClick = { addItem() }) {
            Text(text = "Add Item")
        }

        // Direct computation - recalculates sum on every recomposition
        Text(text = "Sum: ${items.sum()}")
    }
}

@Composable
private fun DerivedStateOfExample() {
    val items = remember { mutableStateListOf(1, 2, 3) }

    // Calculate sum only when items list changes
    val sum by remember {
        derivedStateOf { items.sum() }
    }

    // Wrapping the lambda with remember
    val addItem = remember {
        { items.add((1..10).random()) }
    }

    Column {
        Button(onClick = { addItem() }) {
            Text(text = "Add Item")
        }
        // The sum is only recalculated when items change
        Text(text = "Sum: $sum")
    }
}