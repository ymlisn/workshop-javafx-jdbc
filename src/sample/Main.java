package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    public static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
                ScrollPane scrollpane = loader.load();

                scrollpane.setFitToHeight(true);
                scrollpane.setFitToWidth(true);

                mainScene = new Scene(scrollpane);


                primaryStage.setScene(mainScene);

                primaryStage.setTitle("Sample JavaFX application");

                primaryStage.show();
    }
        catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
