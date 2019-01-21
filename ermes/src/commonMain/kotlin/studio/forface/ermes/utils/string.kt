package studio.forface.ermes.utils

/* Author: Davide Giuseppe Farella */

/** En empty [String] "" */
internal const val EMPTY_STRING = ""

/** Append an [Any] to a [StringBuilder] with and optional [prefix] and [suffix] */
internal fun StringBuilder.append(
    csq: Any,
    prefix: CharSequence = EMPTY_STRING,
    suffix: CharSequence = EMPTY_STRING
) = append( "$prefix$csq$suffix" )

/**
 * Append a [Map] to a [StringBuilder] if not empty
 *
 * @param prefix a [CharSequence] that will precede the entire string
 * @param suffix a [CharSequence] that will secede the entire string
 * @param separator a [CharSequence] that will separate every [Map.Entry] of the [map]
 * @param joiner a [CharSequence] that will separate the [Map.Entry.key] to the [Map.Entry.value]
 * @param keyToString a lambda that will map every [Map.Entry.key] to a [String]
 * @param valueToString a lambda that will map every [Map.Entry.value] to a [String]
 */
internal fun <K,V> StringBuilder.appendIfNotEmpty(
    map: Map<K, V>,
    prefix: CharSequence = EMPTY_STRING,
    suffix: CharSequence = EMPTY_STRING,
    separator: CharSequence = ", ",
    joiner: CharSequence = " - ",
    keyToString: (K) -> String = { it.toString() },
    valueToString: (V) -> String = { it.toString() }
): StringBuilder {
    if ( map.isEmpty() ) return this
    val mapToString = map.toList().joinToString( separator = separator ) { pair ->
        "${keyToString( pair.first )}$joiner${valueToString( pair.second )}"
    }
    return append( "$prefix$mapToString$suffix" )
}

/**
 * Append a [List] to a [StringBuilder] if not empty
 *
 * @param prefix a [CharSequence] that will precede the entire string
 * @param suffix a [CharSequence] that will secede the entire string
 * @param separator a [CharSequence] that will separate every [Map.Entry] of the [map]
 * @param mapper a lambda that will map every element to a [String]
 */
internal fun <E> StringBuilder.appendIfNotEmpty(
    list: List<E>,
    prefix: CharSequence = EMPTY_STRING,
    suffix: CharSequence = EMPTY_STRING,
    separator: CharSequence = ", ",
    mapper: (E) -> String = { it.toString() }
): StringBuilder {
    if ( list.isEmpty() ) return this
    val listToString = list.joinToString( separator = separator ) { mapper( it ) }
    return append( "$prefix$listToString$suffix" )
}

/** Append an [Any] to a [StringBuilder] if not null, with and optional [prefix] and [suffix] */
internal fun StringBuilder.appendIfNotNull(
    csq: Any?,
    prefix: CharSequence = EMPTY_STRING,
    suffix: CharSequence = EMPTY_STRING
) = append( csq?.let { "$prefix$csq$suffix" } ?: EMPTY_STRING )