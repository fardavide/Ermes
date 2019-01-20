package studio.forface.ermes.entities

import studio.forface.ermes.exceptions.InvalidUrlException

/**
 * @author Davide Giuseppe Farella.
 * A custom lightweight class for an Url.
 */
internal class Url( private var s: String ) {

    /**
     * A [Boolean] for keep track whether a query is present, for choose if a new one should be concatenated with
     * "?" of "&"
     *
     * @see addQuery
     */
    private var hasQuery = false

    /**
     * Add a Query if [value] is not null.
     *
     * @param name the name of the query.
     * @param value the value of the query.
     *
     * @see hasQuery
     */
    fun addQuery( name: String, value: String? ) {
        value ?: return

        val conjunction =
                if ( ! hasQuery ) "?".also { hasQuery = true }
                else "&"

        plusAssign("$conjunction$name=$value" )
    }

    /** Append an [Endpoint] to the Url, if not null */
    operator fun plus( endpoint: Endpoint? ) = apply {
        endpoint?.let { plusAssign("/$it" ) }
    }

    /** @see String.plus */
    private operator fun plusAssign( other: String ) {
        s += other
    }

    /**
     * Set a path value.
     *
     * @param old the [String] actually in [s]
     * @param new the [String] that will replace [old] in [s]
     *
     * Eg:  > "http://website.it/news/newsId" -> "http://website.it/news/500"
     */
    fun setPath( old: String, new: String ) {
        s = s.replace( old, new )
    }

    /**
     * @return this [Url] if valid, else
     * @throws InvalidUrlException
     */
    internal fun validateOrThrow() : Url {
        when {
            s.isBlank() -> throw InvalidUrlException( "No Url has been set" )

            !s.startsWith("http://" ) && !s.startsWith( "https://" ) ->
                throw InvalidUrlException( "Url should start with 'http' or 'https''" )

            !s.contains('.' ) -> throw InvalidUrlException( "An Url should contain at least a dot ( '.' )" )
        }
        return this
    }

    /** @see Any.equals */
    override operator fun equals( other: Any? ): Boolean {
        return when ( other ) {
            is String -> s == other
            is Url -> this.s == other.s
            else -> false
        }
    }

    /** @see String.hashCode */
    override fun hashCode(): Int = s.hashCode()

    /** @see String */
    override fun toString(): String = s
}