package im.controller;

import im.MainApp;
import javafx.fxml.FXML;
import model.classes.GroupImpl;
import model.interfaces.Group;

public class RootLayoutController  {
    private MainApp mainApp;

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleNewGroup() {
        Group group = new GroupImpl();
        mainApp.showGroupEditDialog(group);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}