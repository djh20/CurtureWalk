package View.Home;

import MetaData.Protocol;
import Model.Place;
import Network.DataManager;
import Network.Packet;
import impl.org.controlsfx.skin.AutoCompletePopup;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class AutoCompleteTextField extends TextField {
    private AutoCompletePopup autoCompletePopup = new AutoCompletePopup();
    private Place selectedPlace = null;
    //TODO 서버 연결하고 나서 데이터 받고 연결하기
    public AutoCompleteTextField(double width, double height) {
        setMinSize(width, height);
    }

    public AutoCompletePopup getAutoCompletePopup() {
        return autoCompletePopup;
    }

    public void setAutoCompletePopup(AutoCompletePopup autoCompletePopup) {
        this.autoCompletePopup = autoCompletePopup;
    }

    public void setSelectedPlace(Place selectedPlace) {
        this.selectedPlace = selectedPlace;
    }

    public Place getSelectedPlace() {
        return selectedPlace;
    }
}
