package studio.forface.ermes.entities

import java.lang.reflect.Method
import kotlin.reflect.jvm.kotlinFunction

/** A function for create [FunctionIdentifier] by a [Method] */
internal val Method.identifier get()= kotlinFunction!!.identifier