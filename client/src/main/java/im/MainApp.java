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

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane chatLayout;

    private RootLayoutController rootLayoutController;
    private ChatViewController chatViewController;
    private ChatOverviewController chatOverviewController;
    private LoginController loginController;
    private GroupEditDialogController groupEditDialogController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Messenger Client");

        initRootLayout();
        showLogin();

    }

    public void loginSuccess(){
        showChatOverview();
        showChatView();
        new Updater().updateAll();
        try {
            new PushServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/RootLayout.fxml"));
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

    public void showGroupEditDialog(Group group){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/GroupEditDialog.fxml"));
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

    private void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/Login.fxml"));
            AnchorPane login = loader.load();

            rootLayout.setCenter(login);

            loginController = loader.getController();
            loginController.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChatOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/ChatOverview.fxml"));
            AnchorPane chatOverview = (AnchorPane) loader.load();

            rootLayout.setLeft(chatOverview);


            // Give the controller access to the main app.
            chatOverviewController = loader.getController();
            chatOverviewController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChatView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("fxml/ChatView.fxml"));
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