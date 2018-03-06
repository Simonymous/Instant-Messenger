package im.model.listCells;

import im.model.classes.ClientMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class MessageData
{
    @FXML
    private HBox hBox;
    @FXML
    private Label messageAuthor;
    @FXML
    private Label messageContent;

    public MessageData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listCellMessageItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setInfo(ClientMessage cm) {
        messageAuthor.setText(cm.getAuthor());
        messageContent.setText(cm.getContent());
    }

    public HBox getBox()
    {
        return hBox;
    }
}
