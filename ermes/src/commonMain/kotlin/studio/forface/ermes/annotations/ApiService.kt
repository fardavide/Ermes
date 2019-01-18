package studio.forface.ermes.annotations

import studio.forface.ermes.utils.EMPTY_STRING

/**
 * @author Davide Giuseppe Farella.
 * TODO doc
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiService( val endpoint: String = EMPTY_STRING )