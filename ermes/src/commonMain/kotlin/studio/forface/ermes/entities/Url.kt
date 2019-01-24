@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package studio.forface.ermes.entities

import studio.forface.ermes.exceptions.InvalidUrlException
import studio.forface.ermes.utils.appendIfNotEmpty
import studio.forface.ermes.utils.appendIfNotNull
import studio.forface.ermes.utils.set

/**
 * @author Davide Giuseppe Farella.
 * A custom lightweight class for an Url.
 */
data class Url internal constructor(
    private val base: String,
    private var endpoint: Endpoint? = null,
    private val queries: MutableMap<String, String> = mutableMapOf(),
    private val paths: MutableList<UrlPath> = mutableListOf()

) {
    /** @return a new [Url] with the given [Endpoint] */
    internal operator fun plus( endpoint: Endpoint? ) = copy( endpoint = endpoint )

    /** Set the given [Endpoint] to [Url.endpoint] */
    internal operator fun plusAssign( endpoint: Endpoint? ) {
        this.endpoint = endpoint
    }

    /** @return a new [Url] with the given [UrlPath] */
    internal operator fun plus( path: UrlPath ) = copy().apply copy@ { this@copy += path }

    /** Add the given [UrlPath] to [Url.paths] */
    internal operator fun plusAssign( path: UrlPath ) {
        this.paths.add( path )
    }

    /** @return a new [Url] with the given [String] *path* */
    internal operator fun plus( path: String ) = copy().apply copy@ { this@copy += path }

    /** Add the given [String] to [Url.paths] */
    internal operator fun plusAssign( path: String ) {
        this.paths.add( UrlPath( path ) )
    }

    /** @return a new [Url] with the given [UrlQuery] */
    operator fun plus( query: UrlQuery ) = copy().apply copy@ { this@copy += query }

    /** Add the given [UrlQuery] to [Url.queries] */
    operator fun plusAssign( query: UrlQuery ) {
        this.queries[query.first] = query.second
    }

    /** Replace with the given [old] [UrlPath] with a [new] [UrlPath] in [Url.paths] */
    internal operator fun set( old: UrlPath, new: UrlPath ) {
        this.paths[old] = new
    }

    /** Replace with the given [oldPath] [String] *path* with a [newPath] [String] *path* in [Url.paths] */
    internal operator fun set( oldPath: String, newPath: String ) {
        this.paths[UrlPath( oldPath )] = UrlPath( newPath )
    }

    /** @return a [String] url with the available params */
    override fun toString(): String {
        return StringBuilder( base )
            .appendIfNotNull( endpoint, prefix = "/" )
            .appendIfNotEmpty( paths, prefix = "/", separator = "/" )
            .appendIfNotEmpty( queries, prefix = "?", separator = "&", joiner = "=" )
            .toString()
    }

    /**
     * @return this [Url] if valid, else
     * @throws InvalidUrlException
     */
    internal fun validateOrThrow() : Url {
        when {
            base.isBlank() -> throw InvalidUrlException( "No Url has been set" )

            !base.startsWith("http://" ) && !base.startsWith( "https://" ) ->
                throw InvalidUrlException( "Url should start with 'http' or 'https''" )

            !base.contains('.' ) -> throw InvalidUrlException( "An Url should contain at least a dot ( '.' )" )
        }
        return this
    }

    /** Override of [equals] using the [String] generate from [toString] */
   override fun equals( other: Any? )= other.toString() == toString()

    /** Override of [hashCode] using the [String] generate from [toString] */
   override fun hashCode() = toString().hashCode()
}

/** A lightweight class for a [String] endpoint */
internal inline class Endpoint( private val s: String ) {
    /** @see String */
    override fun toString(): String = s
}

/** A lightweight class for a [String] path */
internal inline class UrlPath( private val s: String ) {
    /** @see String */
    override fun toString(): String = s
}

/** A typealias for a [Pair] of [String]s UrlQuery */
typealias UrlQuery = Pair<String, String>