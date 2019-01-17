package studio.forface.ermes.utils

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post

/*
 * Author: Davide Giuseppe Farella.
 * A file containing utilities for Ktor.
 */

/** Call [HttpClient.post] with [headers] as [HttpRequestBuilder.headers] */
suspend inline fun <reified T> HttpClient.get(
        url: String,
        headers: Map<String, String>
) = get<T>( url ) {
    headers { headers.forEach { append( it.key, it.value ) } }
}

/** Call [HttpClient.post] with [headers] as [HttpRequestBuilder.headers] and  [body] as [HttpRequestBuilder.body] */
suspend inline fun <reified T> HttpClient.post(
        url: String,
        headers: Map<String, String>,
        body: Any
) = post<T>( url ) {
    headers { headers.forEach { append( it.key, it.value ) } }
    this.body = body
}