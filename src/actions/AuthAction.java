package actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import org.jetbrains.annotations.Nullable;
import ui.SocialProviderSelectDialog;

public final class AuthAction extends EditorAction {

    public AuthAction() {
        this(new AuthActionHandler());
    }

    private AuthAction(EditorActionHandler defaultHandler) {
        super(defaultHandler);
    }

    private static class AuthActionHandler extends EditorActionHandler {

        @Override
        protected void doExecute(Editor editor, @Nullable Caret caret, DataContext dataContext) {
            super.doExecute(editor, caret, dataContext);
            SocialProviderSelectDialog.createInstance().show();
        }
    }

}
