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


public class LoginController implements EventHandler<ActionEvent> {
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

    @FXML
    private void initialize() {
        loginBtn.setOnAction(this);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void handle(ActionEvent event) {
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
}