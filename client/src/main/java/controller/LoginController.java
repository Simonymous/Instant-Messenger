package instantMessenger.client.src.main.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

    public void checkForLogin(ActionEvent actionEvent) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("resources/ChatView.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}