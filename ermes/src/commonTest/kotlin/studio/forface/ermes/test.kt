package studio.forface.ermes

import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

val testApi = TestApi()

class InvalidBaseUrlTestApi : ErmesApi() {
    override val baseUrl = "invalidBaseUrl"
}

class TestApi : ErmesApi() {
    override val baseUrl = "http://4face.studio"
    val noApiServiceAnnotationService by service<NoApiServiceAnnotationService>()
    val noEndpointService by service<NoEndpointService>()
}

interface NoApiServiceAnnotationService

@ApiService
interface NoEndpointService

@ApiService("endpoint" )
interface Service