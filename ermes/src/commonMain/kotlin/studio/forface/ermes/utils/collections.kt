package studio.forface.ermes.utils

/** Author: Davide Giuseppe Farella */

/** Replace an [old] [E] in the [List] with a [new] [E], if the [List.contains] [old] [E] */
internal fun <E> MutableList<E>.replace( old: E, new: E ) {
    if ( ! contains( old ) ) return
    val index = indexOf( old )
    set( index, new )
}

/** @see MutableList.replace */
internal operator fun <E> MutableList<E>.set( old: E, new: E ) = replace( old, new )