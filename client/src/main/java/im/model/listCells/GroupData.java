package im.model.listCells;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import java.io.IOException;

public class GroupData
{
    @FXML
    private HBox hBox;
    @FXML
    private Label groupName;

    public GroupData() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/listCellGroupItem.fxml"));
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

    public void setInfo(String string)
    {
        groupName.setText(string);
    }

    public HBox getBox()
    {
        return hBox;
    }
}
