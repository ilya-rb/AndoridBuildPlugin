package ui;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPasswordField;
import com.intellij.ui.components.JBTextField;
import common.Constants;
import org.jetbrains.annotations.Nullable;
import social.SocialType;

import javax.swing.*;
import java.awt.*;

public class AuthDialog extends DialogWrapper {

    private final SocialType type;

    public static AuthDialog createInstance(SocialType type) {
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

    private void initializeLayout(JPanel dialog) {
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();

        JTextField username = new JBTextField();
        JPasswordField password = new JBPasswordField();

        // Username hint label
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        dialog.add(new JBLabel(Constants.AUTH_HINT_USERNAME), constraints);

        // Username input field
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        dialog.add(username, constraints);

        // Password hint label
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        dialog.add(new JBLabel(Constants.AUTH_HINT_PASSWORD), constraints);

        // Password input field
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        dialog.add(password, constraints);
    }
}
