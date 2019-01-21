## Basic example

##### Declaration:

```kotlin
@ApiService( endpoint = "posts" )
interface SampleService {
    @Get
    fun string( @Query( "userId" ) userId: Int ): Deferred<String>
}

val url = "https://jsonplaceholder.typicode.com"
```

##### Usage example #1

###### Explicit Service declaration ( property delegation ) via an implementation fo ErmesApi

```kotlin
class SampleApi( 
    baseUrl: String, override val client: HttpClient = HttpClient()
) : ErmesApi( baseUrl ) {
    
    val sampleService by service<SampleService>()
}

val api = SampleApi( url, HttpClient { expectSuccess = false } )

println( api.sampleService.string( userId = 1 ).await() )
```

##### Usage example #2

###### Implicit Service declaration via an instantiation of ErmesApi through default constructor

```kotlin
val api = ErmesApi( url, logging = false )

println( api<SampleService>.string( userId = 1 ).await() )
```

##### Usage example #3

###### Implicit Service declaration via an instantiation of ErmesApi through DSL

```kotlin
val api = ErmesApi( url ) {
    logging = false
    callAdapter = DeferredCallAdapter
}

println( api<SampleService>.string( userId = 1 ).await() )
```

##### Usage example #4

###### Same as Example #3 but with baseUrl declared via DSL

```kotlin
val api4 = ErmesApi {
    baseUrl = url
    logging = false
    client { expectSuccess = false }
}

println( api<SampleService>.string( userId = 1 ).await() )
```