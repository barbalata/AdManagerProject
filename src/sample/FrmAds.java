package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FrmAds {
    /*
    TODO : Make a interface
    TODO : Connect database with application
    TODO : Make an insert, delete and select in database from interface
    TODO : Make the fucking all procedures in database!
    */

    @FXML // fx:id="pnExecut"
    private Pane pnExecut; // Value injected by FXMLLoader

    @FXML
    void setMenuItemAdOnAction(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("frmAds.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setMenuItemAuthorOnClick(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("frmAuthors.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setMenuItemClientOnClick(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("frmClients.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setMenuItemRaportsOnClick(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("frmRaports.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setMenuItemAboutOnClick(){
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemBackupOnClick(){
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemExitOnClick(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        stage.close();
    }
}