package studio.forface.ermes.servicebuilder

/**
 * @author Davide Giuseppe Farella.
 */
expect object ServiceBuilder {
    /**
     * A function for build a service for the given [S].
     *
     * @return an instance of [S]
     */
    inline fun <reified S> createService() : S
}