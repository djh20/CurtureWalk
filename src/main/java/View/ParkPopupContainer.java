package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ParkPopupContainer extends BorderPane {


    private HBox totalBox = new HBox();
    private VBox attribute = new VBox();
    private VBox contentBox = new VBox();
    private Label title;
    private Label Column[] = new Label[3];
    private Label contents[] = new Label[3];

    ParkPopupContainer(){

        title = new Label("전송받을 시설명(공원)");
        title.getStyleClass().add("popupTitle");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("popupTitleBar");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);

        Column[0] = new Label("주소");//address
        Column[1] = new Label("공원분류");//type
        Column[2] = new Label("시설물");//facility
        //전송받은 객체 내용 넣기
        contents[0] = new Label("xx시");
        contents[1] = new Label("어린이공원");
        contents[2] = new Label("배구장/운동기구/조합놀이대/시소/그네/정자/화장실/벤치");
        //소개 내용 넣을 때 점 단위로 스플릿해서 사이에 "\n" 넣어주기
        for(int i=0; i<3; i++){
            Column[i].getStyleClass().add("popuplabel");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].getStyleClass().add("popuplabel");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[2].setStyle("-fx-font-size: 14;");

        attribute.setMinSize(150,300);
        attribute.getStyleClass().add("popupVBox");
        attribute.getChildren().addAll(Column);
        attribute.setAlignment(Pos.CENTER);
        contentBox.setMinSize(150,300);
        contentBox.getChildren().addAll(contents);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.getStyleClass().add("popupVBox");
        totalBox.getChildren().addAll(attribute,contentBox);
        totalBox.setAlignment(Pos.CENTER);


        this.setTop(vbox);
        this.setLeft(totalBox);
        this.setRight(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3));
    }
}
