package studio.forface.ermes.exceptions

import kotlinx.coroutines.Deferred
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.entities.Url
import studio.forface.ermes.servicefactory.ServiceFactory
import studio.forface.ermes.utils.EMPTY_STRING
import studio.forface.ermes.utils.instanceParameter
import kotlin.reflect.KFunction

/* Author: Davide Giuseppe Farella */

/** An [Exception] about a wrong annotation */
class IllegalAnnotationException( message: String ): Exception( message )

/** An [Exception] about a wrong return type */
open class IllegalReturnTypeException( message: String ): Exception( message )

/** An [Exception] about an invalid [Url] */
class InvalidUrlException( message: String ): Exception( message )

/** An [Exception] about a missing annotation */
class MissingAnnotationException( message: String ): Exception( message )

/** An [Exception] about a missing modifier */
open class MissingModifierException( modifier: String, append: String = EMPTY_STRING ):
    Exception( "Missing required modifier '$modifier' $append" )

/** An [IllegalReturnTypeException] about a required [Deferred] */
class RequireDeferredException( kFun: KFunction<*> ): IllegalReturnTypeException(
    "${ServiceFactory::class.simpleName} requires that every function for every ${ApiService::class.simpleName} " +
            "interfaces returns a '${Deferred::class.qualifiedName}'. '${kFun.name}' in " +
            "'${kFun.instanceParameter?.name}' returns a '${kFun.returnType}'"
)

/** A [MissingModifierException] about a required `suspend` function */
class RequireSuspendException( kFun: KFunction<*> ): MissingModifierException( "suspend",
    "${ServiceFactory::class.simpleName} requires that every function for every ${ApiService::class.simpleName} " +
            "interfaces returns a suspend function. '${kFun.name}' in '${kFun.instanceParameter?.name}' does not"
)