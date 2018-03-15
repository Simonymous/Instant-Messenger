package im.controller;

import im.InstantMessengerClient;
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
import im.model.interfaces.Group;
import im.model.interfaces.User;

import java.util.ArrayList;
import java.util.List;

public class GroupEditDialogController implements EventHandler<MouseEvent> {
    private static final String ERR_TITLE = "Fehler";
    private static final String ERR_EMPTY_GROUP_HEADER = "Der Gruppenname darf nicht leer sein!";
    private static final String ERR_EMPTY_GROUP_CONTENT = "Bitte überprüfe den Gruppennamen";
    private static final String ERR_EMPTY_USER_HEADER = "Kein User Ausgewählt!";
    private static final String ERR_EMPTY_USER_CONTENT = "Bitte mindestens einen User wählen.";

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

    private InstantMessengerClient mainApp;
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
        updater.updateLocalUsers();
        selectedUser = new ArrayList<>();

        setListView(UserList.getInstance().getServerUsers());
        userChoice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        userChoice.setOnMouseClicked(this);
    }

    /**
     * set the Users to the ListView and the cellFactory for displaying the content
     *
     * @param observableList
     */
    private void setListView(ObservableList<User> observableList) {
        userChoice.setItems(observableList);
        userChoice.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> userChoice) {
                return new ListViewCellUser();
            }
        });
    }

    /**
     * handle click on delete button, deletes the group and close the dialog
     */
    @FXML
    public void handleDelete() {
        updater.deleteGroup(Integer.toString(group.getGroupId()));
        dialogStage.close();
    }

    /**
     * handle clock on cancel button and close the dialog
     */
    @FXML
    public void handleCancel() {
        dialogStage.close();
    }

    /**
     * handle click on save button, saves the new/changed group and close the dialog
     */
    @FXML
    private void handleSave() {
        if (group == null) doSave();
        else doUpdate();
    }

    /**
     * save the content for an new group
     */
    private void doSave() {
        if (groupNameField.getText().isEmpty()) {
            mainApp.showAlert(ERR_TITLE, ERR_EMPTY_GROUP_HEADER, ERR_EMPTY_GROUP_CONTENT);
        } else if (userChoice.getSelectionModel().isEmpty()) {
            mainApp.showAlert(ERR_TITLE, ERR_EMPTY_USER_HEADER, ERR_EMPTY_USER_CONTENT);
        } else {
            updater.addGroup(groupNameField.getText(), getUserIDs());
            dialogStage.close();
        }
    }

    /**
     * save the content for an updated group
     */
    private void doUpdate() {
        if (groupNameField.getText().isEmpty()) {
            mainApp.showAlert(ERR_TITLE, ERR_EMPTY_GROUP_HEADER, ERR_EMPTY_GROUP_CONTENT);
        } else {
            group.setGroupName(groupNameField.getText());
            updater.updateGroup(group);
            dialogStage.close();
        }
    }

    /**
     * return the IDs of the selected users
     *
     * @return the userIDs
     */
    private ArrayList<Integer> getUserIDs() {
        ArrayList<Integer> ids = new ArrayList<>();
        for (User user : selectedUser) {
            ids.add(user.getUserId());
        }
        ids.add(OwnUser.getInstance().getUserId());
        return ids;
    }

    /**
     * set the group for update and change the text of the save button
     *
     * @param group
     */
    public void setGroup(Group group) {
        this.group = group;
        if (this.group != null) {
            deleteButton.setVisible(true);
            groupNameField.setText(this.group.getGroupName());
            saveButton.setText("Übernehmen");
            userChoice.setVisible(false);
        }
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * handle click on the listView and store the selected Users
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        selectedUser = userChoice.getSelectionModel().getSelectedItems();
    }
}
