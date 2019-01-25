package studio.forface.ermes.sample

import kotlinx.serialization.Serializable
import studio.forface.ermes.ErmesApi
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.annotations.Get
import studio.forface.ermes.annotations.Query
import studio.forface.ermes.calladapters.SuspendCallAdapter


@ApiService
interface SampleService {
    @Get("posts" )
    suspend fun posts( @Query( "userId" ) userId: Int ): List<Post>
}

@Serializable
data class Post( val userId: Int, val id: Int, val title: String, val body: String )

suspend fun main() {
    val baseUrl = "https://jsonplaceholder.typicode.com"
    val api = ErmesApi( baseUrl ) {
        callAdapter = SuspendCallAdapter
    }

    val result = api<SampleService>().posts( userId = 2 )
    println( result )
}