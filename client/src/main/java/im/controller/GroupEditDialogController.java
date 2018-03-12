package im.controller;

import im.core.Updater;
import im.model.UserList;
import im.model.listCells.ListViewCellUser;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.interfaces.Group;
import model.interfaces.User;

import java.util.ArrayList;
import java.util.List;

public class GroupEditDialogController implements EventHandler<MouseEvent> {
    @FXML
    private TextField groupNameField;
    @FXML
    private ListView<User> userChoice;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    private Stage dialogStage;
    private Group group;
    private Updater updater;
    private List<User> selectedUser;

    public GroupEditDialogController() {
    }

    @FXML
    private void initialize() {
        updater = new Updater();
        //updater.updateLocalUsers();
        selectedUser = new ArrayList<>();

        setListView(UserList.getInstance().getServerUsers());
        userChoice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        userChoice.setOnMouseClicked(this);
    }

    private void setListView(ObservableList<User> observableList) {
        userChoice.setItems(observableList);
        userChoice.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> userChoice) {
                return new ListViewCellUser();
            }
        });
    }

    @FXML
    public void handleDelete() {
        updater.deleteGroup(Integer.toString(group.getGroupId()));
        dialogStage.close();
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleSave() {
        if (groupNameField.getText().isEmpty()) {
            showAlert();
        } else {
            updater.addGroup(groupNameField.getText(), getUserIDs());
            dialogStage.close();
        }
    }

    private ArrayList<Integer> getUserIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (User user : selectedUser) {
            ids.add(user.getUserId());
        }
        return ids;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Der Gruppenname darf nicht leer sein!");
        alert.setContentText("Bitte überprüfe den Gruppennamen");

        alert.showAndWait();
    }

    @Override
    public void handle(MouseEvent event) {
            selectedUser = userChoice.getSelectionModel().getSelectedItems();
    }
}
