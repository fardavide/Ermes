@file:Suppress("FunctionName")

package studio.forface.ermes.servicebuilder

import studio.forface.ermes.exceptions.IllegalAnnotationException
import studio.forface.ermes.testApi
import kotlin.test.Test
import kotlin.test.assertFailsWith

/**
 * @author Davide Giuseppe Farella.
 * Test class for [ServiceBuilder]
 */
class ServiceBuilderTest {

    @Test
    fun `createService serviceNotAnnotatedWithApiService throwIllegalAnnotationException`() {
        assertFailsWith( IllegalAnnotationException::class ) {
            testApi.noApiServiceAnnotationService
        }
    }
}