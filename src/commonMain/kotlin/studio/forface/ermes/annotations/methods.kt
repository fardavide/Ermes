package studio.forface.ermes.annotations

import studio.forface.ermes.utils.EMPTY_STRING

/* Author: Davide Giuseppe Farella */

/** An annotation for DELETE method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DELETE( val s: String = EMPTY_STRING )

/** An annotation for GET method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class GET( val s: String = EMPTY_STRING )

/** An annotation for POST method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class POST( val s: String = EMPTY_STRING )

/** An annotation for PUT method */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PUT( val s: String = EMPTY_STRING )


sealed class ApiMethod( val s: String ) {
    class DELETE( s: String ) : ApiMethod( s )
    class GET(    s: String ) : ApiMethod( s )
    class POST(   s: String ) : ApiMethod( s )
    class PUT(    s: String ) : ApiMethod( s )
}