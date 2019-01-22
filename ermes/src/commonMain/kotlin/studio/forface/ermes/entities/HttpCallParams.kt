package studio.forface.ermes.entities

import io.ktor.http.HttpMethod

// TODO
@PublishedApi
internal data class HttpCallParams(
    val httpMethod: HttpMethod,
    val url: Url,
    val body: Any?
)