package studio.forface.ermes.utils

import kotlin.reflect.KCallable
import kotlin.reflect.KParameter

/** @return the instance [KParameter] of a [KCallable] */
internal actual val KCallable<*>.instanceParameter: KParameter? get() = TODO("Not implemented" )

/** @return the values [KParameter] of a [KCallable] */
internal actual val KCallable<*>.valueParameters: List<KParameter> get() = TODO("Not implemented" )