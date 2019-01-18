package studio.forface.ermes.servicebuilder

import studio.forface.ermes.annotations.ApiService

/**
 * @author Davide Giuseppe Farella.
 * TODO
 */
class ApiServiceAnnotationWorker( apiService: ApiService ) {

    val testHasEndpoint = apiService.endpoint.isNotBlank()
}