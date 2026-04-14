package by.bratukhin.uuidv7

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi

/**
 * Test for [GenerateUuidV7Action].
 */
@OptIn(ExperimentalUuidApi::class)
class GenerateUuidV7ActionTest : BasePlatformTestCase() {

    fun testInsertSingleUuid() {
        myFixture.configureByText("Test.java", "")
        myFixture.editor.caretModel.moveToOffset(0)

        myFixture.testAction(GenerateUuidV7Action())

        val text = myFixture.editor.document.text

        assertEquals("Text should contain UUID", 7, UUID.fromString(text).version())
    }

    fun testInsertMultipleCarets() {
        myFixture.configureByText("Test.txt", "line1\nline2\nline3")

        val editor = myFixture.editor
        val caretModel = editor.caretModel

        caretModel.moveToOffset(0)
        caretModel.addCaret(editor.offsetToVisualPosition(6))
        caretModel.addCaret(editor.offsetToVisualPosition(12))

        myFixture.testAction(GenerateUuidV7Action())

        val lines = editor.document.text.split("\n")

        lines.forEach { line ->
            assertEquals(
                "Line '${line}' should start with UUID v7",
                7, UUID.fromString(line.substringBefore("line")).version()
            )
        }
    }

}
