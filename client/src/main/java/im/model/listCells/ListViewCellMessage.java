package im.model.listCells;

import im.controller.listcells.MessageData;
import im.model.ClientMessage;
import javafx.scene.control.ListCell;

public class ListViewCellMessage extends ListCell<ClientMessage> {
    @Override
    public void updateItem(ClientMessage cm, boolean empty) {
        super.updateItem(cm, empty);
        if (cm != null) {
            MessageData messageData = new MessageData();
            messageData.setInfo(cm);
            setGraphic(messageData.getBox());
        }
    }

}
