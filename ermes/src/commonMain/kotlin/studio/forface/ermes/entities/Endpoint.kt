package studio.forface.ermes.entities

/**
 * @author Davide Giuseppe Farella
 * A lightweight class for a [String] endpoint
 */
@Suppress("EXPERIMENTAL_FEATURE_WARNING")
internal inline class Endpoint( private val s: String ) {

    /** @see String */
    override fun toString(): String = s
}