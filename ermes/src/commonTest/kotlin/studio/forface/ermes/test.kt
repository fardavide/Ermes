package studio.forface.ermes

import io.mockk.coEvery
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

val testApi = TestApi()

class InvalidBaseUrlTestApi : ErmesApi("invalidBaseUrl" )

class TestApi : ErmesApi("http://4face.studio" ) {
    val noApiServiceAnnotationService by service<NoApiServiceAnnotationService>()
    val noEndpointService by service<NoEndpointService>()
}

interface NoApiServiceAnnotationService

@ApiService
interface NoEndpointService

@ApiService("endpoint" )
interface Service {

    suspend fun strings(): List<String>

    suspend fun test(): Int {
        coEvery {  } coAnswers
    }
}