package View;

import MetaData.GuiSizeMetaData;
import com.sun.java.accessibility.util.GUIInitializedListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SearchFragment extends BorderPane {
    private AutoCompleteTextField autoCompleteTextField;
    private Button search;
    private Button reset;

    public SearchFragment() {
        HBox buttonBox = new HBox();
        setStyle("-fx-padding: 0 10 0 10");
        autoCompleteTextField = new AutoCompleteTextField();
        //TODO 버튼 이미지화
        search = new Button();
        search.getStyleClass().add("search");
        reset= new Button();
        setOnActionResetButton();
        setOnActionSearchButton();
        setMinSize(GuiSizeMetaData.SEARCHFRAGMENT_WIDTH,GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        setMaxSize(GuiSizeMetaData.SEARCHFRAGMENT_WIDTH,GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);

        autoCompleteTextField.setMinSize(GuiSizeMetaData.WINDOW_WIDTH/4, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        autoCompleteTextField.setMaxSize(GuiSizeMetaData.WINDOW_WIDTH/4, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        search.setMinSize(GuiSizeMetaData.WINDOW_WIDTH/16, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        search.setMaxSize(GuiSizeMetaData.WINDOW_WIDTH/16, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        reset.setMinSize(GuiSizeMetaData.WINDOW_WIDTH/16, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        reset.setMaxSize(GuiSizeMetaData.WINDOW_WIDTH/16, GuiSizeMetaData.SEARCHFRAGMENT_HEIGHT);
        autoCompleteTextField.setMinWidth(380);
        reset.getStyleClass().add("reset");
        setLeft(autoCompleteTextField);

        buttonBox.getChildren().add(search);
        buttonBox.getChildren().add(reset);

        setRight(buttonBox);
        buttonBox.setSpacing(10);
    }

    //TODO setOnAction 등록하기
    private void setOnActionSearchButton(){
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    private void setOnActionResetButton(){
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
