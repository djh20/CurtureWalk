package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MuseumPopupContainer extends BorderPane {
    private GridPane info;
    private Label title;
    private Label Column[] = new Label[11];
    private Label contents[] = new Label[11];

    MuseumPopupContainer(){ //장소객체 받아오는걸로 바꾸기
        info = new GridPane();
        title = new Label("전송받을 시설명(박물관)");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: black; -fx-background-radius: 10");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        info.setMinSize(300,300);
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
            Column[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[10].setStyle("-fx-font-size: 14; ");
        info.setGridLinesVisible(true);
        info.addColumn(0,Column);
        info.addColumn(1,contents);
        info.setAlignment(Pos.CENTER);
        info.getColumnConstraints().add(new ColumnConstraints(130));
        info.getColumnConstraints().add(new ColumnConstraints(130));

        this.setTop(vbox);
        this.setRight(info);
        this.setLeft(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3));
    }
}
