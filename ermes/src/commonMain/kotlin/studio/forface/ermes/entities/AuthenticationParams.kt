package studio.forface.ermes.entities

/**
 * @author Davide Giuseppe Farella
 * An entity containing param for authentication
 */
data class AuthenticationParams(
    val url: Url,
    val headers: Headers
) {

    /** A secondary constructor with vararg [headers] */
    constructor( url: Url, vararg headers: Header ): this( url, headers.toMutableList() )
}

/** A typealias for a [Pair] of [String]s Header */
typealias Header = Pair<String, String>

/** A typealias for a [MutableList] of [Header]s */
typealias Headers = MutableList<Header>