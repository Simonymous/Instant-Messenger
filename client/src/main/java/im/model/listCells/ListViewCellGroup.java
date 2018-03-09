package im.model.listCells;

import im.controller.listcells.GroupData;
import im.model.Chat;
import javafx.scene.control.ListCell;

public class ListViewCellGroup extends ListCell<Chat> {
    @Override
    public void updateItem(Chat string, boolean empty) {
        super.updateItem(string, empty);
        if (string != null) {
            GroupData groupData = new GroupData();
            groupData.setInfo(string.getName());
            setGraphic(groupData.getBox());
        }
    }

}
