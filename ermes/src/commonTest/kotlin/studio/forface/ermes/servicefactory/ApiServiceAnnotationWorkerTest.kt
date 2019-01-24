@file:Suppress("FunctionName")

package studio.forface.ermes.servicefactory

import studio.forface.ermes.NoEndpointService
import studio.forface.ermes.Service
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.utils.findAnnotation
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

/**
 * @author Davide Giuseppe Farella.
 * Test class for [ApiServiceAnnotationWorker]
 */
class ApiServiceAnnotationWorkerTest {

    @Test
    fun `endpoint serviceWithoutEndpoint returnNull`() {
        val worker = ApiServiceAnnotationWorker( NoEndpointService::class.findAnnotation<ApiService>()!! )
        assertNull( worker.endpoint )
    }

    @Test
    fun `endpoint serviceWithEndpoint returnNotNullEndpoint`() {
        val worker = ApiServiceAnnotationWorker( Service::class.findAnnotation<ApiService>()!! )
        assertNotNull( worker.endpoint )
    }
}