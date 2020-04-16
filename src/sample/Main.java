package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        try {

            Parent root = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
            primaryStage.setTitle("Sample JavaFX Application");
            primaryStage.setScene(new Scene(root, 300, 275));
            primaryStage.show();


        }

        catch(IOException e){

            e.printStackTrace();

        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
