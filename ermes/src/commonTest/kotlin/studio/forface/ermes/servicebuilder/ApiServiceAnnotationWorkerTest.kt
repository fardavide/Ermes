@file:Suppress("FunctionName")

package studio.forface.ermes.servicebuilder

import studio.forface.ermes.NoEndpointService
import studio.forface.ermes.Service
import studio.forface.ermes.TestApi
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.utils.findAnnotation
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * @author Davide Giuseppe Farella.
 * Test class for [ApiServiceAnnotationWorker]
 */
class ApiServiceAnnotationWorkerTest {

    @Test // TODO name method
    fun `testHasEndpoint serviceWithoutEndpoint returnFalse`() {
        val worker = ApiServiceAnnotationWorker( NoEndpointService::class.findAnnotation<ApiService>()!! )
        assertFalse( worker.testHasEndpoint )
    }

    @Test // TODO name method
    fun `testHasEndpoint serviceWithEndpoint returnTrue`() {
        val worker = ApiServiceAnnotationWorker( Service::class.findAnnotation<ApiService>()!! )
        assertTrue( worker.testHasEndpoint )
    }
}