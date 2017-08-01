package ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import common.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import social.SocialType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AuthDialog extends DialogWrapper {

    /**
     * Default symbols count in input field
     */
    private static final int DEFAULT_COLUMNS_COUNT = 25;

    /**
     * Selected social type
     * from {@link SocialProviderSelectDialog}
     */
    private final SocialType type;

    static AuthDialog createInstance(SocialType type) {
        AuthDialog dialog = new AuthDialog(type);
        dialog.setAutoAdjustable(true);
        dialog.setTitle(Constants.AUTH_DIALOG_CREDENTIALS_TITLE);
        return new AuthDialog(type);
    }

    private AuthDialog(SocialType type) {
        super(false);
        this.type = type;
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialog = new JPanel(new GridBagLayout());
        initializeLayout(dialog);
        return dialog;
    }

    @NotNull
    @Override
    protected Action getOKAction() {
        return new DialogWrapperAction(Constants.AUTH_BTN_AUTHORIZE) {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                dispose();
            }
        };
    }

    private void initializeLayout(JPanel dialog) {
        dialog.setLayout(new GridBagLayout());

        JTextField username = new JBTextField(DEFAULT_COLUMNS_COUNT);
        JPasswordField password = new JBPasswordField();

        password.setColumns(DEFAULT_COLUMNS_COUNT);

        JBInsets fieldInsets = JBUI.insets(0, 0, 5, 5);
        JBInsets labelInsets = JBUI.insets(0, 0, 5, 0);

        GridBagConstraints constraints = new GridBagConstraints();

        // Username hint label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.insets = fieldInsets;

        dialog.add(new JBLabel(Constants.AUTH_HINT_USERNAME), constraints);

        // Username input field
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = labelInsets;

        dialog.add(username, constraints);

        // Password hint label
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = fieldInsets;

        dialog.add(new JBLabel(Constants.AUTH_HINT_PASSWORD), constraints);

        // Password input field
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.insets = labelInsets;

        dialog.add(password, constraints);
    }
}
