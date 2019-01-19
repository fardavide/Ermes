package studio.forface.ermes.servicebuilder

import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.entities.Endpoint

/**
 * @author Davide Giuseppe Farella.
 * TODO
 */
internal class ApiServiceAnnotationWorker( apiService: ApiService ) {

    val endpoint: Endpoint? = apiService.endpoint.let { endpoint ->
        if ( endpoint.isNotBlank() ) Endpoint( endpoint ) else null
    }
}