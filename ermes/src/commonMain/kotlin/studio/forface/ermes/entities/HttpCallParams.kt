package studio.forface.ermes.entities

import io.ktor.http.HttpMethod
import studio.forface.ermes.servicefactory.HttpCallInvoker

/**
 * @author Davide Giuseppe Farella.
 * An entity containing params for http call executed by [HttpCallInvoker]
 */
@PublishedApi
internal data class HttpCallParams(
    val httpMethod: HttpMethod,
    val url: Url,
    val body: Any?,
    val headers: Headers = mutableListOf()
) {

    /** @return a copy of [HttpCallParams] with [url] and [headers] from [AuthenticationParams] */
    @PublishedApi
    internal operator fun plus( authenticationParams: AuthenticationParams ) = copy(
        url = authenticationParams.url,
        headers = authenticationParams.headers
    )
}