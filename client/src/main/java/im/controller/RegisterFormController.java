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
            showAlert();
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
          showAlert();
      } else {
          authenticator.addNewUser(nameField.getText(), passwordField.getText());
          dialogStage.close();
      }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Fehler");
        alert.setHeaderText("Nutzer existiert bereits!");
        alert.setContentText("Bitte wähle einen anderen Benutzernamen");

        alert.showAndWait();
    }
}
