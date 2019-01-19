@file:Suppress("FunctionName")

package studio.forface.ermes.entities

import studio.forface.ermes.exceptions.InvalidUrlException
import kotlin.test.*

/**
 * @author Davide Giuseppe Farella.
 * A test class for [Url]
 */
class UrlTest {

    @Test
    fun `plusEndpoint whenEndpointIsNull doNothing`() {
        val url = Url("http://4face.studio" )
        url + null
        assertEquals( url, Url("http://4face.studio" ) )
    }

    @Test
    fun `plusEndpoint whenEndpointIsNotNull appendEndpointCorrectly`() {
        val url = Url("http://4face.studio" )
        url + Endpoint( "endpoint" )
        assertEquals( url, Url("http://4face.studio/endpoint" ) )
    }

    @Test
    fun `validateOrThrow whenUrlIsValid returnUrl`() {
        val url = Url("http://4face.studio" )
        val sUrl = Url("https://4face.studio" )

        assertEquals( url, url.validateOrThrow() )
        assertEquals( sUrl, sUrl.validateOrThrow() )
    }

    @Test
    fun `validateOrThrow whenUrlIsInvalid throwInvalidUrlException`() {
        assertFailsWith( InvalidUrlException::class ) {
            Url("4face.studio" ).validateOrThrow()
        }
        assertFailsWith( InvalidUrlException::class ) {
            Url("http://4face" ).validateOrThrow()
        }
    }
}