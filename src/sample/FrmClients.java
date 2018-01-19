package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.DataAccesObject.CityDAO;
import sample.DataAccesObject.ClientsDAO;
import sample.DataAccesObject.RegionDAO;
import sample.Module.City;
import sample.Module.Client;
import sample.Module.Region;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class FrmClients {
    //region Declarations
    private Boolean isEdit = false;

    private Boolean isCity = false;

    private Boolean isRegion = false;

    private Boolean isAll = false;

    private Boolean isClient = false;

    @FXML
    private TextField txtClientRegionSearch;

    @FXML
    private TextField txtClientUrlValue;

    @FXML
    private TextField txtClientCitySearch;

    @FXML
    private TextField txtClientNameSearch;

    @FXML
    private Button btnEdit;

    @FXML
    private TextField txtClientCityValue;

    @FXML
    private TextField txtClientUniqueCodeSearch;

    @FXML
    private MenuButton btnDelete;

    @FXML
    private TextField txtClientStreetValue;

    @FXML
    private TableView<Client> tableViewAds;

    @FXML
    private TextField txtClientEmailValue;

    @FXML
    private TableColumn<Client, String> tableColClientRegion;

    @FXML
    private TableColumn<Client, String> tableColClientStreet;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<String> cbClientRegionValue;

    @FXML
    private ComboBox<String> cbClientCityValue;

    @FXML
    private TableColumn<Client, String> tableColClientCity;

    @FXML
    private Label lblStatusValue;

    @FXML
    private TextField txtClientRegionValue;

    @FXML
    private TextField txtClientTypeSearch;

    @FXML
    private TableColumn<Client, String> tableColClientType;

    @FXML
    private TextField txtClientNameValue;

    @FXML
    private TextField txtClientTypeValue;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtClientBuldingValue;

    @FXML
    private Button btnSave;

    @FXML
    private TextField txtClientUniqueCodeValue;

    @FXML
    private MenuButton btnAdd;

    @FXML
    private Pane pnExecut;

    @FXML
    private TableColumn<Client, String> tableColClientName;

    @FXML
    private TableColumn<Client, String> tableColClientBuilding;

    //endregion

    //region FXML Methods
    @FXML
    void initialize() {
        this.cleanDetailsFields();
        this.cleanSearchFields();
        this.setTabDetails();
        this.setComboBoxVisible(false);

        loadTableView("", "", "", "", "");

        //Focus on first item
        tableViewAds.requestFocus();
        tableViewAds.getSelectionModel().select(0);
        tableViewAds.getFocusModel().focus(0);

        //Set detail section's values
        if (tableViewAds.getItems().size() != 0)
            setDetailsItemOnSelect();
        else
            btnEdit.setDisable(true);
    }

    @FXML
    void setTabDetails() {
        this.disableDetailsFields(false);
        this.disableSelectiveButtons(true);
        this.disableDetailsFields(true);
    }

    @FXML
    void setBtnSearchOnClick(){
        loadTableView(txtClientNameSearch.getText(), txtClientTypeSearch.getText(), txtClientUniqueCodeSearch.getText(), txtClientCitySearch.getText(), txtClientRegionSearch.getText());
    }

    @FXML
    void setTabSearch() {
        this.disableDetailsFields(true);
        this.disableSelectiveButtons(false);
        this.disableSearchFields();
        this.btnSearch.setDisable(false);

        this.loadTableView(txtClientNameSearch.getText(), txtClientTypeSearch.getText(), txtClientUniqueCodeSearch.getText(), txtClientCitySearch.getText(), txtClientRegionSearch.getText());
    }

    @FXML
    void setBtnSaveOnClick() {
        this.disableDetailsFields(true);
        this.disableSelectiveButtons(true);
        this.setComboBoxVisible(false);

        //Get values from interface
        Client client = new Client();
        City city = new City();
        Region region = new Region();

        client.setClientName(txtClientNameValue.getText());
        client.setClientUniqueCode(txtClientUniqueCodeValue.getText());
        client.setClientType(txtClientTypeValue.getText());
        client.setClientEmailAddress(txtClientEmailValue.getText());
        client.setClientURL(txtClientUrlValue.getText());

        client.setClientBuildingNumber(txtClientBuldingValue.getText());
        client.setClientStreet(txtClientStreetValue.getText());

        //Add/Edit values in database
        if (isEdit) {
            region.setRegionName(cbClientRegionValue.getValue());
            region.setRegionID(RegionDAO.RegionGET(cbClientRegionValue.getValue(), -1).get(0).getRegionID());

            city.setCityID(CityDAO.CityGET(cbClientCityValue.getValue(), -1, -1).get(0).getCityID());
            city.setCityName(cbClientCityValue.getValue());
            city.setRegionID(region.getRegionID());

            client.setClientRegion(cbClientRegionValue.getValue());
            client.setClientID(tableViewAds.getSelectionModel().getSelectedItem().getClientID());
            client.setCityID(city.getCityID());
        } else {
            if (isCity) {
                city.setCityID(-1);
                city.setCityName(txtClientCityValue.getText());
                city.setRegionID(RegionDAO.RegionGET(cbClientRegionValue.getValue(), -1).get(0).getRegionID());
            } else if (isClient) {
                City city1 = CityDAO.CityGET(cbClientCityValue.getValue(), -1, -1).get(0);
                city.setCityID(city1.getCityID());
                city.setCityName(cbClientCityValue.getValue());
                city.setRegionID(city1.getRegionID());
            }

            if (isRegion) {
                region.setRegionName(txtClientRegionValue.getText());
            } else if (isClient) {
                region.setRegionName(cbClientRegionValue.getValue());
                region.setRegionID(RegionDAO.RegionGET(cbClientRegionValue.getValue(), -1).get(0).getRegionID());
            }

            if (isAll) {
                city.setCityID(-1);
                city.setCityName(txtClientCityValue.getText());
                region.setRegionID(-1);
                region.setRegionName(txtClientRegionValue.getText());
            }

            if (isClient || isAll) {
                client.setClientRegion(cbClientRegionValue.getValue());
                client.setClientID(-1);
                client.setCityID(city.getCityID());
            }
        }

        if (isEdit) {
            CityDAO.CityUPD(city);
            ClientsDAO.ClientUPD(client);
            RegionDAO.RegionUPD(region);
        } else {
            if (isRegion || isAll) {
                RegionDAO.setRegion(region);
            }

            if (isCity || isAll) {
                city.setRegionID(RegionDAO.RegionGET(!isAll ? cbClientRegionValue.getValue() : txtClientRegionValue.getText(), -1).get(0).getRegionID());
                CityDAO.CityINS(city);
            }
            if (isAll || isClient) {
                client.setCityID(city.getCityID());
                ClientsDAO.ClientINS(client);
            }
        }

        lblStatusValue.setText("Your item was added.");

        loadTableView("", "", "", "", "");
    }

    @FXML
    void setBtnCancelOnClick() {
        this.disableDetailsFields(true);
        this.disableSelectiveButtons(true);
        this.setComboBoxVisible(false);
        this.loadTableView("", "", "", "", "");
    }

    @FXML
    void setBtnAddCityOnClick() {
        this.disableSelectiveButtons(false);
        this.setComboBoxVisible(false);
        this.txtClientCityValue.setDisable(false);
        this.txtClientRegionValue.setVisible(false);
        this.cbClientRegionValue.setVisible(true);
        this.cbClientRegionValue.setDisable(false);
        this.setMode(false, false, true, false, false);
        this.cleanDetailsFields();
    }

    @FXML
    void setBtnAddRegionOnClick() {
        this.disableSelectiveButtons(false);
        this.setComboBoxVisible(false);
        this.txtClientRegionValue.setDisable(false);
        this.setMode(false, true, false, false, false);
        this.cleanDetailsFields();
    }

    @FXML
    void setBtnAddClientsOnClick() {
        this.disableSelectiveButtons(false);
        this.disableDetailsFields(false);
        this.setComboBoxVisible(true);
        this.setMode(false, false, false, true, false);
        this.cleanDetailsFields();
    }

    @FXML
    void setBtnAddAllOnClick() {
        this.disableDetailsFields(false);
        this.disableSelectiveButtons(false);
        this.setComboBoxVisible(false);
        this.setMode(false, false, false, false, true);
        this.cleanDetailsFields();
    }

    @FXML
    void setBtnEditAllOnClick() {
        this.disableSelectiveButtons(false);
        this.disableDetailsFields(false);
        this.setComboBoxVisible(true);
        this.cbClientCityValue.setVisible(true);
        this.txtClientRegionValue.setVisible(false);
        this.setMode(true, false, false, false, true);
    }

    @FXML
    void setBtnDeleteCityOnClick() {
        ArrayList<City> cities = CityDAO.CityGET("", tableViewAds.getSelectionModel().getSelectedItem().getCityID(), -1);
        CityDAO.CityDEL(cities.get(0));

        this.loadTableView("", "", "", "", "");
    }

    @FXML
    void setBtnDeleteClientsOnClick() {
        this.disableDetailsFields(true);
        ClientsDAO.ClientDEL(tableViewAds.getSelectionModel().getSelectedItem());

        this.loadTableView("", "", "", "", "");
    }

    @FXML
    void setBtnDeleteRegionOnClick() {
        RegionDAO.RegionDEL(RegionDAO.RegionGET(cbClientRegionValue.getValue(),-1).get(0));

        this.loadTableView("", "", "", "", "");
    }

    @FXML
    void setCBClientCityValueOnClick() {
        //Add values in City's ComboBox
        ArrayList<City> cities = CityDAO.CityGET("", -1, RegionDAO.RegionGET(cbClientRegionValue.getSelectionModel().getSelectedItem(), -1).get(0).getRegionID());
        cbClientCityValue.getItems().clear();
        if (!cities.isEmpty())
            for (City city : cities) {
                Boolean checked = false;
                for (String existing : cbClientCityValue.getItems()) {
                    if (city.getCityName().equals(existing)) {
                        checked = true;
                    }
                }
                if (!checked) {
                    cbClientCityValue.getItems().addAll(city.getCityName());
                }
            }
    }

    @FXML
    void setCBClientRegionValueOnClick() {
        //Add values in Region's ComboBox
        ArrayList<Region> regions = RegionDAO.RegionGET("", -1);
        if (!regions.isEmpty())
            for (Region region : regions) {
                Boolean checked = false;
                for (String existing : cbClientRegionValue.getItems()) {
                    if (region.getRegionName().equals(existing)) {
                        checked = true;
                    }
                }
                if (!checked) {
                    cbClientRegionValue.getItems().addAll(region.getRegionName());
                }
            }
    }
    //endregion

    //region Private Methods
    private void cleanDetailsFields() {
        //Address
        txtClientBuldingValue.setText("");
        txtClientCityValue.setText("");
        txtClientRegionValue.setText("");
        txtClientStreetValue.setText("");
        txtClientUrlValue.setText("");
        cbClientCityValue.setValue("");
        cbClientRegionValue.setValue("");

        //Client Info
        txtClientNameValue.setText("");
        txtClientEmailValue.setText("");
        txtClientUniqueCodeValue.setText("");
        txtClientTypeValue.setText("");

        //Status
        lblStatusValue.setText("");
    }

    private void cleanSearchFields() {
        //Location Search
        txtClientCitySearch.setText("");
        txtClientRegionSearch.setText("");

        //Client Info Search
        txtClientNameSearch.setText("");
        txtClientTypeSearch.setText("");
    }

    private void disableSearchFields() {
        //Location Search
        txtClientCitySearch.setDisable(false);
        txtClientRegionSearch.setDisable(false);

        //Client Info Search
        txtClientNameSearch.setDisable(false);
        txtClientTypeSearch.setDisable(false);
    }

    private void disableDetailsFields(Boolean disable) {
        //Address
        txtClientBuldingValue.setDisable(disable);
        txtClientCityValue.setDisable(disable);
        txtClientRegionValue.setDisable(disable);
        txtClientStreetValue.setDisable(disable);

        //Client Info
        txtClientNameValue.setDisable(disable);
        txtClientEmailValue.setDisable(disable);
        txtClientUniqueCodeValue.setDisable(disable);
        txtClientTypeValue.setDisable(disable);
        txtClientUrlValue.setDisable(disable);
    }

//    private void disableSelectiveDetailsFields(Boolean disableAddress) {
//        //Address
//        txtClientBuldingValue.setDisable(disableAddress);
//        txtClientCityValue.setDisable(disableAddress);
//        txtClientRegionValue.setDisable(disableAddress);
//        txtClientStreetValue.setDisable(disableAddress);
//
//        //Client Info
//        txtClientNameValue.setDisable(!disableAddress);
//        txtClientEmailValue.setDisable(!disableAddress);
//        txtClientUniqueCodeValue.setDisable(!disableAddress);
//        txtClientTypeValue.setDisable(!disableAddress);
//        txtClientUrlValue.setDisable(!disableAddress);
//    }

    private void disableSelectiveButtons(Boolean disableSave) {
        this.btnAdd.setDisable(!disableSave);
        this.btnEdit.setDisable(!disableSave);
        this.btnDelete.setDisable(!disableSave);

        this.btnCancel.setDisable(disableSave);
        this.btnSave.setDisable(disableSave);
    }

    private void loadTableView(String clientName, String clientType, String clientUniqueCode, String cityName, String regionName) {
        //Set what values from database we'll use in interface
        tableColClientName.setCellValueFactory(new PropertyValueFactory<>("ClientName"));
        tableColClientType.setCellValueFactory(new PropertyValueFactory<>("ClientType"));
        tableColClientCity.setCellValueFactory(new PropertyValueFactory<>("ClientCity"));
        tableColClientBuilding.setCellValueFactory(new PropertyValueFactory<>("ClientBuildingNumber"));
        tableColClientRegion.setCellValueFactory(new PropertyValueFactory<>("ClientRegion"));
        tableColClientStreet.setCellValueFactory(new PropertyValueFactory<>("ClientStreet"));

        //Get values from database
        ArrayList<Client> clients = ClientsDAO.ClientGET(-1, clientName, clientType, clientUniqueCode, cityName, regionName);

        //Populate TableView from database
        tableViewAds.getItems().setAll(clients);

        if (tableViewAds.getItems().size() == 0) {
            this.cleanDetailsFields();
            this.btnEdit.setDisable(true);
        } else {
            this.btnEdit.setDisable(false);
        }
    }
    @FXML
    private void setDetailsItemOnSelect() {
        Client client = tableViewAds.getSelectionModel().getSelectedItem();
        txtClientNameValue.setText(String.valueOf(client.getClientName()));
        txtClientTypeValue.setText(client.getClientType());
        txtClientUniqueCodeValue.setText(client.getClientUniqueCode());
        txtClientEmailValue.setText(client.getClientEmailAddress());
        txtClientUrlValue.setText(client.getClientURL());

        txtClientCityValue.setText(client.getClientCity());
        txtClientStreetValue.setText(client.getClientStreet());
        txtClientBuldingValue.setText(client.getClientBuildingNumber());
        txtClientRegionValue.setText(client.getClientRegion());
        cbClientRegionValue.setValue(client.getClientRegion());
        cbClientCityValue.setValue(client.getClientCity());
    }

    private void setMode(Boolean isEdit, Boolean isRegion, Boolean isCity, Boolean isClient, Boolean isAll) {
        this.isCity = isCity;
        this.isRegion = isRegion;
        this.isClient = isClient;
        this.isAll = isAll;
        this.isEdit = isEdit;
    }

    private void setComboBoxVisible(boolean comboBoxValue) {
        this.cbClientRegionValue.setVisible(comboBoxValue);
        this.txtClientRegionValue.setVisible(!comboBoxValue);
        this.cbClientCityValue.setVisible(comboBoxValue);
        this.txtClientCityValue.setVisible(!comboBoxValue);
    }
    //endregion

    //region Menu Item Functionality
    @FXML
    void setMenuItemEditOnAction() {
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemAddOnAction() {
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemDeleteOnAction() {
        System.out.println("Click!");
    }

    @FXML
    void setMenuItemSearchOnAction() {
        System.out.println("Click!");
    }

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
