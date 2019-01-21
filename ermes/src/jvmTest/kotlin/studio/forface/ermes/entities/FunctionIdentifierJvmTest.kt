package studio.forface.ermes.entities

import org.junit.Test
import kotlin.reflect.KFunction
import kotlin.test.assertEquals

/**
 * @author Davide Giuseppe Farella.
 * Test class for [FunctionIdentifier] on Jvm
 */
class FunctionIdentifierJvmTest {

    @Test
    fun `kotlinToJava matchCorrectly`() {
        class TestClass { fun testMethod( one: Int, two: String ) {} }

        val kFun = TestClass::class.members.first { it.name == "testMethod" } as KFunction<*>
        val kId = kFun.identifier

        val jFun = TestClass::class.java.methods.first { it.name == "testMethod" }
        val jId = jFun.identifier

        assertEquals( jId, kId )
    }
}