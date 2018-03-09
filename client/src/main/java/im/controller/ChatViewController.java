package im.controller;

import im.MainApp;
import im.core.Updater;
import im.model.classes.Chat;
import im.model.classes.ClientMessage;
import im.model.listCells.ListViewCellGroup;
import im.model.listCells.ListViewCellMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import rest.services.GroupRestClientImpl;

public class ChatViewController implements EventHandler<ActionEvent> {
    @FXML
    private ListView lvMessages;
    @FXML
    private Button btn;
    @FXML
    private TextField mb;

    private Chat chat;
    private MainApp mainApp;

    public ChatViewController() {
    }

    @FXML
    private void initialize() {
        setListView(null);
        btn.setOnAction(this);
    }

    public void setListView(ObservableList observableList) {
        lvMessages.setItems(observableList);
        lvMessages.setCellFactory(new Callback<ListView<ClientMessage>, ListCell<ClientMessage>>() {
            public ListCell<ClientMessage> call(ListView<ClientMessage> lvChats) {
                return new ListViewCellMessage();
            }
        });
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        lvMessages.setItems(chat.getMessageList());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void handle(ActionEvent event) {
        String sendMessage = mb.getText();
        new GroupRestClientImpl().postMessage(chat.getStringId(),sendMessage);
        new Updater().updateMessagesForGroup(chat.getStringId());
        System.out.println(chat.getName() + sendMessage);
        mb.clear();
    }
}
