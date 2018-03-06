package im;

import im.controller.ChatOverviewController;
import im.controller.ChatViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane chatLayout;

    private ChatViewController chatViewController;
    private ChatOverviewController chatOverviewController;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Messenger Client");

        initRootLayout();
        showChatOverview();
        showChatView();
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