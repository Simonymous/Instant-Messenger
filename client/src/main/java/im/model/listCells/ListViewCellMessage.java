package im.model.listCells;

import im.controller.listcells.MessageData;
import im.model.ClientMessage;
import javafx.application.Platform;
import javafx.scene.control.ListCell;

/**
 * represents the listCell of the MessageList in ChatView
 */
public class ListViewCellMessage extends ListCell<ClientMessage> {
    /**
     * update an item and show it in ListView
     *
     * @param cm    the clientMessage that is updated
     * @param empty
     */
    @Override
    public void updateItem(ClientMessage cm, boolean empty) {
        super.updateItem(cm, empty);
        if (cm != null) {
            final MessageData messageData = new MessageData();
            messageData.setInfo(cm);
            Platform.runLater(new Runnable() {
                /**
                 * avoid threading conflict between main and notificationThread
                 */
                @Override
                public void run() {
                    setGraphic(messageData.getBox());
                }
            });

        }
    }

}
