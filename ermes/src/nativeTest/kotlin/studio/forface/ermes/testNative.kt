package studio.forface.ermes

import kotlinx.coroutines.CoroutineScope

actual fun runTest( block: suspend (scope : CoroutineScope) -> Unit ): Unit = TODO("not implemented" )