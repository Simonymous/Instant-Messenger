package im.controller.listcells;

import im.model.interfaces.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Controller for ListCellUserItem
 */
public class UserData {

    @FXML
    private HBox hBox;
    @FXML
    private Label label_id;
    @FXML
    private Label label_username;

    /**
     * standard constructor init the layout for an list item
     */
    public UserData() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListCellUserItem.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the info of the user in the list item
     *
     * @param user the user with the info
     */
    public void setInfo(User user) {
        label_id.setText(Integer.toString(user.getUserId()));
        label_username.setText(user.getUsername());
    }

    public HBox getBox() {
        return hBox;
    }
}
