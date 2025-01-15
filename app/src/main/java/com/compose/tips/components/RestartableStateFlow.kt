package com.compose.tips.components

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.stateIn

//class MainViewModel @Inject constructor(
//    repository: TimelineRepository
//): ViewModel() {
//
//    val timelineUi: RestartableStateFlow<ScreenUi?> = repository.fetchTimelineUi()
//        .flatMapLatest { response -> flowOf(response.getOrNull()) }
//        .restartableStateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )
//
//    fun restartTimeline() {
//        timelineUi.restart()
//    }
//}

interface RestartableStateFlow<out T> : StateFlow<T> {
    fun restart()
}

interface SharingRestartable : SharingStarted {
    fun restart()
}

fun SharingStarted.makeRestartable(): SharingRestartable {
    return SharingRestartableImpl(this)
}

private data class SharingRestartableImpl(
    private val sharingStarted: SharingStarted,
) : SharingRestartable {

    private val restartFlow = MutableSharedFlow<SharingCommand>(extraBufferCapacity = 2)

    override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> {
        return merge(restartFlow, sharingStarted.command(subscriptionCount))
    }

    override fun restart() {
        restartFlow.tryEmit(SharingCommand.STOP_AND_RESET_REPLAY_CACHE)
        restartFlow.tryEmit(SharingCommand.START)
    }
}

fun <T> Flow<T>.restartableStateIn(
    scope: CoroutineScope,
    started: SharingStarted,
    initialValue: T
): RestartableStateFlow<T> {
    val sharingRestartable = started.makeRestartable()
    val stateFlow = stateIn(scope, sharingRestartable, initialValue)
    return object : RestartableStateFlow<T>, StateFlow<T> by stateFlow {
        override fun restart() = sharingRestartable.restart()
    }
}