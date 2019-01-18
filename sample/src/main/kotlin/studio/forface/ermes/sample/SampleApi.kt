package studio.forface.ermes.sample

import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

class SampleApi( override val baseUrl: String ) : ErmesApi() {

    val sampleService by service<SampleService>()
}

interface SampleService {

}