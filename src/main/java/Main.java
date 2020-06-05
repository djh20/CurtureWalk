import MetaData.GuiSizeMetaData;
import View.MainView;
import View.MainViewContainer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
