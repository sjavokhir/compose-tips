package com.compose.tips.experiments

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import com.compose.tips.TAG

@Composable
fun ComposeLifecycleEffect() {
    LifecycleStartEffect(null) {
        onStopOrDispose {
            Log.i(TAG, "onStopOrDispose")
        }
    }

    LifecycleResumeEffect(null) {
        onPauseOrDispose {
            Log.i(TAG, "onPauseOrDispose")
        }
    }
}