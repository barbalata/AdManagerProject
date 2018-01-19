package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.DataAccesObject.AuthorsDAO;
import sample.Module.Author;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class FrmAuthors {

    //region Declaration
    @FXML
    private ComboBox<String> cbAuthorPrefixNumber;

    @FXML
    private TableColumn<Author, String> tableColPhone;

    @FXML
    private TextField txtLastNameValue;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEdit;

    @FXML
    private TextField txtFirstNameSearch;

    @FXML
    private TextField txtAuthorPrefixNumber;
    @FXML
    private Label lblStatusValue;

    @FXML
    private TableColumn<Author, String> tableColEmail;

    @FXML
    private TextField txtLastNameSearch;

    @FXML
    private TextField txtPhoneValue;

    @FXML
    private TextField txtEmailValue;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<Author, String> tableColFirstName;

    @FXML
    private Pane pnExecut;

    @FXML
    private TableView<Author> tableViewAds;

    @FXML
    private TextField txtFirstNameValue;

    @FXML
    private TableColumn<Author, String> tableColLastName;

    private boolean isEdit;
    //endregion

    //region FXML Methods
    @FXML
    void initialize() {
        cleanDetailsFields();
        cleanSearchFields();
        this.disableSelectiveButtons(true);
        this.setDisableFields(true);

        //Max length for txtAuthorPrefixNumber
        txtAuthorPrefixNumber.textProperty().addListener((ov, oldValue, newValue) -> {
            if (txtAuthorPrefixNumber.getText().length() > 3) {
                //Set the last key pressed in the txtPhoneValue box
                txtPhoneValue.setText(txtAuthorPrefixNumber.getText().substring(3));

                //Stop typing in the txtAuthorPrefixNumber
                String s = txtAuthorPrefixNumber.getText().substring(0, 3);
                txtAuthorPrefixNumber.setText(s);

                //Set cursor in txtAuthorPrefixNumber at last position
                txtPhoneValue.requestFocus();
                txtPhoneValue.positionCaret(1);
            }
        });

        //Max length txtPhoneValue
        txtPhoneValue.textProperty().addListener((ov, oldValue, newValue) -> {
            if (txtPhoneValue.getText().length() > 11) {
                //Stop typing in the txtPhoneValue
                String s = txtPhoneValue.getText().substring(0, 11);
                txtPhoneValue.setText(s);
            }
        });

        //Only numbers
        txtPhoneValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtPhoneValue.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        //Populate the table from database
        loadTableViewAds("", "");

        //Focus on first item
        tableViewAds.requestFocus();
        tableViewAds.getSelectionModel().select(0);
        tableViewAds.getFocusModel().focus(0);

        //Set detail section's values
        if(tableViewAds.getItems().size() != 0)
            setDetailsItemOnSelect();

        //Set txtAuthorPrefixNumber invisible
        txtAuthorPrefixNumber.setVisible(true);
    }

    @FXML
        //Get values for Detail Section from Table
    void setDetailsItemOnSelect() {
        Author author = tableViewAds.getSelectionModel().getSelectedItem();
        txtFirstNameValue.setText(author.getAuthorFirstName());
        txtLastNameValue.setText(author.getAuthorLastName());
        txtEmailValue.setText(author.getAuthorEmailAddress());
        txtPhoneValue.setText(author.getAuthorPhone().substring(3));
        cbAuthorPrefixNumber.setValue(author.getAuthorPhone().substring(0, 3));
    }

    @FXML
        //Save button
    void setBtnSaveOnClick() {
        //set interface
        tableViewAds.setDisable(false);
        this.disableSelectiveButtons(true);
        this.setDisableFields(true);
        cbAuthorPrefixNumber.setVisible(true);
        txtAuthorPrefixNumber.setVisible(false);

        //Get values from interface
        Author author = new Author();
        author.setAuthorID(tableViewAds.getSelectionModel().getSelectedItem().getAuthorID());
        author.setAuthorEmailAddress(txtEmailValue.getText());
        author.setAuthorFirstName(txtFirstNameValue.getText());
        author.setAuthorPhone(cbAuthorPrefixNumber.getValue() + txtPhoneValue.getText());
        author.setAuthorLastName(txtLastNameValue.getText());

        //Add/Edit values in database
        if (!isEdit) {
            author.setAuthorPhone(txtAuthorPrefixNumber.getText() + txtPhoneValue.getText());
            AuthorsDAO.setAuthor(author);
        }
        else{
            author.setAuthorPhone(cbAuthorPrefixNumber.getValue() + txtPhoneValue.getText());
            AuthorsDAO.updAuthor(author);
        }

        lblStatusValue.setText("Your item was added.");

        loadTableViewAds("", "");
    }

    @FXML
        //Cancel button
    void setBtnCancelOnClick() {
        tableViewAds.setDisable(false);
        this.disableSelectiveButtons(true);
        this.setDisableFields(true);

        cbAuthorPrefixNumber.setVisible(true);
        txtAuthorPrefixNumber.setVisible(false);
    }

    @FXML
        //Add button
    void setBtnAdOnAction() {
        cleanDetailsFields();
        tableViewAds.setDisable(true);
        this.disableSelectiveButtons(false);
        this.setDisableFields(false);
        cbAuthorPrefixNumber.setVisible(false);
        txtAuthorPrefixNumber.setVisible(true);

        isEdit = false;
    }

    @FXML
        //Edit button
    void setBtnEditOnClick() {
        tableViewAds.setDisable(true);
        this.disableSelectiveButtons(false);
        this.setDisableFields(false);
        isEdit = true;
    }

    @FXML
        //Delete button
    void setBtnDeleteOnClick() {
        this.setDisableFields(true);
        AuthorsDAO.delAuthor(tableViewAds.getSelectionModel().getSelectedItem());

        loadTableViewAds("", "");
    }

    @FXML
        //Search button
    void setSearchOnClick() {
        loadTableViewAds(txtFirstNameSearch.getText(), txtLastNameSearch.getText());
    }
    //endregion

    //region Private Methods
    private void cleanDetailsFields() {
        //Author
        txtEmailValue.setText("");
        txtFirstNameValue.setText("");
        txtLastNameValue.setText("");
        txtPhoneValue.setText("");
        txtAuthorPrefixNumber.setText("+");

        //Status
        lblStatusValue.setText("");
    }

    private void cleanSearchFields() {
        txtFirstNameSearch.setText("");
        txtLastNameSearch.setText("");
    }

    private void disableSelectiveButtons(Boolean disableSave) {
        this.btnAdd.setDisable(!disableSave);
        this.btnEdit.setDisable(!disableSave);
        this.btnDelete.setDisable(!disableSave);

        this.btnCancel.setDisable(disableSave);
        this.btnSave.setDisable(disableSave);
    }

    private void setDisableFields(Boolean disableFields) {
        this.txtPhoneValue.setDisable(disableFields);
        this.txtLastNameValue.setDisable(disableFields);
        this.txtEmailValue.setDisable(disableFields);
        this.txtFirstNameValue.setDisable(disableFields);
        this.cbAuthorPrefixNumber.setDisable(disableFields);
        this.txtAuthorPrefixNumber.setDisable(disableFields);
    }

    private void loadTableViewAds(String firstName, String lastName) {
        //Set what values from database we'll use in interface
        tableColFirstName.setCellValueFactory(new PropertyValueFactory<>("AuthorFirstName"));
        tableColLastName.setCellValueFactory(new PropertyValueFactory<>("AuthorLastName"));
        tableColPhone.setCellValueFactory(new PropertyValueFactory<>("AuthorPhone"));
        tableColEmail.setCellValueFactory(new PropertyValueFactory<>("AuthorEmailAddress"));

        //Get values from database
        ArrayList<Author> authors = AuthorsDAO.getAuthor(-1, firstName, lastName);

        //Populate TableView from database
        tableViewAds.getItems().setAll(authors);

        //Add values in PrefixNumber's ComboBox
        for (Author author : tableViewAds.getItems()) {
            Boolean checked = false;
            for (String prefix : cbAuthorPrefixNumber.getItems()) {
                if (author.getAuthorPhone().substring(0, 3).equals(prefix)) {
                    checked = true;
                }
            }
            if (!checked) {
                cbAuthorPrefixNumber.getItems().addAll(author.getAuthorPhone().substring(0, 3));
            }
        }
    }
    //endregion

    //region Menu Item Functionality

    @FXML
    void setMenuItemAdOnAction() {
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
    void setMenuItemAuthorOnClick() {
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
    void setMenuItemClientOnClick() {
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
    void setMenuItemReportsOnClick() {
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
    void setMenuItemAboutOnClick() {
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemBackupOnClick() {
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemExitOnClick() {
        System.out.println("Click!");
        Stage stage = (Stage) pnExecut.getScene().getWindow();
        stage.close();
    }
    //endregion
}