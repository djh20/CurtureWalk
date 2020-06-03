package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class RuinsPopupContainer extends BorderPane {


    private HBox totalBox = new HBox();
    private VBox attribute = new VBox();
    private VBox contentBox = new VBox();
    private Label title;
    private Label Column[] = new Label[5];
    private Label contents[] = new Label[5];

    RuinsPopupContainer(){

        title = new Label("전송받을 시설명(유적)");
        title.getStyleClass().add("popupTitle");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("popupTitleBar");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);

        Column[0] = new Label("전화번호");//phoneNum
        Column[1] = new Label("주소");//address
        Column[2] = new Label("유적분류");//ruinsType
        Column[3] = new Label("시대");//creationPeriod
        Column[4] = new Label("소개");//introduce
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시");
        contents[2] = new Label("자연유산");
        contents[3] = new Label("고려시대");
        contents[4] = new Label("이것은 \n 문화유적 \n 입니다");
        //소개 내용 넣을 때 점 단위로 스플릿해서 사이에 "\n" 넣어주기
        for(int i=0; i<5; i++){
            Column[i].getStyleClass().add("popuplabel");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].getStyleClass().add("popuplabel");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[4].setStyle("-fx-font-size: 14;");
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
