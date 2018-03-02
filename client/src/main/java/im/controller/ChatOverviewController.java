package im.controller;

import im.MainApp;
import im.model.interfaces.Group;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ChatOverviewController {
    @FXML
    private ListView<Group> lvChats;
    private ListCell<Group> zelle;


    private MainApp mainApp;

    public ChatOverviewController() {
    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
}