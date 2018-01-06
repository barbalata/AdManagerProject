package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML // fx:id="paneTreeVeiw"
    private Pane paneTreeVeiw; // Value injected by FXMLLoader

    @FXML // fx:id="pnExecut"
    private Pane pnExecut; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        TreeItem<String> root = new TreeItem<>();
        root.setExpanded(true);

        TreeItem<String> stiAds = makeBranch("Ads", root);
        makeBranch("Details", stiAds);
        makeBranch("Edit", stiAds);
        makeBranch("Search", stiAds);

        TreeItem<String> stiAuthors = makeBranch("Authors", root);
        makeBranch("Details", stiAuthors);
        makeBranch("Edit", stiAuthors);
        makeBranch("Search", stiAuthors);

        TreeItem<String> stiClients = makeBranch("Clients", root);
        makeBranch("Details", stiClients);
        makeBranch("Edit", stiClients);
        makeBranch("Search", stiClients);

        TreeItem<String> stiReports = makeBranch("Reports", root);
        makeBranch("Details", stiReports);
        makeBranch("Edit", stiReports);
        makeBranch("Search", stiReports);

        TreeView<String> treeViewProjectStructure = new TreeView<>(root);
        treeViewProjectStructure.setShowRoot(false);
        treeViewProjectStructure.getSelectionModel().selectedIndexProperty()
                .addListener((v, oldValue, newValue) -> {
                    if (newValue.intValue() == 0) {
                        changeScene("frmAds.fxml");
                    }
                    if (newValue.intValue() == 1) {
                        changeScene("frmAuthors.fxml");
                    }
                    if (newValue.intValue() == 2) {
                        changeScene("frmClients.fxml");
                    }
                    if (newValue.intValue() == 3) {
                        changeScene("frmReports.fxml");
                    }
                });

        paneTreeVeiw.getChildren().add(treeViewProjectStructure);


    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> stringTreeItem = new TreeItem<>(title);
        stringTreeItem.setExpanded(false);
        parent.getChildren().add(stringTreeItem);

        return stringTreeItem;
    }

    private void changeScene(String fxmlFile) {
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
