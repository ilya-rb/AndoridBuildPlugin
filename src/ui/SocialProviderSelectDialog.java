package ui;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBCardLayout;
import common.Constants;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import data.social.SocialType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SocialProviderSelectDialog extends DialogWrapper implements ActionListener {

    private JComboBox<String> list;
    private SocialType selectedType = SocialType.DEFAULT;

    public static SocialProviderSelectDialog createInstance() {
        SocialProviderSelectDialog dialog = new SocialProviderSelectDialog();

        dialog.setResizable(false);
        dialog.setTitle(Constants.SOCIAL_DIALOG_TITLE);

        return dialog;
    }

    private SocialProviderSelectDialog() {
        super(false);
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new JBCardLayout());

        this.list = new ComboBox<>(Constants.SOCIAL_DIALOG_SUPPORTED_NETWORKS);
        this.list.addActionListener(this);

        panel.setLayout(new CardLayout());
        panel.add(this.list);

        return panel;
    }

    @NotNull
    @Override
    protected Action getOKAction() {
        return new DialogWrapperAction(Constants.SOCIAL_DIALOG_OK_ACTION) {
            @Override
            protected void doAction(ActionEvent actionEvent) {
                dispose();
                AuthDialog.createInstance(selectedType).show();
            }
        };
    }

    @Override
    protected void dispose() {
        super.dispose();

        this.list.removeActionListener(this);
        this.list = null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            JComboBox box = (JComboBox) event.getSource();
            switch (box.getSelectedIndex()) {
                case 0:
                    selectedType = SocialType.TWITTER;
                    break;
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}

