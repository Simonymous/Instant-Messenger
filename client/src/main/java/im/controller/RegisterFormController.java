package im.controller;

import im.core.Updater;
import im.core.UserAuthenticator;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterFormController  {
    @FXML
    private TextField nameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button checkNameButton;

    private Stage dialogStage;
    private Updater updater;

    public RegisterFormController() {
    }

    @FXML
    private void initialize() {

    }

    @FXML
    public void handleCheckName() {
        UserAuthenticator authenticator = new UserAuthenticator();
        if (authenticator.doesUserExist(nameField.getText())) {
            showAlert("Nutzer existiert bereits!", "Bitte wähle einen anderen Nutzernamen.");
        } else {
            showAlert("Nutzername in Ordnung","Der angegebene Nutzername kann verwendet werden.");
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
      UserAuthenticator authenticator = new UserAuthenticator();
      if (authenticator.doesUserExist(nameField.getText())) {
          showAlert("Nutzer existiert bereits!", "Bitte wähle einen anderen Nutzernamen.");
      } else {
          authenticator.addNewUser(nameField.getText(), passwordField.getText());
          dialogStage.close();
      }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void showAlert(String alertHeader, String alertMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Namensüberprüfung");
        alert.setHeaderText(alertHeader);
        alert.setContentText(alertMessage);

        alert.showAndWait();
    }
}
