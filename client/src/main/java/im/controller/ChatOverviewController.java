package im.controller;

import im.MainApp;
import im.model.classes.Chat;
import im.model.classes.Chats;
import im.model.listCells.ListViewCellGroup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class ChatOverviewController implements EventHandler<MouseEvent> {
    @FXML
    private ListView lvChats;

    private Chats chats;
    private MainApp mainApp;

    public ChatOverviewController() {
    }

    @FXML
    private void initialize() {
        chats = Chats.getInstance();
        setListView(chats.getChatList());
        lvChats.setOnMouseClicked(this);
    }

    public void setListView(ObservableList observableList) {
        lvChats.setItems(observableList);
        lvChats.setCellFactory(new Callback<ListView<Chat>, ListCell<Chat>>() {
            public ListCell<Chat> call(ListView<Chat> lvChats) {
                return new ListViewCellGroup();
            }
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void handle(MouseEvent event) {
        String chat = lvChats.getSelectionModel().getSelectedItem().toString();
        mainApp.getChatViewController().setChat(chats.getChatByName(chat));
        System.out.println(chat);
    }

    public Chats getChats(){
        return chats;
    }
}