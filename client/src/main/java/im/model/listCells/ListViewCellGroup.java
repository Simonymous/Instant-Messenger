package im.model.listCells;

import im.controller.listcells.GroupData;
import im.model.Chat;
import javafx.application.Platform;
import javafx.scene.control.ListCell;

/**
 * represents the listCell of the ChatList in ChatOverView
 */
public class ListViewCellGroup extends ListCell<Chat> {
    /**
     * update an item an show it in ListView
     *
     * @param chat  the chat that is updated
     * @param empty
     */
    @Override
    public void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);
        if (chat != null) {
            final GroupData groupData = new GroupData();
            groupData.setInfo(chat.getName());
            Platform.runLater(new Runnable() {
                /**
                 * avoid threading conflict between main and notificationThread
                 */
                @Override
                public void run() {
                    setGraphic(groupData.getBox());
                }
            });
        }
    }

}
