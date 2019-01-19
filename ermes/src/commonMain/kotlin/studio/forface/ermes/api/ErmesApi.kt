package studio.forface.ermes.api

import io.ktor.client.HttpClient
import studio.forface.ermes.entities.Url
import studio.forface.ermes.exceptions.InvalidUrlException
import studio.forface.ermes.servicebuilder.ServiceFactory
import kotlin.properties.ReadOnlyProperty

/**
 * @author Davide Giuseppe Farella.
 * TODO.
 *
 * @throws InvalidUrlException if the [baseUrl] is not valid
 * @see Url.validateOrThrow
 */
abstract class ErmesApi( baseUrl: String ) {

    /** An [Url] representing the base url for the API */
    internal val baseUrl = Url( baseUrl ).validateOrThrow()

    /** The [HttpClient] for the API */
    open val client = HttpClient()

    /** A [Boolean] representing whether the logging should be enabled */
    open val logging = false

    /** An instance of [ServiceFactory] for create the Services */
    // private val serviceBuilder = ServiceFactory()

    /**
     * A public reference to [serviceBuilder] for get it from an inline function *only* within a [ReadOnlyProperty]
     * context, while keeping [serviceBuilder] itself private.
     * @see ErmesApi.service
     */
    // @Suppress("unused")
    // val ReadOnlyProperty<ErmesApi, *>.internalServiceBuilder get() = serviceBuilder
}