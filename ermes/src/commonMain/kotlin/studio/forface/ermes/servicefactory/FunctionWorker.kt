package studio.forface.ermes.servicefactory

import studio.forface.ermes.annotations.ApiParam
import studio.forface.ermes.annotations.apiMethod
import studio.forface.ermes.annotations.apiParam
import studio.forface.ermes.entities.Url
import studio.forface.ermes.utils.valueParameters
import kotlin.reflect.KFunction

/**
 * @author Davide Giuseppe Farella
 * TODO
 */
internal class FunctionWorker( function: KFunction<*>, url: Url ) {

    private val method = function.apiMethod

    private val params: List<ApiParam?> = function.valueParameters.map { it.apiParam }

    private val url = url + method.path

    operator fun invoke( args: List<Any?> ) {

    }
}