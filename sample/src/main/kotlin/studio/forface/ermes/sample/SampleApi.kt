package studio.forface.ermes.sample

import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

class SampleApi( val baseUrl: String ) : ErmesApi( baseUrl ) {

    val sampleService by service<SampleService>()
}

@ApiService( endpoint = "samples" )
interface SampleService {

}