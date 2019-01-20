@file:Suppress("FunctionName")

package studio.forface.ermes.entities

import kotlin.reflect.KFunction
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * @author Davide Giuseppe Farella.
 * Test class for [FunctionIdentifier]
 */
class FunctionIdentifierTest {

    @Test
    fun `fromKFunction createCorrectly`() {
        class TestClass { fun testMethod( one: Int, two: String ) {} }

        val kFun = TestClass::class.members.first { it.name == "testMethod" } as KFunction<*>
        val id = kFun.identifier

        assertEquals( id.name,"testMethod" )
        assertEquals( id.argsType, listOf( "kotlin.Int", "kotlin.String" ) )
    }
}