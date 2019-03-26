package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class {@code Client} contains
 * starts the client that uses javafx scene
 * @author Alexander Valai
 */

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("periodicalGUI.fxml"));
        primaryStage.setTitle("Periodical GUI");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setHeight(500);
        primaryStage.setWidth(600);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
