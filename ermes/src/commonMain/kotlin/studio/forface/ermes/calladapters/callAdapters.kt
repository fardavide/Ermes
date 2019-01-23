package studio.forface.ermes.calladapters

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import studio.forface.ermes.exceptions.RequireDeferredException
import studio.forface.ermes.exceptions.RequireSuspendException
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter

/**
 * @author Davide Giuseppe Farella.
 * An interface for an adapter for web calls.
 * It will check that all the [KFunction] are valid for the adapter and wrap the [KFunction] invocation properly.
 */
interface CallAdapter {

    /** A [Boolean] representing whether the [CallAdapter] has a wrap type. e.g. `Deferred` */
    val hasWrapType: Boolean

    /** Assert that given [KFunction] is valid */
    fun assertValidFunction( kFun: KFunction<*> )

    /**
     * If [hasWrapType]
     * @return the [KType] wrapped in the [CallAdapter] [KType], from [KFunction.returnType]
     * else
     * @return [KFunction.returnType]
     *
     * i.e. >   `Deferred<List<String>>` -> `List<String>`
     *      >   `List<String>` -> `List<String>`
     */
    fun KFunction<*>.unwrapReturnType() =
        if ( hasWrapType ) returnType.arguments.first().type!! else returnType

    /** Wrap an http call in the proper way */
    fun <T : Any> wrapCall( call: suspend CallAdapter.() -> T ): Any
}

/** A [CallAdapter] for Coroutines [Deferred] */
object DeferredCallAdapter : CallAdapter {

    /** Wrap type is [Deferred] */
    override val hasWrapType = true

    /** @throws RequireDeferredException if the given [KFunction] doesn't return a [Deferred] */
    override fun assertValidFunction( kFun: KFunction<*> ) {
        if ( kFun.returnType.classifier != Deferred::class ) throw RequireDeferredException( kFun )
    }

    /** Wrap an http call into a [Deferred] */
    override fun <T : Any> wrapCall( call: suspend CallAdapter.() -> T ): Any =
        GlobalScope.async { this@DeferredCallAdapter.call() }
}

/** A [CallAdapter] for suspend functions */
internal object SuspendCallAdapter : CallAdapter { //TODO not implemented, make public

    /** No wrap type */
    override val hasWrapType = false

    /** @throws RequireSuspendException if the given [KFunction] is not suspend */
    override fun assertValidFunction( kFun: KFunction<*> ) {
        if ( ! kFun.isSuspend ) throw RequireSuspendException( kFun )
    }

    /** Wrap an http call into a suspend function */
    override fun <T : Any> wrapCall( call: suspend CallAdapter.() -> T ): Any =
        TODO("Find way to call suspend: call()" )
}