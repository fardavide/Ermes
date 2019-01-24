package studio.forface.ermes.annotations

import studio.forface.ermes.ErmesApi
import studio.forface.ermes.authenticator.Authenticator
import studio.forface.ermes.utils.EMPTY_STRING

/**
 * @author Davide Giuseppe Farella.
 * An annotation required for every service of [ErmesApi]
 *
 * @param endpoint an OPTIONAL endpoint for the base url
 * @param identifier an OPTIONAL [String] identifier for the service, if could be useful for the [Authenticator]
 * e.g. >   authenticator { serviceIdentifier ->
                when ( serviceIdentifier ) {
                    "myServiceV3" -> url += "sessionId" to someSessionId
                    "myServiceV4" -> headers += "Authorization" to "Bearer $someToken"
                }
            }
 *
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiService( val endpoint: String = EMPTY_STRING, val identifier: String = EMPTY_STRING )