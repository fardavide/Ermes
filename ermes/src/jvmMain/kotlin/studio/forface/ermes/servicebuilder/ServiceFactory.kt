package studio.forface.ermes.servicebuilder

import java.lang.reflect.Proxy

/** Platform dependant creation of Proxy */
// internal actual inline fun <reified S : Any> ServiceFactory<S>.makeProxy(): S {
//     return Proxy.newProxyInstance(
//         S::class.java.classLoader,
//         arrayOf<Class<*>>( S::class.java )
//     ) { _, _, _ -> } as S
// }