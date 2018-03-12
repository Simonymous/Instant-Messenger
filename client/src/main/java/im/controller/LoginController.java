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
    @FXML
    private TextField getUserText;
    @FXML
    private PasswordField getPasswordText;
    @FXML
    private Button loginBtn;

    private InstantMessengerClient mainApp;

    public LoginController() {
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleLogin() {
        String name = getUserText.getText();
        String password = getPasswordText.getText();
        if(!name.isEmpty()) {
            UserAuthenticator authenticator = new UserAuthenticator();
            if (authenticator.authenticateUser(name, password)) {
                mainApp.loginSuccess();
            } else {
                showAlert();
            }
        } else {
            showAlert();
        }
    }

    @FXML
    public void handleRegister() {
       mainApp.showRegisterForm();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Nutzer konnte nicht authentifiziert werden");
        alert.setContentText("Bitte überprüfe den Namen oder das Passwort");

        alert.showAndWait();
    }
}