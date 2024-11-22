package com.compose.tips.experiments

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

/**
 * Recomposition scopes between Button and Switch
 *
 * The Compose material component Switch can trigger the recomposition of the entire scope of the
 * Composable function, so it must be used judiciously. For instance, in the given example,
 * clicking on the button does not recompose the MyList Composable.
 *
 * This occurs because the Composable lambda parameter of the Button captures the smallest necessary
 * recomposition scope. However, any interaction with the Switch will cause a recomposition
 * each time it is toggled.
 */
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