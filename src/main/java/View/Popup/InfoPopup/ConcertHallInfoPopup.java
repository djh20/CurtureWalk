package View.Popup.InfoPopup;

import MetaData.GuiMetaData;
import MetaData.Protocol;
import Model.ConcertHall;
import Model.ConcertInfo;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import View.CustomAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;

public class ConcertHallInfoPopup extends InfoPopup {


    private final String[] cols = {"전화번호", "주소", "홈페이지", "주차공간", "좌석 수"};
    private Button popupConcertinfo;
    private HBox seperator = new HBox();
    private ConcertHall concertHall;

    public ConcertHallInfoPopup(){
        super();
        for(String col : cols){
            popupitems.put(col, new Popupitem(col));
        }
        contentBox.getChildren().addAll(popupitems.values());
        popupConcertinfo = new Button("공연 정보 보기");
        popupConcertinfo.setMinSize(100,30);
        popupConcertinfo.getStyleClass().add("popupButton");
        VBox bottom = new VBox();
        bottom.setMinSize(100,30);
        bottom.getChildren().add(popupConcertinfo);
        bottom.setAlignment(Pos.CENTER);
        seperator.setMinSize(GuiMetaData.MAPVIEW_WIDTH/4*3,10);
        contentBox.getChildren().addAll(seperator,bottom);
    }

    @Override
    public void setContent(Place place) {
        ConcertHall concertHall = (ConcertHall) place;
        popupConcertinfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Popup pop = new Popup();
                pop.setAutoHide(true);
                Window stage = popupConcertinfo.getScene().getWindow();
                Place[] tmp = {concertHall};
                Packet receive = DataManager.getInstance().requestData(new Packet<Place>(Protocol.REQ_CONCERT_INFO, tmp));
                if(receive != null) {
                    ConcertInfo[] result = (ConcertInfo[]) receive.getData();
                    pop.getContent().add(new ConcertInfoPopup(concertHall, result));
                    pop.show(stage);
                }
                else
                    new CustomAlert("정보가 없습니다");
            }
        });
        title.setText(concertHall.getName());
        popupitems.get("전화번호").setContent(concertHall.getPhoneNum());
        popupitems.get("주소").setContent(concertHall.getAddress());
        popupitems.get("홈페이지").setContent(concertHall.getSiteUrl());
        popupitems.get("주차공간").setContent(concertHall.getHasParkingLot());
        popupitems.get("좌석 수").setContent(concertHall.getNumSeat());
        validateLabels();
        initMapVIew(place);
    }
}
