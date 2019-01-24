package studio.forface.ermes

import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.entities.HttpCallParams
import studio.forface.ermes.entities.Url
import studio.forface.ermes.servicefactory.service

expect fun runTest( block: suspend (scope : CoroutineScope) -> Unit )

val testApi = TestApi()

const val testUrl = "http://4face.studio"

internal val testParams = HttpCallParams( HttpMethod.Get, Url( testUrl ), null )

class InvalidBaseUrlTestApi : ErmesApi("invalidBaseUrl" )

class TestApi : ErmesApi(testUrl ) {
    val noApiServiceAnnotationService by service<NoApiServiceAnnotationService>()
    val noDeferredService by service<NoDeferredService>()
    val noEndpointService by service<NoEndpointService>()

    val service by service<Service>()
}

interface NoApiServiceAnnotationService

@ApiService("no_deferred")
interface NoDeferredService {
    suspend fun noDeferredMethod(): Int
}

@ApiService
interface NoEndpointService

@ApiService("endpoint" )
interface Service {
    fun deferrer(): Deferred<Int>
}