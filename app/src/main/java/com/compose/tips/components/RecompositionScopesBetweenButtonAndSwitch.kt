package com.compose.tips.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun RecompositionScopesBetweenButtonAndSwitch() {
    Column {
        var checkedButton by remember { mutableStateOf(false) }
        var checkedSwitch by remember { mutableStateOf(false) }

        Button(onClick = { checkedButton = !checkedButton }) {
            Text(text = checkedButton.toString())
        }

        Switch(
            checked = checkedSwitch,
            onCheckedChange = { checkedSwitch = !checkedSwitch }
        )

        MyList(List(10) { it.toString() })
    }
}

@Composable
private fun MyList(values: List<String>) {
    Log.i("LOG_TAG", "My List: $values")
}