package im.controller;

import im.core.Updater;
import im.model.classes.UserList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.classes.UserImpl;
import model.interfaces.Group;

import java.util.ArrayList;

public class GroupEditDialogController {
    @FXML
    private TextField groupNameField;
    @FXML
    private ListView userChoice;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button deleteButton;

    private Stage dialogStage;
    private Group group;

    private Updater updater;

    @FXML
    private void initialize() {
        //updater.updateLocalUsers();
        UserList.getInstance().addUser(new UserImpl("name1",""));
        UserList.getInstance().addUser(new UserImpl("name2",""));
        UserList.getInstance().addUser(new UserImpl("name3",""));

        userChoice.setItems(UserList.getInstance().getServerUsers());
        userChoice.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }


    @FXML
    public void handleDelete() {

    }

    @FXML
    public void handleClose() {

    }

    @FXML
    private void handleSave() {
      if(groupNameField.getText() == "") {
          //Alert
      } else {
          updater.addGroup(groupNameField.getText(), getUserIDs());
          dialogStage.close();
      }
    }

    private ArrayList<Integer> getUserIDs() {
        return null;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
