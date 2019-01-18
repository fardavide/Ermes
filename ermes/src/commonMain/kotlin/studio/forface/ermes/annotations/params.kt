package studio.forface.ermes.annotations

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


sealed class ApiParam {
    object Body : ApiParam()
    class Field( val s: String ) : ApiParam()
    class Path(  val s: String ) : ApiParam()
    class Query( val s: String ) : ApiParam()
}