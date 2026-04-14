package by.bratukhin.uuidv7

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * An IntelliJ Platform action that generates a time-ordered UUID v7 and inserts it
 * at the current caret position(s) in the active editor.
 *
 * This action integrates with the IDE's "Generate" menu (Alt+Insert / Cmd+N) and
 * supports multiple carets, inserting a unique UUID at each caret location.
 *
 * UUID v7 is based on a Unix timestamp with additional random bits, making it
 * lexicographically sortable by creation time — ideal for database primary keys
 * and distributed systems.
 *
 * @see Uuid.generateV7
 * @see <a href="https://www.rfc-editor.org/rfc/rfc9562.html">RFC 9562</a>
 */
class GenerateUuidV7Action : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val editor: Editor = event.getData(CommonDataKeys.EDITOR) ?: return
        val project: Project = event.project ?: return
        val document: Document = editor.document
        val caretModel = editor.caretModel

        WriteCommandAction.runWriteCommandAction(project) {
            for (caret in caretModel.allCarets) {
                document.insertString(caret.offset, generateUuidV7())
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class)
    private fun generateUuidV7(): String = Uuid.generateV7().toString()
}
