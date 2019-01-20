package studio.forface.ermes.exceptions

import kotlinx.coroutines.Deferred
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.entities.Url
import studio.forface.ermes.servicebuilder.ServiceFactory
import studio.forface.ermes.utils.EMPTY_STRING
import kotlin.reflect.KClass
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
class MissingModifierException( modifier: String, append: String = EMPTY_STRING ):
    Exception( "Missing required modifier '$modifier' $append" )

/** An [IllegalReturnTypeException] about a required [Deferred] */
class RequireDeferredException( kClass: KClass<*>, kFun: KFunction<*> ): IllegalReturnTypeException(
    "${ServiceFactory::class.simpleName} require that every function for every ${ApiService::class.simpleName} " +
            "interfaces returns a '${Deferred::class.qualifiedName}'. '${kFun.name}' in '${kClass.qualifiedName}' " +
            "returns a '${kFun.returnType}'"
)