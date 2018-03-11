package im.controller;

import im.InstantMessengerClient;
import im.core.OwnUser;
import im.core.UserAuthenticator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField getUserText;
    @FXML
    private PasswordField getPasswordText;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginLabel;

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

        UserAuthenticator authenticator = new UserAuthenticator();
        if (true || authenticator.authenticateUser(name, password) || true) { //TODO: remvoe true when authentification works
            try {
                //OwnUser.getInstance().createUser(name);

                if (true || OwnUser.getInstance().isCreated()) {
                    mainApp.loginSuccess();
                }
            } catch (Exception e) {

            }
        } else {
            loginLabel.setText("Benutzername oder Passwort ungültig");
        }
    }

    @FXML
    public void handleRegister() {
       mainApp.showRegisterForm();
    }
}