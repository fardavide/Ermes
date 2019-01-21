package studio.forface.ermes.annotations

import io.ktor.http.HttpMethod
import studio.forface.ermes.entities.UrlPath
import studio.forface.ermes.exceptions.MissingAnnotationException
import studio.forface.ermes.utils.EMPTY_STRING
import studio.forface.ermes.utils.findAnnotation
import kotlin.reflect.KFunction

/* Author: Davide Giuseppe Farella */

/** An annotation for DELETE method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Delete( val path: String = EMPTY_STRING )

/** An annotation for GET method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Get( val path: String = EMPTY_STRING )

/** An annotation for POST method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Post( val path: String = EMPTY_STRING )

/** An annotation for PUT method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Put( val path: String = EMPTY_STRING )


internal sealed class ApiMethod( path: String, val httpMethod: HttpMethod ) {
    val path = UrlPath( path )

    class DELETE( s: String ) : ApiMethod( s, HttpMethod.Delete )
    class GET(    s: String ) : ApiMethod( s, HttpMethod.Get )
    class POST(   s: String ) : ApiMethod( s, HttpMethod.Post )
    class PUT(    s: String ) : ApiMethod( s, HttpMethod.Put )
}

/**
 * Find an [ApiMethod] annotation for the given [KFunction].
 * @return [ApiMethod].
 * @throws MissingAnnotationException if no annotation is found.
 */
internal val KFunction<*>.apiMethod: ApiMethod get() {
    return findAnnotation<Delete>()?.let { ApiMethod.DELETE( it.path ) }
        ?: findAnnotation<Get>()   ?.let { ApiMethod.GET(    it.path ) }
        ?: findAnnotation<Post>()  ?.let { ApiMethod.POST(   it.path ) }
        ?: findAnnotation<Put>()   ?.let { ApiMethod.PUT(    it.path ) }

        ?: throw MissingAnnotationException( "No ${ApiMethod::class.qualifiedName} annotation found for function: $name" )
}