package im.controller;

import im.core.OwnUser;
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

    public static final String ERR_EMPTY_GROUP_HEADER = "Der Gruppenname darf nicht leer sein!";
    public static final String ERR_EMPTY_GROUP_CONTENT = "Bitte überprüfe den Gruppennamen";
    public static final String ERR_EMPTY_USER_HEADER = "Kein User Ausgewählt!";
    public static final String ERR_EMPTY_USER_CONTENT = "Bitte mindestens einen User wählen.";

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

    /**
     * is called if the layout is initialized
     * sets the selection mode of ListView to multiple and fill with users
     */
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
        if(group == null) doSave();
        else doUpdate();
    }

    private void doSave() {
        if (groupNameField.getText().isEmpty()) {
            showAlert(ERR_EMPTY_GROUP_HEADER, ERR_EMPTY_GROUP_CONTENT);
        } else if (userChoice.getSelectionModel().isEmpty()) {
            showAlert(ERR_EMPTY_USER_HEADER, ERR_EMPTY_USER_CONTENT);
        } else {
            updater.addGroup(groupNameField.getText(), getUserIDs());
            dialogStage.close();
        }
    }

    private void doUpdate() {
        if (groupNameField.getText().isEmpty()) {
            showAlert(ERR_EMPTY_GROUP_HEADER, ERR_EMPTY_GROUP_CONTENT);
        } else {
            group.setGroupName(groupNameField.getText());
            updater.updateGroup(group);
            dialogStage.close();
        }
    }

    private ArrayList<Integer> getUserIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (User user : selectedUser) {
            ids.add(user.getUserId());
        }
        ids.add(OwnUser.getInstance().getUserId());
        return ids;
    }

    public void setGroup(Group group) {
        this.group = group;
        if (this.group != null) {
            deleteButton.setVisible(true);
            groupNameField.setText(this.group.getGroupName());
            saveButton.setText("Update");
            userChoice.setVisible(false);
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    @Override
    public void handle(MouseEvent event) {
        selectedUser = userChoice.getSelectionModel().getSelectedItems();
    }
}
