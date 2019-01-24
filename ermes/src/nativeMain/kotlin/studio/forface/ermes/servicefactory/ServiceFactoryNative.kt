package studio.forface.ermes.servicefactory

import kotlin.reflect.KFunction

/** Platform dependant creation of Proxy */
@PublishedApi
internal actual inline fun <reified S : Any> ServiceFactory.makeProxy(
    functionInvocationHandlers: Map<KFunction<*>, FunctionWorker>,
    httpCallInvoker: HttpCallInvoker
): S = TODO("not implemented" )