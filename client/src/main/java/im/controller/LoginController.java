package im.controller;

import im.InstantMessengerClient;
import im.core.OwnUser;
import im.core.UserAuthenticator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    private static final String ALERT_TITLE = "Fehler";
    private static final String ALERT_HEADER = "Nutzer konnte nicht authentifiziert werden";
    private static final String ALERT_MESSAGE = "Bitte überprüfe den Namen oder das Passwort";

    @FXML
    private TextField getUserText;
    @FXML
    private PasswordField getPasswordText;
    @FXML
    private Button loginBtn;

    private InstantMessengerClient mainApp;

    public LoginController() {
    }

    @FXML
    protected void initialize() {

    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
        getUserText.requestFocus();
    }

    /**
     * Handles a Click on the Login-Button and executes its logic
     */
    @FXML
    public void handleLogin() {
        String name = getUserText.getText();
        String password = getPasswordText.getText();
        if (!name.isEmpty()) {
            UserAuthenticator authenticator = new UserAuthenticator();
            if (authenticator.authenticateUser(name, password)) {
                mainApp.loginSuccess();
            } else {
                mainApp.showAlert(ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE);
            }
        } else {
            mainApp.showAlert(ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE);
        }
    }

    /**
     * Handles a Click on the Register-Button and opens a new Windows for Registration
     */
    @FXML
    public void handleRegister() {
        mainApp.showRegisterForm();
    }
}