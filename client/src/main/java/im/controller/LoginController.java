package im.controller;

import im.InstantMessengerClient;
import im.core.UserAuthenticator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LoginController implements EventHandler<KeyEvent> {
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
        getUserText.setOnKeyPressed(this);
        getPasswordText.setOnKeyPressed(this);
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

    /**
     * handle the enter event on the textfield and executes its logic
     *
     * @param event
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) handleLogin();
    }
}