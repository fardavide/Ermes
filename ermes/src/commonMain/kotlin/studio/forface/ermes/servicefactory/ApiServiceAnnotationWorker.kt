package studio.forface.ermes.servicefactory

import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.entities.Endpoint

/**
 * @author Davide Giuseppe Farella.
 * A class for handle [ApiService]
 */
internal class ApiServiceAnnotationWorker( apiService: ApiService ) {

    /** @return [Endpoint] if [ApiService.endpoint] is not blank, else null */
    val endpoint: Endpoint? = apiService.endpoint.let { endpoint ->
        if ( endpoint.isNotBlank() ) Endpoint( endpoint ) else null
    }

    /** @return the [ApiService.identifier] */
    val identifier = apiService.identifier
}