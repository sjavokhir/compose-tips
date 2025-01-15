package com.compose.tips.components

import androidx.compose.runtime.Composable

/**
 * In [Jetpack Release Note October 30, 2024](https://developer.android.com/jetpack/androidx/versions/all-channel#october_30_2024)
 * introduces that Compose 1.8.0-alpha05 has auto-sizing text, custom overscroll effects,
 * and experimental autofill support.
 */
@Composable
fun AutoSizeTextDemo() {
    val text = "This is a sample string!"

//    Column(Modifier.fillMaxWidth()) {
//        // This text will be sized according to default values of AutoSize.StepBased
//        BasicText(text, autoSize = AutoSize.StepBased())
//
//        // This text can either have a font size of 10, 20, 30, 40, 50 or 60 sp
//        BasicText(
//            text,
//            autoSize =
//            AutoSize.StepBased(minFontSize = 10.sp, maxFontSize = 60.sp, stepSize = 10.sp)
//        )
//    }
}