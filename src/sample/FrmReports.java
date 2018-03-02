package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class FrmReports {
    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // fx:id="pnExecut"
    private Pane pnExecut; // Value injected by FXMLLoader

    //region Menu Item Functionality
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
    void setMenuItemReportsOnClick(){
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("frmReports.fxml"));
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
    //endregion
}
