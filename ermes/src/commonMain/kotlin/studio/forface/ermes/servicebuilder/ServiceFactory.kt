package studio.forface.ermes.servicebuilder

import kotlinx.coroutines.Deferred
import studio.forface.ermes.annotations.ApiService
import studio.forface.ermes.api.ErmesApi
import studio.forface.ermes.entities.Endpoint
import studio.forface.ermes.entities.Url
import studio.forface.ermes.exceptions.MissingAnnotationException
import studio.forface.ermes.exceptions.MissingModifierException
import studio.forface.ermes.exceptions.RequireDeferredException
import studio.forface.ermes.utils.findAnnotation
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KProperty
import kotlin.reflect.KTypeParameter

/**
 * @author Davide Giuseppe Farella.
 * A class that will created the implementation of the requested Service [S].
 *
 * @param S the return type of [invoke]
 * @param ermesApi required for retrieve Api info, such as [ErmesApi.baseUrl]
 * @param serviceKlass the Class of the requested Service [S] used for reflection and required since [S] will be erased
 * at runtime.
 *
 * @throws MissingAnnotationException
 * @see apiServiceAnnotationWorker
 *
 * @throws RequireDeferredException
 * @see serviceFunctions
 *
 * TODO @throws MissingModifierException
 * TODO @see serviceFunctions
 */
@PublishedApi
internal class ServiceFactory<S : Any>( ermesApi: ErmesApi, private val serviceKlass: KClass<S> ) {

    /**
     * An instance of [ApiServiceAnnotationWorker]
     * @throws MissingAnnotationException if [serviceKlass] is not annotated with [ApiService]
     */
    private val apiServiceAnnotationWorker = ApiServiceAnnotationWorker(
        serviceKlass.findAnnotation<ApiService>() ?: throw MissingAnnotationException(
            "The service must be annotated with ${ApiService::class.qualifiedName}"
        )
    )

    /** A validated base [Url], ( with the [Endpoint] if not null ) for the calls */
    private val baseUrl = ermesApi.baseUrl + apiServiceAnnotationWorker.endpoint

    /**
     * All the declared [KFunction]s in [S], filtering [equals], [toString] and [hashCode]
     * @throws RequireDeferredException if a function in [S] doesn't return a [Deferred]
     *
     * TODO @throws MissingModifierException if a function is not annotated with 'suspend'.
     */
    private val serviceFunctions = serviceKlass.members
        .mapNotNull { it as? KFunction<*> }
        .filterNot { it.name == "equals" || it.name == "toString" || it.name == "hashCode" }
        .also { it.forEach { f ->
            if ( f.returnType.classifier != Deferred::class ) throw RequireDeferredException( serviceKlass, f )
        } }
        //.also { it.forEach { f -> if ( !f.isSuspend ) throw MissingModifierException( "suspend" ) } }


    /** Create an implementation at runtime of the requested Service [S] */
    operator fun invoke() : S {



        //return makeProxy()

        TODO("Not Implemented" )
    }
}

/** Platform dependant creation of Proxy */
//internal expect inline fun <reified S : Any> ServiceFactory<S>.makeProxy() : S

/**
 * Create a Service by property delegation
 * @return [ReadOnlyProperty] representing the implementation of the requested [S], generated by [ServiceFactory].
 */
inline fun <reified S : Any> ErmesApi.service() = object : ReadOnlyProperty<ErmesApi, S> {

    /** A lazy value for the Service [S] */
    private val value: S by lazy { this@service<S>() }

    /**
     * @see ReadOnlyProperty.getValue
     * @return [value]
     */
    override fun getValue( thisRef: ErmesApi, property: KProperty<*>): S = value
}