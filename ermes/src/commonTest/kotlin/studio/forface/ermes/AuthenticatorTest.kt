@file:Suppress("FunctionName")

package studio.forface.ermes

import studio.forface.ermes.authenticator.Authenticator
import studio.forface.ermes.entities.Url
import studio.forface.ermes.utils.EMPTY_STRING
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Davide Giuseppe Farella
 * Test class for [Authenticator]
 */
class AuthenticatorTest {

    @Test
    fun `invoke setParamsCorrectly`() {
        val authenticator = Authenticator {
            url += "a" to "b"
            headers += "head" to "body"
            headers += "arm" to "leg"
        }
        val params = authenticator( testParams, EMPTY_STRING )
        assertEquals( params.url, Url( testUrl ) + ( "a" to "b" ) )
        assertEquals( params.headers, listOf( "head" to "body", "arm" to "leg" ) )
    }
}