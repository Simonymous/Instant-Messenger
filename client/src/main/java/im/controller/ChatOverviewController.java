package im.controller;

import im.InstantMessengerClient;
import im.model.Chat;
import im.model.ChatList;
import im.model.listCells.ListViewCellGroup;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class ChatOverviewController implements EventHandler<MouseEvent> {
    @FXML
    private ListView<Chat> lvChats;

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
     * set the List which is observed by the listView and the CellFactory for displaying the content of group/chat
     *
     * @param observableList
     */
    public void setListView(ObservableList<Chat> observableList) {
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

    public Chat getSelected() {
        return lvChats.getSelectionModel().getSelectedItem();
    }

    /**
     * handle the click event on listView for changing the group/chat which is shown
     *
     * @param event
     */
    @Override
    public void handle(MouseEvent event) {
        if (lvChats.getSelectionModel().getSelectedItem()!=null) {
            mainApp.getChatViewController().setChat(lvChats.getSelectionModel().getSelectedItem());
        }
    }
}