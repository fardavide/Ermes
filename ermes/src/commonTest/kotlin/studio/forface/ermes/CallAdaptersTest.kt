@file:Suppress("FunctionName")

package studio.forface.ermes.api

import kotlinx.coroutines.Deferred
import studio.forface.ermes.calladapters.CallAdapter
import studio.forface.ermes.calladapters.DeferredCallAdapter
import studio.forface.ermes.calladapters.SuspendCallAdapter
import studio.forface.ermes.exceptions.RequireDeferredException
import studio.forface.ermes.exceptions.RequireSuspendException
import studio.forface.ermes.runTest
import kotlin.reflect.KFunction
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * @author Davide Giuseppe Farella
 * Test class for [CallAdapter]s
 */
class CallAdaptersTest {

    @Suppress("unused")
    interface TestClass {
        fun deferred() : Deferred<Int>
        suspend fun suspend() : Int
    }

    private val deferredFun = TestClass::class.members.first { it.name == "deferred" } as KFunction
    private val suspendFun = TestClass::class.members.first { it.name == "suspend" } as KFunction

    @Test
    fun `deferred assertCorrectly`() {
        DeferredCallAdapter.assertValidFunction( deferredFun )
        assertFailsWith( RequireDeferredException::class ) { DeferredCallAdapter.assertValidFunction( suspendFun ) }
    }

    @Test
    fun `deferred wrapCorrectly`() = runTest {
        val wrap = DeferredCallAdapter.wrapCall { 4 }
        assertTrue { wrap is Deferred<*> }

        @Suppress("UNCHECKED_CAST")
        val result = ( wrap as Deferred<Int> ).await()
        assertTrue { result == 4 }
    }

    @Test
    fun `suspend assertCorrectly`() {
        SuspendCallAdapter.assertValidFunction( suspendFun )
        assertFailsWith( RequireSuspendException::class ) { SuspendCallAdapter.assertValidFunction( deferredFun ) }
    }

    @Test
    fun `suspend wrapCorrectly`() = runTest {
        val wrap = SuspendCallAdapter.wrapCall { 4 }
        assertEquals( wrap,4 )
    }
}