package im.controller.listcells;

import im.model.ClientMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

/**
 * Controller for ListCellMessageItem
 */
public class MessageData {
    @FXML
    private HBox hBox;
    @FXML
    private Label messageAuthor;
    @FXML
    private Label messageContent;

    /**
     * standard constructor init the layout for an list item
     */
    public MessageData() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ListCellMessageItem.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * set the info of the message in the list item
     *
     * @param cm the message with the info
     */
    public void setInfo(ClientMessage cm) {
        messageAuthor.setText(cm.getAuthor());
        messageContent.setText(cm.getContent());
    }

    public HBox getBox() {
        return hBox;
    }
}
