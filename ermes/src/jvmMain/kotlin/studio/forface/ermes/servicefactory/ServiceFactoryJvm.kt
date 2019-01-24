package studio.forface.ermes.servicefactory

import io.ktor.client.response.HttpResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializerByTypeToken
import java.lang.reflect.Proxy
import java.lang.reflect.Type
import kotlin.collections.set
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.kotlinFunction

/** Platform dependant creation of Proxy */
@PublishedApi
internal actual inline fun <reified S : Any> ServiceFactory.makeProxy(
    functionInvocationHandlers: Map<KFunction<*>, FunctionWorker>,
    httpCallInvoker: HttpCallInvoker
): S {
    return Proxy.newProxyInstance(
        S::class.java.classLoader,
        arrayOf<Class<*>>( S::class.java )
    ) { _, method, args ->

        val kFunction = method.kotlinFunction!!
        val functionWorker = functionInvocationHandlers[kFunction]!!

        runBlocking {
            callAdapter.wrapCall {
                val baseParams = functionWorker( args )
                val authenticatedParams = authenticator( baseParams, identifier )
                val result = httpCallInvoker<HttpResponse>( authenticatedParams )

                val returnType = kFunction.unwrapReturnType()
                converter( result, returnType )
            }
        }
    } as S
}

// TODO temporary implementation
@PublishedApi
internal object KSerializersCache {

    private val strategies = mutableMapOf<Type, KSerializer<Any>>()

    operator fun invoke( type: Type ) : KSerializer<Any> {
        return strategies[type] ?: kotlin.run {
            val strategy = serializerByTypeToken( type )
            strategies[type] = strategy
            strategy
        }
    }
}