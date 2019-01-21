@file:Suppress("FunctionName")

package studio.forface.ermes.servicefactory

import studio.forface.ermes.exceptions.MissingAnnotationException
import studio.forface.ermes.exceptions.RequireDeferredException
import studio.forface.ermes.testApi
import kotlin.test.Test
import kotlin.test.assertFailsWith

/**
 * @author Davide Giuseppe Farella.
 * Test class for [ServiceFactory]
 */
class ServiceFactoryTest {

    @Test
    fun test() {
        //val members = ServiceFactory( testApi, Service::class ).serviceFunctions
        //members
    }

    @Test // TODO temporary test, check NotImplementedError since ServiceFactory.invoke() is not implemented yet
    fun runCorrectly() {
        assertFailsWith( NotImplementedError::class ) {
            testApi.service
        }
    }

    @Test
    fun `onInit serviceNotAnnotatedWithApiService throwMissingAnnotationException`() {
        assertFailsWith( MissingAnnotationException::class ) {
            testApi.noApiServiceAnnotationService
        }
    }

    @Test
    fun `onInit serviceFunctionDoesNotReturnDeferred throwRequireDeferredException`() {
        assertFailsWith( RequireDeferredException::class ) {
            testApi.noDeferredService
        }
    }
}