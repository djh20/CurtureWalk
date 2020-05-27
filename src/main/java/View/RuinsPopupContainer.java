package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class RuinsPopupContainer extends BorderPane {


    private GridPane info;
    private Label title;
    private Label Column[] = new Label[4];
    private Label contents[] = new Label[4];

    RuinsPopupContainer(){
        info = new GridPane();
        title = new Label("전송받을 시설명(유적)");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: black; -fx-background-radius: 10");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        info.setMinSize(300,300);
        Column[0] = new Label("전화번호");
        Column[1] = new Label("주소");
        Column[2] = new Label("종류");
        Column[3] = new Label("소개");
        //전송받은 객체 내용 넣기
        contents[0] = new Label("0000");
        contents[1] = new Label("xx시");
        contents[2] = new Label("문화유적");
        contents[3] = new Label("이것은 \n 문화유적 \n 입니다");
        //소개 내용 넣을 때 점 단위로 스플릿해서 사이에 "\n" 넣어주기
        for(int i=0; i<4; i++){
            Column[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[3].setStyle("-fx-font-size: 14;");
        info.setGridLinesVisible(true);
        info.addColumn(0,Column);
        info.addColumn(1,contents);
        info.setAlignment(Pos.CENTER);
        info.getColumnConstraints().add(new ColumnConstraints(130));
        info.getColumnConstraints().add(new ColumnConstraints(130));
        Button a = new Button("지도");
        a.setMinWidth(300);
        a.setMinHeight(300);
        this.setTop(vbox);
        this.setCenter(info);
        this.setBottom(a);
    }
}
