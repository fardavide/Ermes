package studio.forface.ermes.servicebuilder

/**
 * @author Davide Giuseppe Farella.
 */
actual object ServiceBuilder {
    /**
     * A function for build a service for the given [S].
     *
     * @return an instance of [S]
     */
    actual inline fun <reified S> createService() : S {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}