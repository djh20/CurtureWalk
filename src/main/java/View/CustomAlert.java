package View;

import MetaData.GuiMetaData;
import MetaData.ShortestPathApiMetaData;
import Model.Path;
import Model.PublicTransportPath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class CustomAlert extends javafx.scene.control.Alert {
    public CustomAlert(String content) {
        super(AlertType.WARNING);
        setTitle("경고");
        setContentText(content);
        show();
    }
}
