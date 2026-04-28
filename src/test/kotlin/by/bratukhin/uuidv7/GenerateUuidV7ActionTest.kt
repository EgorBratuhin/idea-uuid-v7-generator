package by.bratukhin.uuidv7

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.apache.commons.lang3.StringUtils
import org.assertj.core.api.Assertions.assertThat
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

        assertThat(UUID.fromString(text).version())
            .describedAs { "Text should be UUID v7" }
            .isEqualTo(7)
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

        assertThat(lines).allSatisfy {
            assertThat(UUID.fromString(it.substringBefore("line")).version())
                .describedAs { "Line '${it}' should start with UUID v7" }
                .isEqualTo(7)
        }
    }

    fun testReplaceSelectedText() {
        val placeholder = "placeholder"
        val sourceText = """String id = "$placeholder";"""

        myFixture.configureByText("Test.java", sourceText)

        val editor = myFixture.editor

        val startOffset = sourceText.indexOf(placeholder)
        val endOffset = startOffset + placeholder.length
        editor.selectionModel.setSelection(startOffset, endOffset)

        myFixture.testAction(GenerateUuidV7Action())

        val result = editor.document.text

        val prefix = sourceText.substring(0, startOffset)
        val suffix = sourceText.substring(endOffset)

        assertThat(result)
            .startsWith(prefix)
            .endsWith(suffix)
            .doesNotContain(placeholder)

        val uuidPart = StringUtils.substringBetween(result, prefix, suffix)

        assertThat(UUID.fromString(uuidPart).version())
            .describedAs { "Replaced text should be UUID v7" }
            .isEqualTo(7)
    }

}
