package com.compose.tips.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.runtime.Composable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HandlingStatusBarHeight() {
    val statusBarHeight =
        WindowInsets.statusBarsIgnoringVisibility.asPaddingValues().calculateTopPadding()
}
