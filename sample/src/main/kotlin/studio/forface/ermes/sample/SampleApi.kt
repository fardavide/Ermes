package studio.forface.ermes.sample

import io.ktor.client.HttpClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.servicebuilder.service

suspend fun main() = runBlocking {
    val url = "http://4face.studio"

    // Example #1 - Explicit Service declaration ( property delegation ) via an implementation fo ErmesApi
    class SampleApi( baseUrl: String, override val client: HttpClient = HttpClient() ) : ErmesApi( baseUrl ) {
        val sampleService by service<SampleService>()
    }
    val api1 = SampleApi( url, HttpClient { expectSuccess = false } )

    // Example #2 - Implicit Service declaration via an instantiation of ErmesApi through default constructor
    val api2 = ErmesApi( url, logging = false )

    // Example #3 - Implicit Service declaration via an instantiation of ErmesApi through DSL
    val api3 = ErmesApi( url ) {
        logging = false
    }

    // Example #4 - Same as Example #3 but with baseUrl declared via DSL
    val api4 = ErmesApi {
        baseUrl = url
        logging = false
        client { expectSuccess = false }
    }

    // Example #1
    val result1 = api1.sampleService.string()

    // Example #(2,3,4)
    val result2 = api2<SampleService>().string()

    println( result1.await() )
    println( result2.await() )
}

@ApiService( endpoint = "samples" )
interface SampleService {
    fun string(): Deferred<String>
}