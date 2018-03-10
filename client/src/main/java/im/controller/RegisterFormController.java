package im.controller;

import im.core.Updater;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegisterFormController implements EventHandler<MouseEvent> {
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

    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void handle(MouseEvent event) {

    }
}
