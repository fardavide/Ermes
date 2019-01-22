package studio.forface.ermes.servicefactory

import studio.forface.ermes.api.CallAdapter
import java.lang.reflect.Proxy
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.kotlinFunction

/** Platform dependant creation of Proxy */
@PublishedApi
internal actual inline fun <reified S : Any> ServiceFactory.makeProxy(
    callAdapter: CallAdapter,
    functionInvocationHandlers: Map<KFunction<*>, FunctionWorker>,
    httpCallInvoker: HttpCallInvoker
): S {
    return Proxy.newProxyInstance(
        S::class.java.classLoader,
        arrayOf<Class<*>>( S::class.java )
    ) { _, method, args ->

        val functionWorker = functionInvocationHandlers[method.kotlinFunction]!!
        callAdapter.wrapCall { httpCallInvoker<String>( functionWorker( args ) ) }

    } as S
}