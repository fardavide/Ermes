package studio.forface.ermes.utils

/** Author: Davide Giuseppe Farella */

/** Call [List.forEach] separating [Pair.first] to [Pair.second] on a [List] of [Pair]s */
internal fun <F, S> Iterable<Pair<F, S>>.forEach( block: ( first: F, second: S ) -> Unit ) =
        forEach { block( it.first, it.second ) }

/** @return a [List] of [Pair]s, filtering when [Pair.first] is null or [Pair.second] is null */
@Suppress("UNCHECKED_CAST")
internal fun <F: Any, S: Any> Iterable<Pair<F?, S?>>.filterNotPairWithNull() =
        filterNot { it.first == null || it.second == null } as List<Pair<F, S>>

/** Replace an [old] [E] in the [List] with a [new] [E], if the [List.contains] [old] [E] */
internal fun <E> MutableList<E>.replace( old: E, new: E ) {
    if ( ! contains( old ) ) return
    val index = indexOf( old )
    set( index, new )
}

/** @see MutableList.replace */
internal operator fun <E> MutableList<E>.set( old: E, new: E ) = replace( old, new )