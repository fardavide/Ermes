package studio.forface.ermes.servicefactory

import io.ktor.client.HttpClient
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.url
import studio.forface.ermes.entities.HttpCallParams

/**
 * @author Davide Giuseppe Farella.
 * A class for invoke Http calls
 */
@PublishedApi
internal class HttpCallInvoker( val client: HttpClient ) {

    /** @return the [T] result of the Http call, generate with [HttpCallParams] */
    suspend inline operator fun <reified T> invoke( params: HttpCallParams ) : T {
        return client.request {
            method = params.httpMethod
            url( params.url.toString() )
            params.headers.forEach { header( it.first, it.second ) }
            params.body?.let { body = it }
        }
    }
}