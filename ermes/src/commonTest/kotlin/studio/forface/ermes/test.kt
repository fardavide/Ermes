package studio.forface.ermes

import io.mockk.coEvery
import kotlinx.coroutines.Deferred
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

val testApi = TestApi()

class InvalidBaseUrlTestApi : ErmesApi("invalidBaseUrl" )

class TestApi : ErmesApi("http://4face.studio" ) {
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