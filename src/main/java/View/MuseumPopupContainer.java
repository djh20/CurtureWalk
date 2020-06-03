package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class MuseumPopupContainer extends BorderPane {

    private Label title;
    private Label Column[] = new Label[11];
    private Label contents[] = new Label[11];
    private HBox totalBox = new HBox();
    private VBox attribute = new VBox();
    private VBox contentBox = new VBox();

    MuseumPopupContainer(){ //장소객체 받아오는걸로 바꾸기

        title = new Label("전송받을 시설명(박물관)");
        title.getStyleClass().add("popupTitle");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("popupTitleBar");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);

        Column[0] = new Label("전화번호"); //phoneNum
        Column[1] = new Label("주소"); //address
        Column[2] = new Label("홈페이지"); //siteURL
        Column[3] = new Label("관리시설"); //operatingInstitution
        Column[4] = new Label("평일 관람시간"); //weekdayOpen~close
        Column[5] = new Label("주말 관람시간"); //weekendOpen~Close
        Column[6] = new Label("휴관정보"); //closedInfo
        Column[7] = new Label("성인 입장료"); //adultPrice
        Column[8] = new Label("청소년 입장료"); //teenagerPrice
        Column[9] = new Label("아이 입장료"); //childrenPrice
        Column[10] = new Label("소개");
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시 xx구");
        contents[2] = new Label("금오공대");
        contents[3] = new Label("www");
        contents[4] = new Label("00~00");
        contents[5] = new Label("00~00");
        contents[6] = new Label("매주 일요일");
        contents[7] = new Label("0원");
        contents[8] = new Label("0원");
        contents[9] = new Label("0원");
        contents[10] = new Label("소개내용");
        //소개 내용 넣을 때 점 단위로 스플릿해서 사이에 "\n" 넣어주기
        for(int i=0; i<11; i++){
            Column[i].getStyleClass().add("popuplabel");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].getStyleClass().add("popuplabel");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[10].setStyle("-fx-font-size: 14; ");
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
        this.setRight(totalBox);
        this.setLeft(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3));
    }
}
