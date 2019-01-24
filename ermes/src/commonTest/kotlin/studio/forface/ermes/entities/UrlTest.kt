@file:Suppress("FunctionName")

package studio.forface.ermes.entities

import studio.forface.ermes.exceptions.InvalidUrlException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * @author Davide Giuseppe Farella.
 * A test class for [Url]
 */
class UrlTest {

    @Test
    fun `toString allParams createRegularUrl`() {
        val url = Url("http://4face.studio" )
        url += Endpoint("gallery" )
        url += UrlPath("movies" )
        url += UrlPath("userId" )
        url["userId"] = "4face"
        url += "sort" to "desc"
        url += "year" to "2019"

        assertEquals( url.toString(),"http://4face.studio/gallery/movies/4face?sort=desc&year=2019" )
    }

    @Test
    fun `toString noParams createRegularUrl`() {
        val url = Url("http://4face.studio" )

        assertEquals( url.toString(),"http://4face.studio" )
    }

    @Test
    fun `plusAssignEndpoint whenEndpointIsNull doNothing`() {
        val url = Url("http://4face.studio" )
        url += null
        assertEquals( url, Url("http://4face.studio" ) )
    }

    @Test
    fun `plusAssignEndpoint whenEndpointIsNotNull appendEndpointCorrectly`() {
        val url = Url("http://4face.studio" )
        url += Endpoint( "endpoint" )
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