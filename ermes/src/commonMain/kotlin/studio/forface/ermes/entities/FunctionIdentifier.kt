package studio.forface.ermes.entities

import studio.forface.ermes.utils.valueParameters
import kotlin.reflect.KFunction

/**
 * @author Davide Giuseppe Farella
 * A class for identify a function through platforms
 */
internal class FunctionIdentifier( val name: String, val argsType: List<String> ) {

    /** Check [other] is [FunctionIdentifier] and has same [name] and [argsType] */
    override fun equals( other: Any? ) =
        other is FunctionIdentifier && other.name == name && other.argsType == argsType

    /** Sum [name] [hashCode] plus all the [argsType]s [hashCode] */
    override fun hashCode() =
        name.hashCode() + argsType.map { it.hashCode() }.reduce { acc, next -> acc + next }

    override fun toString() = "$name: ${argsType.joinToString() }"
}

/** A function for create [FunctionIdentifier] by a [KFunction] */
internal val KFunction<*>.identifier get()=
    FunctionIdentifier( name, valueParameters.map { it.type.toString() } )