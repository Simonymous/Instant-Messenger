package im.controller;

import im.InstantMessengerClient;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import model.classes.GroupImpl;
import model.interfaces.Group;

public class RootLayoutController {

    @FXML
    private Menu menuGroup;

    private InstantMessengerClient mainApp;

    public RootLayoutController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleExit() {
        mainApp.getPrimaryStage().close();
    }

    @FXML
    private void handleNewGroup() {
        Group group = new GroupImpl();
        mainApp.showGroupEditDialog(group);
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    public void setMenuVisable() {
        menuGroup.setVisible(true);
    }
}