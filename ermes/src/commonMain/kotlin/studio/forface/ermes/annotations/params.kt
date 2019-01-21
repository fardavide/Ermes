package studio.forface.ermes.annotations

import studio.forface.ermes.utils.findAnnotation
import kotlin.reflect.KParameter

/*  Author: Davide Giuseppe Farella  */

/** An annotation for Body param */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Body

/** An annotation for Field param */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Field( val name: String )

/** An annotation for Path param */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Path( val path: String )

/** An annotation for Query param */
@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class Query( val query: String )


internal sealed class ApiParam {
    object Body : ApiParam()
    class Field( val s: String ) : ApiParam()
    class Path(  val s: String ) : ApiParam()
    class Query( val s: String ) : ApiParam()
}

/**
 * Find an [ApiParam] annotation for the given [KParameter].
 * If no annotations is found, null will be returned and the [KParameter] will be skipped.
 * @return OPTIONAL [ApiParam].
 */
internal val KParameter.apiParam: ApiParam? get() {
    return findAnnotation<Body>() ?.let { ApiParam.Body }
        ?: findAnnotation<Field>()?.let { ApiParam.Field( it.name ) }
        ?: findAnnotation<Path>() ?.let { ApiParam.Path( it.path ) }
        ?: findAnnotation<Query>()?.let { ApiParam.Query( it.query ) }
}