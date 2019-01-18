package studio.forface.ermes.utils

import kotlin.reflect.KClass

/** Find from the receiver [KClass] the first annotation that match the [ANNOTATION] param */
inline fun <reified ANNOTATION> KClass<*>.findAnnotation() =
    annotations.find { it is ANNOTATION } as ANNOTATION?