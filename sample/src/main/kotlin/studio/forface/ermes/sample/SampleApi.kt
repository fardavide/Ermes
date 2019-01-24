package studio.forface.ermes.sample

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import studio.forface.ermes.ErmesApi
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.annotations.Get
import studio.forface.ermes.annotations.Query
import studio.forface.ermes.authenticator.Authenticator
import studio.forface.ermes.calladapters.SuspendCallAdapter
import studio.forface.ermes.entities.AuthenticationParams
import studio.forface.ermes.entities.Url


@ApiService
interface SampleService {
    @Get("posts" )
    suspend fun posts( @Query( "userId" ) userId: Int ): List<Post>
}

@Serializable
data class Post( val userId: Int, val id: Int, val title: String, val body: String )

suspend fun main() = runBlocking {
    val url = "https://jsonplaceholder.typicode.com"
    val api = ErmesApi( url ) {
        callAdapter = SuspendCallAdapter
        authenticator {
            if ( serviceIdentifier == "myOAuthService" )
                headers += "Authorization" to "Bearer $someToken"
        }
    }

    val result = api<SampleService>().posts( userId = 1 )
    println( result )
}

class MyAuth: Authenticator() {
    override fun invoke( url: Url, serviceIdentifier: String) : AuthenticationParams {
        return if ( serviceIdentifier == "myOAuthService" )
            AuthenticationParams( url,"Authorization" to "Bearer $someToken" )
        else super.invoke( url, serviceIdentifier )
    }
}