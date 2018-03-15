package im.model.listCells;

import im.controller.listcells.GroupData;
import im.model.Chat;
import javafx.application.Platform;
import javafx.scene.control.ListCell;

public class ListViewCellGroup extends ListCell<Chat> {
    @Override
    public void updateItem(Chat string, boolean empty) {
        super.updateItem(string, empty);
        if (string != null) {
            final GroupData groupData = new GroupData();
            groupData.setInfo(string.getName());
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
