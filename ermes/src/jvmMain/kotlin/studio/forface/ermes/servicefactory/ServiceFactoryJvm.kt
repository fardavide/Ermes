package studio.forface.ermes.servicefactory

import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import studio.forface.ermes.calladapters.CallAdapter
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

        val kFunction = method.kotlinFunction!!
        val functionWorker = functionInvocationHandlers[kFunction]!!

        callAdapter.wrapCall {
            val result = httpCallInvoker<HttpResponse>( functionWorker( args ) )

            result.readText()
        }
    } as S
}