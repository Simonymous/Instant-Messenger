package im.model.listCells;

import im.controller.listcells.UserData;
import javafx.application.Platform;
import javafx.scene.control.ListCell;
import model.interfaces.User;

public class ListViewCellUser extends ListCell<User> {
    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (user != null) {
            final UserData userData = new UserData();
            userData.setInfo(user);
            Platform.runLater(new Runnable() {
                /**
                 * avoid threading conflict between main and notificationThread
                 */
                @Override
                public void run() {
                    setGraphic(userData.getBox());
                }
            });
        }
    }
}
