package studio.forface.ermes.annotations

/**
 * @author Davide Giuseppe Farella.
 * TODO doc
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiService( val apiVersion: ApiVersion, val endpoint: String )

enum class ApiVersion ( private val path: String ) {
    V3("3"),
    V4("4");

    override fun toString() = path
}