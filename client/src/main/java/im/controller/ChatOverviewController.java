package im.controller;

import im.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ChatOverviewController {
    @FXML
    private ListView lvChats;

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