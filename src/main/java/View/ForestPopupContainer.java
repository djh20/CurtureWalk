package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ForestPopupContainer extends BorderPane {


    private Label title;
    private Label Column[] = new Label[6];
    private Label contents[] = new Label[6];
    private HBox totalBox = new HBox();
    private VBox attribute = new VBox();
    private VBox contentBox = new VBox();

    ForestPopupContainer(){
        title = new Label("전송받을 시설명(숲)");
        title.getStyleClass().add("popupTitle");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("popupTitleBar");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        Column[0] = new Label("전화번호");//phoneNum
        Column[1] = new Label("주소");//address
        Column[2] = new Label("입장료");//entrancePrice
        Column[3] = new Label("홈페이지");//siteUrl
        Column[4] = new Label("숙박시설유무");//canStay
        Column[5] = new Label("보유시설물");//facility
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시");
        contents[2] = new Label("0원");
        contents[3] = new Label("www");
        contents[4] = new Label("Y");
        contents[5] = new Label("숲속의집, 오토캠핑장, 야영장, 카라반");
        for(int i=0; i<6; i++){
            Column[i].getStyleClass().add("popuplabel");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].getStyleClass().add("popuplabel");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
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
