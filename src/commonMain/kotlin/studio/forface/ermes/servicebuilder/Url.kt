package studio.forface.ermes.servicebuilder

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

    /** @see String */
    override fun toString(): String = s

    /** @see String.hashCode */
    override fun hashCode(): Int = s.hashCode()
}