package studio.forface.ermes.api

import io.ktor.client.HttpClient
import studio.forface.ermes.servicebuilder.ServiceBuilder
import studio.forface.ermes.servicebuilder.service
import kotlin.properties.ReadOnlyProperty

/**
 * @author Davide Giuseppe Farella.
 * TODO.
 */
abstract class ErmesApi {

    /** A [String] representing the base url for the API */
    abstract val baseUrl : String

    /** The [HttpClient] for the API */
    open val client = HttpClient()

    /** A [Boolean] representing whether the logging should be enabled */
    open val logging = false

    /** An instance of [ServiceBuilder] for create the Services */
    // private val serviceBuilder = ServiceBuilder()

    /**
     * A public reference to [serviceBuilder] for get it from an inline function *only* within a [ReadOnlyProperty]
     * context, while keeping [serviceBuilder] itself private.
     * @see ErmesApi.service
     */
    // @Suppress("unused")
    // val ReadOnlyProperty<ErmesApi, *>.internalServiceBuilder get() = serviceBuilder
}