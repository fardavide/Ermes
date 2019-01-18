package studio.forface.ermes.servicebuilder

import studio.forface.ermes.exceptions.InvalidUrlException

/**
 * @author Davide Giuseppe Farella.
 * A custom lightweight class for an Url.
 */
class Url( private var s: String ) {

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

    /** @see String.equals */
    override fun equals( other: Any? ): Boolean {
        return s == other
    }

    /** @see String.plus */
    private operator fun plusAssign( other: String ) {
        s += other
    }

    /**
     * @return this [Url] if valid, else
     * @throws InvalidUrlException
     */
    internal fun validateOrThrow() : Url {
        when {
            !s.startsWith("http" ) -> throw InvalidUrlException( "Url should start with 'http' or 'https''" )
            !s.contains('.' ) -> throw InvalidUrlException( "An Url should contain at least a dot ( '.' )" )
        }
        return this
    }

    /** @see String */
    override fun toString(): String = s

    /** @see String.hashCode */
    override fun hashCode(): Int = s.hashCode()
}