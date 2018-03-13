package im.controller;

import im.InstantMessengerClient;
import im.core.OwnUser;
import im.core.UserAuthenticator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeUsernameFormController {
    private static final String ALERT_TITLE = "Namensüberprüfung";
    private static final String ALERT_HEADER_USER_EXIST = "Nutzer existiert bereits!";
    private static final String ALERT_HEADER_USER_OK = "Nutzername in Ordnung";
    private static final String ALERT_MESSAGE_USER_EXIST = "Bitte wähle einen anderen Nutzernamen.";
    private static final String ALERT_MESSAGE_USER_OK = "Der angegebene Nutzername kann verwendet werden.";

    @FXML
    private TextField nameField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button checkNameButton;

    private Stage dialogStage;
    private InstantMessengerClient mainApp;
    private UserAuthenticator authenticator;

    public ChangeUsernameFormController() {
    }

    @FXML
    private void initialize() {
        authenticator = new UserAuthenticator();
        nameField.setText(OwnUser.getInstance().getUsername());
    }

    @FXML
    public void handleCheckName() {
        if (authenticator.doesUserExist(nameField.getText())) {
            mainApp.showAlert(ALERT_TITLE, ALERT_HEADER_USER_EXIST, ALERT_MESSAGE_USER_EXIST);
        } else {
            mainApp.showAlert(ALERT_TITLE, ALERT_HEADER_USER_OK, ALERT_MESSAGE_USER_OK);
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        if (authenticator.doesUserExist(nameField.getText())) {
            mainApp.showAlert(ALERT_TITLE, ALERT_HEADER_USER_EXIST, ALERT_MESSAGE_USER_EXIST);
        } else {
            authenticator.changeUsername(nameField.getText());
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