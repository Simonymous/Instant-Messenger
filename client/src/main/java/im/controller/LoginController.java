package im.controller;

import im.MainApp;
import im.core.OwnUser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

    private MainApp mainApp;

    public LoginController() {
    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    public void handleLogin() {
        String name = getUserText.getText();
        try {
            //OwnUser.getInstance().createUser(name);

            if (true || OwnUser.getInstance().isCreated()) {
                mainApp.loginSuccess();
            }
        } catch (Exception e) {
            loginLabel.setText("Ungültiger Benutzername");
        }
    }

    @FXML
    public void handleRegister() {
       mainApp.showRegisterForm();
    }
}