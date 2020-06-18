import MetaData.GuiMetaData;
import Network.DataManager;
import View.Home.MainView;
import View.Home.MainViewContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main  extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new MainView(), GuiMetaData.WINDOW_WIDTH, GuiMetaData.WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        MainViewContainer.getInstance();
        DataManager.getInstance();
        stage.show();
    }

}
