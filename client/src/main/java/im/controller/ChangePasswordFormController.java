package im.controller;

import im.InstantMessengerClient;
import im.core.UserAuthenticator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangePasswordFormController {
    private static final String ALERT_TITLE = "Passwort";
    private static final String ALERT_HEADER = "Ungültiges Passwort";
    private static final String ALERT_MESSAGE = "Bitte geben sie ihr aktuelles Passwort ein";

    @FXML
    private TextField oldPasswordField;
    @FXML
    private TextField newPasswordField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    private Stage dialogStage;
    private InstantMessengerClient mainApp;
    private UserAuthenticator authenticator;

    public ChangePasswordFormController() {
    }

    @FXML
    private void initialize() {
        authenticator = new UserAuthenticator();
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        if (!authenticator.isPasswordOk(oldPasswordField.getText())) {
            mainApp.showAlert(ALERT_TITLE, ALERT_HEADER, ALERT_MESSAGE);
        } else {
            authenticator.changPassword(newPasswordField.getText());
            dialogStage.close();
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }
}