package studio.forface.ermes.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import studio.forface.ermes.exceptions.RequireDeferredException
import studio.forface.ermes.exceptions.RequireSuspendException
import kotlin.reflect.KFunction

/**
 * @author Davide Giuseppe Farella.
 * An interface for an adapter for web calls.
 * It will check that all the [KFunction] are valid for the adapter and wrap the [KFunction] invocation properly.
 */
interface CallAdapter {

    /** Assert that given [KFunction] is valid */
    fun assertValidFunction( kFun: KFunction<*> )

    // TODO
    fun wrapCall( call: () -> Any ): suspend () -> Any
}

/** A [CallAdapter] for Coroutines [Deferred] */
object DeferredCallAdapter : CallAdapter {

    /** @throws RequireDeferredException if the given [KFunction] doesn't return a [Deferred] */
    override fun assertValidFunction( kFun: KFunction<*> ) {
        if ( kFun.returnType.classifier != Deferred::class ) throw RequireDeferredException( kFun )
    }

    override fun wrapCall( call: () -> Any ) = suspend { GlobalScope.async { call() } }
}

/** A [CallAdapter] for suspend functions */
object SuspendCallAdapter : CallAdapter {

    /** @throws RequireSuspendException if the given [KFunction] is not suspend */
    override fun assertValidFunction( kFun: KFunction<*> ) {
        if ( ! kFun.isSuspend ) throw RequireSuspendException( kFun )
    }

    override fun wrapCall( call: () -> Any ): suspend () -> Any = { call }
}