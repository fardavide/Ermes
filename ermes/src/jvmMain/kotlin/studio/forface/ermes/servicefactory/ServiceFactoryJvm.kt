package studio.forface.ermes.servicefactory

import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readBytes
import io.ktor.client.response.readText
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON
import kotlinx.serialization.parse
import kotlinx.serialization.serializerByTypeToken
import studio.forface.ermes.calladapters.CallAdapter
import studio.forface.ermes.converters.Converter
import java.lang.reflect.Proxy
import java.lang.reflect.Type
import kotlin.collections.Map
import kotlin.collections.mutableMapOf
import kotlin.collections.set
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.kotlinFunction

/** Platform dependant creation of Proxy */
@PublishedApi
internal actual inline fun <reified S : Any> ServiceFactory.makeProxy(
    callAdapter: CallAdapter,
    converter: Converter,
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
            val httpParams = functionWorker( args )
            val result = httpCallInvoker<HttpResponse>( httpParams )

            val returnType = kFunction.unwrapReturnType()
            // when( val classifier = returnType.classifier ) {
            //     HttpResponse::class ->  result
            //     String::class ->        result.readText()
            //     ByteArray::class ->     result.readBytes()
            //     else -> JSON.parse( KSerializersCache( returnType.javaType ), result.readText() )
            // }
            converter( result, returnType )
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