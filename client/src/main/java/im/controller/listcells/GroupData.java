package im.controller.listcells;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Controller for ListCellGroupItem
 */
public class GroupData {
    @FXML
    private HBox hBox;
    @FXML
    private Label groupName;

    /**
     * standard constructor init the layout for an list item
     */
    public GroupData() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ListCellGroupItem.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the info of the group name in the list item
     *
     * @param string the info for set
     */
    public void setInfo(String string) {
        groupName.setText(string);
    }

    public HBox getBox() {
        return hBox;
    }
}
