package studio.forface.ermes.entities

import java.lang.reflect.Method

/** A function for create [FunctionIdentifier] by a [Method] */
internal val Method.identifier get()=
    FunctionIdentifier( name, parameterTypes.map { it.kotlin.qualifiedName!! } )