package View.Popup;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LoadingPopup extends Popup {
    public LoadingPopup() {
        BorderPane borderPane = new BorderPane();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream("images/Spinner.gif");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        borderPane.setCenter(imageView);
        getContent().add(borderPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void show(Stage stage){
        new Thread(new Runnable() {
            @Override
            public void run() {
                show(stage);
            }
        }).start();
    }

}
