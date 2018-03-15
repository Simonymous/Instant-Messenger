package im.controller;

import im.InstantMessengerClient;
import im.core.Updater;
import im.model.Chat;
import im.model.classes.GroupImpl;
import im.model.interfaces.Group;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class RootLayoutController {

    @FXML
    private Menu menuGroup;
    @FXML
    private MenuItem itemChUsername;
    @FXML
    private MenuItem itemChPassword;
    @FXML
    private MenuItem itemUpdateAll;

    private InstantMessengerClient mainApp;

    public RootLayoutController() {
    }

    /**
     * is called if the layout is initialized
     */
    @FXML
    private void initialize() {
    }

    /**
     * handle click on menuItem Update All, and updates everything
     */
    @FXML
    private void handleUpdate() {
        Updater updater = new Updater();
        updater.updateAll();
    }

    /**
     * handle click on menuItem Exit, and close the InstantMessenger
     */
    @FXML
    private void handleExit() {
        mainApp.getPrimaryStage().close();
    }

    /**
     * handle click on menuItem EditUsername, and show the dialog for changes
     */
    @FXML
    private void handleEditUsername() {
        mainApp.showChangeUsernameForm();
    }

    /**
     * handle click on menuItem EditPassword, and show the dialog for changes
     */
    @FXML
    private void handleEditPassword() {
        mainApp.showChangePasswordForm();
    }

    /**
     * handle click on menuItem NewGroup, and show the dialog for content
     */
    @FXML
    private void handleNewGroup() {
        mainApp.showGroupEditDialog(null);
    }

    /**
     * handle click on menuItem EditGroup, and show the dialog for changes
     */
    @FXML
    private void handleEditGroup() {
        Chat chat = mainApp.getChatOverviewController().getSelected();
        Group group = new GroupImpl(chat.getId(), chat.getName());
        mainApp.showGroupEditDialog(group);
    }

    /**
     * handle click on menuItem DeleteGroup, and deletes the selected group
     */
    @FXML
    private void handleDeleteGroup() {
        Chat chat = mainApp.getChatOverviewController().getSelected();
        Group group = new GroupImpl(chat.getId(), chat.getName());
        new Updater().deleteGroup(Integer.toString(group.getGroupId()));
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * sets the hidden menuItems if the login was successful
     */
    public void setMenuVisable() {
        menuGroup.setVisible(true);
        itemChUsername.setVisible(true);
        itemChPassword.setVisible(true);
        itemUpdateAll.setVisible(true);
    }
}