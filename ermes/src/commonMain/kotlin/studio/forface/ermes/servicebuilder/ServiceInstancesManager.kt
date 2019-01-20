package studio.forface.ermes.servicebuilder

import studio.forface.ermes.api.ErmesApi
import kotlin.reflect.KClass

/**
 * @author Davide Giuseppe Farella
 * A class for manage ( create, hold and retrieve ) single instances of Services for [ErmesApi]
 */
internal class ServiceInstancesManager(
    private val ermesApi: ErmesApi,
    private val instanceCreator: InstanceCreator = InstanceCreator( ermesApi ),
    private val instancesHolder: InstancesHolder = InstancesHolder()
) {

    /** @return the instance of the requested Service [S] */
    inline operator fun <reified S : Any> invoke() : S = instancesHolder { instanceCreator() }
}


/** A class for create instances of Services */
internal class InstanceCreator( private val ermesApi: ErmesApi ) {

    /** @return a new instance of [S], created via [ServiceFactory] */
    inline operator fun <reified S : Any> invoke() = ServiceFactory( ermesApi, S::class )()
}


/** A class for store and retrieve instances of Services */
internal class InstancesHolder {

    /** A mutable [Map] holding instances of Service ( [Any] ) indexed by their [KClass] */
    private val instances = mutableMapOf<KClass<*>, Any>()

    /**
     * @return the instance for the requested [S]
     * If no instance is available in [instances], a new one will be created invoking [create] and stored in
     * [instances], then the new instance will be returned.
     */
    inline operator fun <reified S : Any> invoke( create: () -> S ) : S {
        val kClass = S::class
        if ( ! instances.contains( kClass ) ) instances[kClass] = create()
        return instances[kClass] as S
    }
}