import MetaData.GuiSizeMetaData;
import View.MainView;
import View.MainViewContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main  extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Dasd");
        Scene scene = new Scene(new MainView(), GuiSizeMetaData.WINDOW_WIDTH,GuiSizeMetaData.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        MainViewContainer.getInstance();
        stage.show();
    }

}
