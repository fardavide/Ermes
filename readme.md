## Basic example



#### Setup:

```kotlin
@Serializable
data class Post( val userId: Int, val id: Int, val title: String, val body: String )

val url = "https://jsonplaceholder.typicode.com"
```



#### Service declaration:

###### With Suspend function:

```kotlin
@ApiService( /* endpoint = optionaEndpoint */ )
interface SampleService {
    @Get( "posts" )
    suspend fun posts( @Query( "userId" ) userId: Int ): List<Post>
}
```

###### With Deferred:

```kotlin
@ApiService( /* endpoint = optionaEndpoint */ )
interface SampleService {
    @Get( "posts" )
    fun posts( @Query( "userId" ) userId: Int ): Deferred<List<Post>>
}
```



#### Api creation:

*For use suspend functions, declare* `callAdapter = SuspendCallAdapter` *via DSL or override `override val callAdapter = SuspendCallAdapter`*



##### Usage example #1 ( Suspend )

###### Explicit Service declaration ( property delegation ) via an implementation fo ErmesApi

```kotlin
class SampleApi( 
    baseUrl: String, override val client: HttpClient = HttpClient()
) : ErmesApi( baseUrl ) {
    override val callAdapter = SuspendCallAdapter
    val sampleService by service<SampleService>()
}

val api = SampleApi( url, HttpClient { expectSuccess = false } )

println( api.sampleService.posts( userId = 1 ) )
```



##### Usage example #2 ( Deferred )

###### Implicit Service declaration via an instantiation of ErmesApi through default constructor

```kotlin
val api = ErmesApi( url, logging = false )

println( api<SampleService>.posts( userId = 1 ).await() )
```



##### Usage example #3 ( Suspend )

###### Implicit Service declaration via an instantiation of ErmesApi through DSL

```kotlin
val api = ErmesApi( url ) {
    logging = false
    callAdapter = SuspendCallAdapter
}

println( api<SampleService>.posts( userId = 1 ) )
```



##### Usage example #4 ( Deferred )

###### Same as Example #3 but with baseUrl declared via DSL

```kotlin
val api4 = ErmesApi {
    baseUrl = url
    logging = false
    client { expectSuccess = false }
    // Default value: callAdapter = DeferredCallAdapter
}

println( api<SampleService>.posts( userId = 1 ).await() )
```