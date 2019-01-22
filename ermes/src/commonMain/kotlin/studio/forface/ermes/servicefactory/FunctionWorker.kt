package studio.forface.ermes.servicefactory

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import studio.forface.ermes.annotations.ApiMethod
import studio.forface.ermes.annotations.ApiParam
import studio.forface.ermes.annotations.apiMethod
import studio.forface.ermes.annotations.apiParam
import studio.forface.ermes.entities.HttpCallParams
import studio.forface.ermes.entities.Url
import studio.forface.ermes.exceptions.MissingAnnotationException
import studio.forface.ermes.utils.valueParameters
import kotlin.reflect.KFunction

/**
 * @author Davide Giuseppe Farella
 * A class that handle a [KFunction] with its annotations and return an [HttpCallParams] that can be easily used from
 * make an Http call via the specified [HttpClient].
 *
 * @throws MissingAnnotationException
 * @see method
 *
 * @throws IllegalArgumentException
 * @see invoke
 * @see preconditions
 */
@PublishedApi
internal class FunctionWorker( function: KFunction<*>, url: Url ) {

    /** The [String] name of the [KFunction] */
    private val functionName = function.name

    /**
     * The [ApiMethod] annotation of the [KFunction].
     * @throws MissingAnnotationException if no [ApiMethod] annotation is found
     */
    private val method = function.apiMethod

    /** A [List] of NULLABLE [ApiParam] annotations of the [KFunction.valueParameters] */
    private val params: List<ApiParam?> = function.valueParameters.map { it.apiParam }

    /** An [Url] created from the constructor url plus [ApiMethod.path] in [method] */
    private val url = url + method.path

    /**
     * @return [HttpCallParams] created from [ApiMethod.httpMethod] in [method], [url], [params] and [args]
     *
     * @throws IllegalArgumentException
     * @see preconditions
     */
    operator fun invoke( args: Array<out Any?> ): HttpCallParams {
        val fields = mutableMapOf<String, String>()
        val bodies = mutableListOf<String>()

        // For each arg in `args`, get the param from `params` with the same index, if both of there are NOT null, then
        // save the given params in `fields` or `bodies` or directly append to `url`
        args.map { it?.toString() }.forEachIndexed { index, arg ->
            val param = params[index]
            if ( param != null && arg != null ) {

                @Suppress("REDUNDANT_ELSE_IN_WHEN")
                when ( param ) {
                    is ApiParam.Body ->     bodies += arg
                    is ApiParam.Field ->    fields[param.field] = arg
                    is ApiParam.Path ->     url[param.path] = arg
                    is ApiParam.Query ->    url += param.name to arg
                    else -> throw NotImplementedError( "${param::class.qualifiedName} not implemented" )
                }
            }
        }

        // Check the preconditions or throw exception
        preconditions( functionName, method, bodies.size, fields.isNotEmpty() )

        // Set a `body` from the first element in `bodies`, from `fields` or leave it null.
        var body: Any? = null
        bodies.firstOrNull()?.let { body = it }
        if ( fields.isNotEmpty() ) body = MultiPartFormDataContent( formData {
            fields.forEach { append( it.key, it.value ) }
        } )

        return HttpCallParams( method.httpMethod, url, body )
    }

    /**
     * Check if all the precondition are satisfied, otherwise throw an Exception
     * @throws IllegalArgumentException
     */
    private fun preconditions( caller: String, apiMethod: ApiMethod, bodyCount: Int, hasFields: Boolean ) {
        val method = apiMethod::class.simpleName
        val body = ApiParam.Body::class.simpleName
        val field = ApiParam.Field::class.simpleName

        if ( apiMethod is ApiMethod.POST || apiMethod is ApiMethod.PUT ) {
            if ( bodyCount == 0 && ! hasFields )
                throw IllegalStateException( "$caller: A $body OR at least a $field is required for a $method request" )

        } else if ( apiMethod !is ApiMethod.DELETE ) {
            if ( bodyCount != 0 || hasFields )
                throw IllegalStateException( "$caller: A $method cannot have a $body or a $field" )
        }

        if ( bodyCount > 1 ) throw IllegalStateException( "$caller: Define only one $body" )
        if ( bodyCount > 0 && hasFields )
            throw IllegalStateException( "$caller: Define only a $body or a set of ${field}s" )
    }
}