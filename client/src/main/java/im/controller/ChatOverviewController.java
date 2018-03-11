package im.controller;

import im.InstantMessengerClient;
import im.model.Chat;
import im.model.ChatList;
import im.model.listCells.ListViewCellGroup;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ChatOverviewController implements EventHandler<MouseEvent> {
    @FXML
    private ListView lvChats;

    private ChatList chatList;
    private InstantMessengerClient mainApp;

    public ChatOverviewController() {
    }

    /**
     * is called if the layout id initialized
     * sets the chatList
     */
    @FXML
    private void initialize() {
        chatList = ChatList.getInstance();
        setListView(chatList.getChatList());
        lvChats.setOnMouseClicked(this);
    }

    /**
     * set the List which is is observed by the listview and the CellFactory for displying the content of group/chat
     *
     * @param observableList
     */
    private void setListView(ObservableList observableList) {
        lvChats.setItems(observableList);
        lvChats.setCellFactory(new Callback<ListView<Chat>, ListCell<Chat>>() {
            public ListCell<Chat> call(ListView<Chat> lvChats) {
                return new ListViewCellGroup();
            }
        });
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * handle the click event on listview for changing the group/chat which is shown
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        String chat = lvChats.getSelectionModel().getSelectedItem().toString();
        mainApp.getChatViewController().setChat(chatList.getChatByName(chat));
        System.out.println(chat);
    }
}