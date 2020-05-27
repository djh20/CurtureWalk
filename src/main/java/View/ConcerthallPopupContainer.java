package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

public class ConcerthallPopupContainer extends BorderPane {

    private GridPane info;
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

    ConcerthallPopupContainer(){
        info = new GridPane();
        title = new Label("전송받을 시설명(공연장)");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: black; -fx-background-radius: 10");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        info.setMinSize(300,300);
        Column[0] = new Label("전화번호");
        Column[1] = new Label("주소");
        Column[2] = new Label("홈페이지");
        Column[3] = new Label("주차공간");
        Column[4] = new Label("좌석 수");
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시 xx구");
        contents[2] = new Label("www");
        contents[3] = new Label("없음");
        contents[4] = new Label("5000");
        for(int i=0; i<5; i++){
            Column[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        concert = new TableView<>();
        concertContents[0] = new TableColumn<>("공연 이름");
        concertContents[1] = new TableColumn<>("시작일");
        concertContents[2] = new TableColumn<>("종료일");
        concertContents[3] = new TableColumn<>("공연 시간");
        concertContents[4] = new TableColumn<>("입장료");
        concertContents[5] = new TableColumn<>("입장나이");
        concertContents[6] = new TableColumn<>("소개");
        concertContents[6].setMinWidth(150);
        concert.getColumns().addAll(concertContents);
        concert.setMinSize(200,200);
        info.setGridLinesVisible(true);
        info.addColumn(0,Column);
        info.addColumn(1,contents);
        info.setAlignment(Pos.CENTER);
        info.getColumnConstraints().add(new ColumnConstraints(130));
        info.getColumnConstraints().add(new ColumnConstraints(130));
        Button a = new Button("지도");
        a.setMinWidth(300);
        a.setMinHeight(300);
        GridPane center = new GridPane();
        center.setMinWidth(600);
        center.add(info,0,0);
        center.add(a,1,0);
        center.setAlignment(Pos.CENTER);
        Label bottomTitle = new Label("공연 정보");
        bottomTitle.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");
        VBox bottom = new VBox(bottomTitle, concert);
        bottom.setAlignment(Pos.CENTER);
        this.setTop(vbox);
        this.setCenter(center);
        this.setBottom(bottom);
    }
}
