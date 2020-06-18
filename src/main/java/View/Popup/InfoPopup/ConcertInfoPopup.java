package View.Popup.InfoPopup;

import MetaData.Protocol;
import Model.ConcertHall;
import Model.ConcertInfo;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.LinkedHashMap;

public class ConcertInfoPopup extends BorderPane {
    private TableView table;
    private double size = 0;
    private final LinkedHashMap<String, String> cols = new LinkedHashMap<String, String>(){{
        put("공연이름","concertName");
        put("시작일","concertStartDay");
        put("종료일","concertEndDay");
        put("공연 시간","concertTime");
        put("입장료","entrancePrice");
        put("입장나이","entranceAge");
        put("소개","introduce");
    }};

    public ConcertInfoPopup(ConcertHall concertHall, ConcertInfo[] concertInfos) {

        this.table = new TableView<>();
        setCenter(table);
        for(String col : cols.keySet()){
            System.out.println(col);
            TableColumn tableColumn = new TableColumn(col);
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(cols.get(col)));
            size += tableColumn.prefWidthProperty().get();
            table.getColumns().add(tableColumn);
        }
        System.out.println(size);
        table.setMinWidth(size);
        this.setMinWidth(size);
        addRows(concertInfos);

    }


    private void addRows(ConcertInfo[] concertInfos){
        for(ConcertInfo concertInfo : concertInfos){
            System.out.println(concertInfo.getConcertName());
            table.getItems().add(concertInfo);
        }
    }


    private void resize(){

    }
}
