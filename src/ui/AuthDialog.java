package ui;

import com.intellij.ui.JBColor;
import common.Constants;
import data.social.SocialAuthenticator;
import data.social.SocialType;
import data.social.twitter.TwitterSocialAuthenticator;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;

public class AuthDialog {

    /**
     * Default symbols count in input field
     */
    private static final int DEFAULT_COLUMNS_COUNT = 25;

    /**
     * Social authenticator for selected {@link SocialType}
     */
    private final SocialAuthenticator socialAuthenticator;

    static AuthDialog createInstance(SocialType type) {
        return new AuthDialog(type);
    }

    private AuthDialog(SocialType type) {
        initializeLayout();
        this.socialAuthenticator = SocialAuthenticator.Factory.create(type);
    }

    private void initializeLayout() {
        JFrame jFrame = new JFrame(Constants.AUTH_DIALOG_CREDENTIALS_TITLE);
        JFXPanel panel = new JFXPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        jFrame.add(panel);
        jFrame.setBackground(JBColor.DARK_GRAY);
        jFrame.setMinimumSize(new Dimension(dim.width / 2, dim.height / 2));
        jFrame.setLocation(dim.width / 2 - jFrame.getMinimumSize().width / 2, dim.height / 2 - jFrame.getMinimumSize().height / 2);

        Platform.runLater(() -> {
            WebView webView = new WebView();

            webView.setMinWidth(jFrame.getMinimumSize().width);
            webView.setMinHeight(jFrame.getMinimumSize().height);

            panel.setScene(new Scene(webView));

            jFrame.setVisible(true);
            jFrame.toFront();

            ((TwitterSocialAuthenticator) socialAuthenticator)
                    .requestOauthToken()
                    .thenAcceptAsync(s -> webView.getEngine().load(s));
        });
    }

    private void onLoggedIn(boolean status, Throwable error) {

    }
}
