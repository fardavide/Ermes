package studio.forface.ermes.authenticator

import studio.forface.ermes.entities.AuthenticationParams
import studio.forface.ermes.entities.HttpCallParams
import studio.forface.ermes.entities.Url

/**
 * @author Davide Giuseppe Farella
 * A class for authenticate http calls
 */
open class Authenticator( private val builder: AuthenticationParamsBuilder = { } ) {

    /** @return [HttpCallParams] created by merging the given [httpCallParams] and [AuthenticationParams] created */
    internal operator fun invoke( httpCallParams: HttpCallParams, serviceIdentifier: String ) =
            httpCallParams + this( httpCallParams.url, serviceIdentifier )

    /** @return [AuthenticationParams] created with the [builder] from [url] and [serviceIdentifier] */
    open operator fun invoke( url: Url, serviceIdentifier: String ) =
            AuthenticationParams( url, mutableListOf() ).apply { builder( serviceIdentifier ) }
}

/** A default [Authenticator] */
object DefaultAuthenticator : Authenticator()

/**
 * A typealias for a lambda that has [AuthenticationParams] as receiver, a service identifier [String] as param and
 * [Unit] as return type
 */
typealias AuthenticationParamsBuilder = AuthenticationParams.( serviceIdentifier: String ) -> Unit