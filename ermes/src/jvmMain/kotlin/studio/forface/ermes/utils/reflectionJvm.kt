package studio.forface.ermes.utils

import kotlin.reflect.KCallable
import kotlin.reflect.KParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.valueParameters

/** @return the instance [KParameter] of a [KCallable] */
internal actual val KCallable<*>.instanceParameter: KParameter? get() = instanceParameter

/** @return the values [KParameter] of a [KCallable] */
internal actual val KCallable<*>.valueParameters: List<KParameter> get() = valueParameters