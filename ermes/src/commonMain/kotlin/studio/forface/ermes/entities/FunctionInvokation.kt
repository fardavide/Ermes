package studio.forface.ermes.entities

import kotlin.reflect.KFunction

/**
 * @author Davide Giuseppe Farella
 * A typealias for a lambda that from a [FunctionIdentifier] returns its invocation result [Any] ( nullable )
 *
 * I.g. `val result: String = functionInvokation()`
 */
internal class FunctionInvokation( val identifier: FunctionIdentifier, block: () -> Any? )