package View;

import MetaData.GuiSizeMetaData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

public class ConcerthallPopupContainer extends BorderPane {

    private Label title;
    private Label Column[] = new Label[5];
    private Label contents[] = new Label[5];
    private TableView<String> concert; //콘서트 모델 완성 시 String to Concert
    private TableColumn<String, String> concertContents[] = new TableColumn[7];
    private TableColumn<String, String> concertName;
    private TableColumn<String, String> concertStartDay;
    private TableColumn<String, String> concertEndDay;
    private TableColumn<String, String> concertTime;
    private TableColumn<String, String> concertEntrancePrice;
    private TableColumn<String, String> concertEntranceAge;
    private TableColumn<String, String> concertIntroduce;
    private Button popupConcertinfo;
    private HBox totalBox = new HBox();
    private VBox attribute = new VBox();
    private VBox contentBox = new VBox();
    private HBox seperator = new HBox();

    ConcerthallPopupContainer(){
        title = new Label("전송받을 시설명(공연장)");
        title.getStyleClass().add("popupTitle");
        VBox vbox = new VBox();
        vbox.getStyleClass().add("popupTitleBar");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        Column[0] = new Label("전화번호");//PhoneNum
        Column[1] = new Label("주소");//address
        Column[2] = new Label("홈페이지");//siteUrl
        Column[3] = new Label("주차공간");//hasParkingLot
        Column[4] = new Label("좌석 수");//numSeat
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시 xx구");
        contents[2] = new Label("www");
        contents[3] = new Label("없음");
        contents[4] = new Label("5000");
        for(int i=0; i<5; i++){
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



        popupConcertinfo = new Button("공연 정보 보기");
        popupConcertinfo.setMinSize(100,30);
        popupConcertinfo.getStyleClass().add("popupButton");
        popupConcertinfo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Popup pop = new Popup();
                pop.setAutoHide(true);
                Window stage = popupConcertinfo.getScene().getWindow();
                concert = new TableView<>();
                concertContents[0] = new TableColumn<>("공연 이름");//concertName
                concertContents[1] = new TableColumn<>("시작일");//concertStartDay
                concertContents[2] = new TableColumn<>("종료일");//concertEndDay
                concertContents[3] = new TableColumn<>("공연 시간");//concertTime
                concertContents[4] = new TableColumn<>("입장료");//isFree&entrancePrice
                concertContents[5] = new TableColumn<>("입장나이");//entranceAge
                concertContents[6] = new TableColumn<>("소개");//introduce
                concertContents[6].setMinWidth(190);
                concert.getColumns().addAll(concertContents);
                concert.setMinSize(200,200);
                Label bottomTitle = new Label("공연 정보");
                bottomTitle.getStyleClass().add("popupTitle");
                VBox bottom = new VBox(bottomTitle, concert);
                bottom.setAlignment(Pos.CENTER);
                pop.getContent().add(bottom); //조건에 따라 종류별 팝업컨테이너 넣기
                pop.getContent().get(0).getStyleClass().add("popupMain");
                pop.show(stage);
            }
        });

        seperator.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH/4*3,10);
        VBox left = new VBox();
        left.setMinSize(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3);
        left.getChildren().addAll(totalBox,seperator,popupConcertinfo);
        left.setAlignment(Pos.CENTER);


        this.setTop(vbox);
        this.setLeft(left);
        this.setRight(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3));
    }
}
