package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class FrmConfig {

    //region Declarations
    @FXML // fx:id="rbWindowsAuthentication"
    private RadioButton rbWindowsAuthentication; // Value injected by FXMLLoader

    @FXML // fx:id="txtServerName"
    private TextField txtServerName; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancel"
    private Button btnCancel; // Value injected by FXMLLoader

    @FXML // fx:id="rbSQLAuthentication"
    private RadioButton rbSQLAuthentication; // Value injected by FXMLLoader

    @FXML // fx:id="txtUserName"
    private TextField txtUserName; // Value injected by FXMLLoader

    @FXML // fx:id="lblStatusValue"
    private Label lblStatusValue; // Value injected by FXMLLoader

    @FXML // fx:id="lblStatusValue2"
    private Label lblStatusValue2; // Value injected by FXMLLoader

    @FXML // fx:id="txtDatabaseName"
    private TextField txtDatabaseName; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassword"
    private PasswordField txtPassword; // Value injected by FXMLLoader

    //endregion Declarations

    //region FXML Methods
    @FXML
    void testConnectionOnMouseClicked() {
        if (!testConnection()) {
            lblStatusValue.setText("The connection can be made!");
            lblStatusValue2.setText("");
        } else {
            lblStatusValue.setText("Connection ERROR!");
            lblStatusValue2.setText("Please insert the informations again.");
        }
    }

    @FXML
    void setRbWindowsAuthenticationOnAction() {
        radioButtonsSelect(true);
    }

    @FXML
    void setRbSQLAuthenticationOnAction() {
        radioButtonsSelect(false);
    }

    @FXML
    void setBtnCancelOnClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void setBtnOkOnClick() {
        if(txtDatabaseName.getText().isEmpty()){
            lblStatusValue.setText("ERROR!");
            lblStatusValue2.setText("Please insert the database name.");
        }else
        if (!testConnection()) {
            //Write the configuration file
            sample.utils.ConfigurationFile.SetConnectionProperties(
                    txtServerName.getText(),
                    rbWindowsAuthentication.isSelected(),
                    txtDatabaseName.getText(),
                    txtUserName.getText(),
                    txtPassword.getText());

            //Open main window
            try {
                //Close current window
                Stage stageClose = (Stage) btnCancel.getScene().getWindow();
                stageClose.close();

                //Open next window
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Ad Manager");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblStatusValue.setText("Connection ERROR!");
            lblStatusValue2.setText("Please insert the informations again.");
        }
    }


    @FXML
    void initialize() {
        radioButtonsSelect(true);
    }

    //endregion FXML Methods

    //region Private Methods
    private Boolean testConnection() {
        Boolean error;
        System.out.println("\nPlease wait to test the connection.");
        if (!txtServerName.getText().isEmpty()) {
            if (rbWindowsAuthentication.isSelected()) {
                String stringConnection = sample.utils.SqlConf.SetConnectionURL(
                        txtServerName.getText(),
                        rbWindowsAuthentication.isSelected());
                System.out.println("String Connection for test: " + stringConnection);

                error = !sample.utils.SqlConf.TestConnection(stringConnection);
            } else {
                String stringConnection = sample.utils.SqlConf.SetConnectionURL(
                        txtServerName.getText(),
                        txtDatabaseName.getText(),
                        rbWindowsAuthentication.isSelected(),
                        txtUserName.getText(),
                        txtPassword.getText());
                System.out.println("String Connection for test: " + stringConnection);

                error = !sample.utils.SqlConf.TestConnection(stringConnection);
            }
        } else {
            error = true;
        }
        System.out.println("The test was done! " + (error ?  "You have errors.":"You don't have errors."));
        return error;
    }

    private void radioButtonsSelect(Boolean chooseWinAuth) {
        txtPassword.setDisable(chooseWinAuth);
        txtUserName.setDisable(chooseWinAuth);
        rbSQLAuthentication.setSelected(!chooseWinAuth);
        rbWindowsAuthentication.setSelected(chooseWinAuth);
    }

    //endregion Private Methods
}