package im.model.listCells;

import im.controller.listcells.UserData;
import im.model.interfaces.User;
import javafx.application.Platform;
import javafx.scene.control.ListCell;

/**
 * represents the listCell of UserList in GroupEditDialog
 */
public class ListViewCellUser extends ListCell<User> {
    /**
     * update an item an show it in ListView,
     *
     * @param user  the user that is updated
     * @param empty
     */
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
