package im.controller;

import im.InstantMessengerClient;
import im.core.Updater;
import im.model.Chat;
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
        mainApp.showGroupEditDialog(null);
    }

    @FXML
    private void handleEditGroup() {
        Chat chat = mainApp.getChatOverviewController().getSelected();
        Group group = new GroupImpl(chat.getId(),chat.getName());
        mainApp.showGroupEditDialog(group);
    }

    @FXML
    private void handleDeleteGroup() {
        Chat chat = mainApp.getChatOverviewController().getSelected();
        Group group = new GroupImpl(chat.getId(),chat.getName());
        new Updater().deleteGroup(Integer.toString(group.getGroupId()));
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    public void setMenuVisable() {
        menuGroup.setVisible(true);
    }
}