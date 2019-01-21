package studio.forface.ermes.utils

import kotlin.reflect.*

/* Author: Davide Giuseppe Farella */

/** Find from the receiver [KAnnotatedElement] the first annotation that match the [ANNOTATION] param */
internal inline fun <reified ANNOTATION> KAnnotatedElement.findAnnotation() =
    annotations.find { it is ANNOTATION } as ANNOTATION?

/** @return the OPTIONAL instance [KParameter] of a [KCallable] */
internal expect val KCallable<*>.instanceParameter: KParameter?

/** @return the values [KParameter] of a [KCallable] */
internal expect val KCallable<*>.valueParameters: List<KParameter>

/** Find from the receiver [KClass] the first annotation that match the [ANNOTATION] param */
internal inline fun <reified ANNOTATION> KClass<*>.findAnnotation() =
    annotations.find { it is ANNOTATION } as ANNOTATION?

/** @return a [List] of the [KClass] [KFunction]s, filtering [equals], [toString] and [hashCode] */
internal val KClass<*>.realFunctions get() = members.mapNotNull { it as? KFunction<*> }
    .filterNot { it.name == "equals" || it.name == "toString" || it.name == "hashCode" }