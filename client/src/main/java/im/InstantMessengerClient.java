package im;

import im.controller.*;
import im.core.PushServer;
import im.core.Updater;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.interfaces.Group;

import java.io.IOException;

public class InstantMessengerClient extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane chatLayout;

    private RootLayoutController rootLayoutController;
    private ChatViewController chatViewController;
    private ChatOverviewController chatOverviewController;
    private LoginController loginController;
    private GroupEditDialogController groupEditDialogController;
    private RegisterFormController registerFormController;

    /**
     * program start, set the primaryStage and the Title
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Messenger Client");

        initRootLayout();
        showLogin();
    }

    /**
     * Method is called after login was succeed
     * for init the main layout of the messenger
     */
    public void loginSuccess() {
        rootLayoutController.setMenuVisable();
        showChatOverview();
        showChatView();
        new Updater().updateAll();
        try {
            new PushServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * init the Root of Layout with Menu bar
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * is called for add or editing a group/chat
     *
     * @param group the choosed or new group for editing
     */
    public void showGroupEditDialog(Group group) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/GroupEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("EditGroup");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            groupEditDialogController = loader.getController();
            groupEditDialogController.setDialogStage(dialogStage);
            groupEditDialogController.setGroup(group);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * is called for registering a new user
     *
     */
    public void showRegisterForm() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/RegisterForm.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Neuer Nutzer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            registerFormController = loader.getController();
            registerFormController.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * show the login Layout at start of program
     */
    private void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/Login.fxml"));
            AnchorPane login = loader.load();

            rootLayout.setCenter(login);

            loginController = loader.getController();
            loginController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the list of groups/chats at the left of root
     */
    public void showChatOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/ChatOverview.fxml"));
            AnchorPane chatOverview = (AnchorPane) loader.load();

            rootLayout.setLeft(chatOverview);


            // Give the controller access to the main app.
            chatOverviewController = loader.getController();
            chatOverviewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * shows the list of messages for the selected chat/group
     */
    public void showChatView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InstantMessengerClient.class.getClassLoader().getResource("fxml/ChatView.fxml"));
            AnchorPane chatView = (AnchorPane) loader.load();

            rootLayout.setCenter(chatView);

            chatViewController = loader.getController();
            chatViewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ChatViewController getChatViewController() {
        return chatViewController;
    }

    public ChatOverviewController getChatOverviewController() {
        return chatOverviewController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}