package im.model.listCells;

import im.controller.listcells.UserData;
import javafx.scene.control.ListCell;
import model.interfaces.User;

public class ListViewCellUser extends ListCell<User> {
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (user != null) {
            UserData userData = new UserData();
            userData.setInfo(user);
            setGraphic(userData.getBox());
        }
    }
}
