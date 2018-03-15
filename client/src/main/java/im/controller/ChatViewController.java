package im.controller;

import im.InstantMessengerClient;
import im.core.OwnUser;
import im.core.Updater;
import im.model.Chat;
import im.model.ClientMessage;
import im.model.listCells.ListViewCellMessage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import model.classes.MessageImpl;
import rest.services.GroupRestClientImpl;

public class ChatViewController implements EventHandler<KeyEvent> {
    @FXML
    private ListView<ClientMessage> lvMessages;
    @FXML
    private Button btn;
    @FXML
    private TextField mb;

    private Chat chat;
    private InstantMessengerClient mainApp;

    public ChatViewController() {
    }

    /**
     * is called if the layout id initialized
     * sets the messageList
     */
    @FXML
    private void initialize() {
        setListView(null);
        mb.setOnKeyPressed(this);
    }

    /**
     * set the List which is observed by the listView and the CellFactory for displaying the content of message
     *
     * @param observableList
     */
    private void setListView(ObservableList<ClientMessage> observableList) {
        lvMessages.setItems(observableList);
        lvMessages.setCellFactory(new Callback<ListView<ClientMessage>, ListCell<ClientMessage>>() {
            public ListCell<ClientMessage> call(ListView<ClientMessage> lvChats) {
                return new ListViewCellMessage();
            }
        });
    }

    /**
     * set the Chat, whose messages are displayed;
     *
     * @param chat
     */
    public void setChat(Chat chat) {
        this.chat = chat;
        setListView(this.chat.getMessageList());
        //lvMessages.setItems(this.chat.getMessageList());
    }

    /**
     * handle the click event on the send button and post the message to server
     */
    @FXML
    private void handleSend() {
        if(mb.getText().isEmpty()) return;
        String sendMessage = mb.getText();
        Updater updater = new Updater();
        updater.postMessage(chat.getStringId(),sendMessage);
        updater.updateMessagesForGroup(chat.getStringId());
        mb.clear();
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * handle the enter event on the textfield and post the message to server
     *
     * @param event
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) handleSend();
    }
}
