package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.ConfigurationFile;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ConfigurationFile configurationFile = new ConfigurationFile();
        if (configurationFile.getServer() == null || !utils.SqlConf.TestConnection()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("frmConfig.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                primaryStage.setTitle("Config the application");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                primaryStage.setTitle("Ad Manager");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
