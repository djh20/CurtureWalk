package View;

import MetaData.GuiSizeMetaData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ParkPopupContainer extends BorderPane {


    private GridPane info;
    private Label title;
    private Label Column[] = new Label[3];
    private Label contents[] = new Label[3];

    ParkPopupContainer(){
        info = new GridPane();
        title = new Label("전송받을 시설명(공원)");
        title.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: white;");
        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: black; -fx-background-radius: 10");
        vbox.setMinWidth(300);
        vbox.getChildren().add(title);
        vbox.setAlignment(Pos.CENTER);
        info.setMinSize(300,300);
        Column[0] = new Label("주소");//address
        Column[1] = new Label("공원분류");//type
        Column[2] = new Label("시설물");//facility
        //전송받은 객체 내용 넣기
        contents[0] = new Label("xx시");
        contents[1] = new Label("어린이공원");
        contents[2] = new Label("배구장/운동기구/조합놀이대/시소/그네/정자/화장실/벤치");
        //소개 내용 넣을 때 점 단위로 스플릿해서 사이에 "\n" 넣어주기
        for(int i=0; i<3; i++){
            Column[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            Column[i].setMaxWidth(Double.MAX_VALUE);
            Column[i].setAlignment(Pos.CENTER);
            contents[i].setStyle("-fx-font-size: 24; -fx-font-weight: bold;");
            contents[i].setMaxWidth(Double.MAX_VALUE);
            contents[i].setAlignment(Pos.CENTER);
        }
        contents[2].setStyle("-fx-font-size: 14;");
        info.setGridLinesVisible(true);
        info.addColumn(0,Column);
        info.addColumn(1,contents);
        info.setAlignment(Pos.CENTER);
        info.getColumnConstraints().add(new ColumnConstraints(80));
        info.getColumnConstraints().add(new ColumnConstraints(200));
        Button a = new Button("지도");
        a.setMinWidth(300);
        a.setMinHeight(300);
        this.setTop(vbox);
        this.setRight(info);
        this.setLeft(new MapView(GuiSizeMetaData.MAPVIEW_WIDTH/4*3, GuiSizeMetaData.MAPVIEW_HEIGHT/4*3));
    }
}
