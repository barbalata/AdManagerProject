package sample;

import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventDispatcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import sample.DataAccesObject.*;
import sample.Module.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class FrmAds {

    //region Declarations

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Boolean isAdd;
    private Boolean isEdit;
    private Boolean isDelete;
    private Boolean isAd;
    private Boolean isLocation;
    private Boolean isPrice;
    private Boolean isContent;

    //region Ads Titled Pane
    @FXML
    private ComboBox<Integer> comboBoxClient;

    @FXML
    private Label lblAdClientNameValue;

    @FXML
    private Label lblAdAuthorNameValue;

    @FXML
    private ComboBox<Integer> comboBoxAuthor;

    @FXML
    private DatePicker datePickerAdsStartDate;

    @FXML
    private DatePicker datePickerAdsStopDate;

    @FXML
    private ComboBox<Integer> comboBoxPrice;

    @FXML
    private ComboBox<Integer> comboBoxLocation;

    @FXML
    private ComboBox<Integer> comboBoxContent;

    //endregion

    @FXML
    private TextField txtSearchAdCode;

    @FXML
    private TextField txtPriceTotal;

    @FXML
    private Tab tabSearch;

    @FXML
    private TextField txtPriceUnityPrice;

    @FXML
    private TextField txtPriceNumberClicks;

    @FXML
    private MenuButton btnDelete;

    @FXML
    private Tab tabDetails;

    @FXML
    private TableView<Ad> tableViewAds;

    @FXML
    private MenuButton btnEdit;

    @FXML
    private TextField txtPriceNumberViews;

    @FXML
    private DatePicker dpSearchStartData;

    @FXML
    private MediaView mediaContentMedia;

    @FXML
    private TextField txtLocationX;

    @FXML
    private MenuItem btnDeleteLocation;

    @FXML
    private MenuItem btnAddAd;

    @FXML
    private MenuItem btnEditAd;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField txtSearchClientName;

    @FXML
    private TextField txtLocationY;

    @FXML
    private TextField txtPricePriceView;

    @FXML
    private Label lblStatusValue;

    @FXML
    private MenuItem btnEditClient;

    @FXML
    private MenuItem btnEditAll;

    @FXML
    private TextField txtContentTitle;

    @FXML
    private MenuItem btnDeletePrice;

    @FXML
    private TextField txtContentMediaPath;

    @FXML
    private Button btnCancel;

    @FXML
    private TextArea txtContentBody;

    @FXML
    private Button btnSave;

    @FXML
    private DatePicker dpSearchStopDate;

    @FXML
    private TextField txtLocationSize;

    @FXML
    private TextField txtSearchAuthorName;

    @FXML
    private MenuButton btnAdd;

    @FXML
    private MenuItem btnEditPrice;

    @FXML
    private Pane pnExecut;

    @FXML
    private TextField txtPricePriceClick;

    @FXML
    private MenuItem btnAddLocation;

    @FXML
    private MenuItem btnDeleteAll;

    @FXML
    private MenuItem btnAddContent;

    @FXML
    private MenuItem btnAddAll;

    @FXML
    private TableColumn<Ad, ?> tableColumnAdTotalPrice;

    @FXML
    private TableColumn<Ad, String> tableColumnAdCode;

    @FXML
    private TableColumn<Ad, String> tableColumnAdClientName;

    @FXML
    private TableColumn<Ad, String> tableColumnAdAuthorName;

    @FXML
    private TableColumn<Ad, java.util.Date> tableColumnAdStartDate;

    @FXML
    private TableColumn<Ad, java.util.Date> tableColumnAdStopDate;
    //endregion

    //region FXML Methods
    @FXML
    void initialize() {
        this.setDisableLocation(true);
        this.setDisableContent(true);
        this.setDisablePrice(true);
        this.setDisableAds(true);
        this.tableViewAds.setDisable(false);

        txtPriceTotal.setDisable(true);
        txtPriceNumberClicks.setDisable(true);
        txtPriceNumberViews.setDisable(true);
        this.setDisableSelectedButtons(true);
        cleanDetailsFields();
        cleanSearchFields();

        //Populate the table from database
        loadTableView(-1, "", "", new GregorianCalendar(1990, 1, 1), new GregorianCalendar(2040, 12, 31));

        //Focus on first item
        tableViewAds.requestFocus();
        tableViewAds.getSelectionModel().select(0);
        tableViewAds.getFocusModel().focus(0);

        comboBoxLocation.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue observableValue, Integer lastValue, Integer currentValue) {
                System.out.println(observableValue);
                System.out.println(lastValue);
                System.out.println(currentValue);
                loadLocationDetailsValue(tableViewAds.getSelectionModel().getSelectedItem(), LocationDAO.LocationSEL(currentValue).get(0));
            }
        });

        comboBoxContent.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue observableValue, Integer lastValue, Integer currentValue) {
                System.out.println(observableValue);
                System.out.println(lastValue);
                System.out.println(currentValue);
                loadContentDetailsValue(ContentDAO.ContentSEL(currentValue).get(0));
            }
        });

        comboBoxPrice.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue observableValue, Integer lastValue, Integer currentValue) {
                System.out.println(observableValue);
                System.out.println(lastValue);
                System.out.println(currentValue);
                loadPriceDetailsValue(tableViewAds.getSelectionModel().getSelectedItem(), PriceDAO.PriceSEL(currentValue).get(0));
            }
        });

        comboBoxAuthor.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue observableValue, Integer lastValue, Integer currentValue) {
                System.out.println(observableValue);
                System.out.println(lastValue);
                System.out.println(currentValue);
                Author author = AuthorsDAO.getAuthor(currentValue, "", "").get(0);
                lblAdAuthorNameValue.setText(author.getAuthorFirstName() + " " + author.getAuthorLastName());
            }
        });

        comboBoxClient.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue observableValue, Integer lastValue, Integer currentValue) {
                System.out.println(observableValue);
                System.out.println(lastValue);
                System.out.println(currentValue);
                Client client = ClientsDAO.ClientGET(currentValue, "", "", "", "", "").get(0);
                lblAdClientNameValue.setText(client.getClientName());
            }
        });

        //Set detail section's values
        if (tableViewAds.getItems().size() != 0)
            setDetailsItemOnSelect();
    }

    @FXML
    void setTabSearch(){
        dpSearchStartData.setValue(LocalDate.now());
        dpSearchStopDate.setValue(LocalDate.now());
    }
    @FXML
    void setBtnSearchOnClick(){
        GregorianCalendar gregorianCalendar = new GregorianCalendar(dpSearchStartData.getValue().getYear(), dpSearchStartData.getValue().getMonthValue(), dpSearchStartData.getValue().getDayOfMonth());
        GregorianCalendar gregorianCalendar1 = new GregorianCalendar(dpSearchStopDate.getValue().getYear(), dpSearchStopDate.getValue().getMonthValue(), dpSearchStopDate.getValue().getDayOfMonth());

        this.loadTableView(Integer.valueOf(txtSearchAdCode.getText()), txtSearchClientName.getText(), txtSearchAuthorName.getText(), gregorianCalendar,gregorianCalendar1);
    }

    //region Main Button -> Add/Edit/Delete
    @FXML
    void setBtnEditOnClick() {
        isEdit = true;
        isAdd = false;
        isDelete = false;
    }

    @FXML
    void setBtnAddOnClick() {
        isEdit = false;
        isAdd = true;
        isDelete = false;
    }

    @FXML
    void setBtnDeleteOnClick() {
        isEdit = false;
        isAdd = false;
        isDelete = true;
    }
    //endregion

    //region Sub-Buttons Add/Edit
    @FXML
    void setBtnAddEditLocationOnClick() {
        this.setDisableLocation(false);
        this.setDisableContent(true);
        this.setDisablePrice(true);
        this.setDisableSelectedButtons(false);
        this.setDisableAds(true);
        this.tableViewAds.setDisable(true);
        this.cleanDetailsFields();
        this.selectOptionAddEdit(false, false, true, false);

        if(isEdit){
            this.comboBoxLocation.setDisable(false);
        }
    }

    @FXML
    void setBtnAddEditAdOnClick() {
        this.setDisableLocation(true);
        this.setDisableContent(true);
        this.setDisablePrice(true);
        this.setDisableSelectedButtons(false);
        this.setDisableAds(false);
        this.cleanDetailsFields();
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(true, false, false, false);
    }

    @FXML
    void setBtnAddEditContentOnClick() {
        this.setDisableLocation(true);
        this.setDisableContent(false);
        this.setDisablePrice(true);
        this.setDisableSelectedButtons(false);
        this.setDisableAds(true);
        this.cleanDetailsFields();
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(false, true, false, false);

        if(isEdit){
            this.comboBoxContent.setDisable(false);
        }
    }

    @FXML
    void setBtnAddEditPriceOnClick() {
        this.setDisableLocation(true);
        this.setDisableContent(true);
        this.setDisablePrice(false);
        this.cleanDetailsFields();
        this.setDisableSelectedButtons(false);
        this.setDisableAds(true);
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(false, false, false, true);

        if(isEdit){
            this.comboBoxPrice.setDisable(false);
        }
    }
    //endregion

    //region Sub-buttons Delete
    @FXML
    void setBtnDeleteLocationOnClick() {
        this.btnAdd.setDisable(true);
        this.btnEdit.setDisable(true);
        this.setDisableSelectedButtons(false);
        this.comboBoxLocation.setDisable(false);
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(false, false, true, false);
    }

    @FXML
    void setBtnDeleteContentOnClick() {
        this.btnAdd.setDisable(true);
        this.btnEdit.setDisable(true);
        this.setDisableSelectedButtons(false);
        this.comboBoxContent.setDisable(false);
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(false, false, true, false);
    }

    @FXML
    void setBtnDeletePriceOnClick() {
        this.btnAdd.setDisable(true);
        this.btnEdit.setDisable(true);
        this.setDisableSelectedButtons(false);
        this.comboBoxContent.setDisable(false);
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(false, false, false, true);
    }

    @FXML
    void setBtnDeleteAdOnClick() {
        this.btnAdd.setDisable(true);
        this.btnEdit.setDisable(true);
        this.setDisableSelectedButtons(false);
        this.comboBoxContent.setDisable(false);
        this.tableViewAds.setDisable(true);
        this.selectOptionAddEdit(true, false, false, false);
    }
    //endregion

    //region Button Cancel
    @FXML
    void setBtnCancelOnClick() {
        this.setDisableLocation(true);
        this.setDisableContent(true);
        this.setDisablePrice(true);
        this.setDisableAds(true);
        this.setDisableSelectedButtons(true);
        this.tableViewAds.setDisable(false);

        this.lblStatusValue.setText("The operation was canceled.");
    }
    //endregion

    //region Button Save
    @FXML
    void setBtnSaveOnClick() {
        this.setDisableLocation(true);
        this.setDisableContent(true);
        this.setDisablePrice(true);
        this.setDisableAds(true);
        this.setDisableSelectedButtons(true);
        this.tableViewAds.setDisable(false);

        //Get data from graphic interface
        if (isDelete) {
            if (isAd) {
                AdsDAO.AdDEL(tableViewAds.getSelectionModel().getSelectedItem());
            } else {
                if (isPrice) {
                    PriceDAO.PriceDEL(PriceDAO.PriceSEL(comboBoxPrice.getSelectionModel().getSelectedItem()).get(0));
                } else {
                    if (isContent) {
                        ContentDAO.ContentDEL(ContentDAO.ContentSEL(comboBoxContent.getSelectionModel().getSelectedItem()).get(0));
                    } else {
                        if (isLocation) {
                            LocationDAO.LocationDEL(LocationDAO.LocationSEL(comboBoxLocation.getSelectionModel().getSelectedIndex()).get(0));
                        }
                    }
                }
            }
        } else {
            if (isAdd || isEdit) {
                if (isAd) {
                    Ad ad = new Ad();
                    ad.setClientID(comboBoxClient.getSelectionModel().getSelectedItem());
                    ad.setAuthorID(comboBoxAuthor.getSelectionModel().getSelectedItem());
                    ad.setPriceID(comboBoxPrice.getSelectionModel().getSelectedItem());
                    ad.setStopDate(Date.from(datePickerAdsStopDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ad.setStartDate(Date.from(datePickerAdsStartDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    ad.setLocationID(comboBoxLocation.getSelectionModel().getSelectedItem());
                    ad.setContentID(comboBoxContent.getSelectionModel().getSelectedItem());
                    ad.setAdViewNumber(Integer.valueOf(txtPriceNumberViews.getText()));
                    ad.setAdClickNumber(Integer.valueOf(txtPriceNumberClicks.getText()));
                    ad.setAdSize(txtLocationSize.getText());

                    if (isAdd) {
                        AdsDAO.AdINS(ad);
                    } else if (isEdit) {
                        ad.setAdID(tableViewAds.getSelectionModel().getSelectedItem().getAdID());

                        AdsDAO.AdUPD(ad);
                    }
                } else {
                    if (isPrice) {
                        Price price = new Price();
                        price.setPriceUnite(txtPriceUnityPrice.getText());
                        price.setPriceCostView(new BigDecimal(txtPricePriceView.getText().replaceAll(",", "")));
                        price.setPriceCostClick(new BigDecimal(txtPricePriceClick.getText().replaceAll(",", "")));

                        if (isAdd) {
                            PriceDAO.PriceINS(price);
                        } else if (isEdit) {
                            price.setPriceID(comboBoxPrice.getSelectionModel().getSelectedItem());

                            PriceDAO.PriceUPD(price);
                        }
                    } else {
                        if (isContent) {
                            Content content = new Content();
                            File file = new File(txtContentMediaPath.getText());
                            if (file.exists()) {
                                content.setContentMedia(txtContentMediaPath.getText());
                                content.setContentTitle(txtContentTitle.getText());
                                content.setContentBody(txtContentBody.getText());

                                if (isAdd) {
                                    ContentDAO.ContentINS(content);
                                } else if (isEdit) {
                                    content.setContentID(comboBoxContent.getSelectionModel().getSelectedItem());

                                    ContentDAO.ContentUPD(content);
                                }
                            } else {
                                lblStatusValue.setText("Insert a valid path");
                            }
                        } else {
                            if (isLocation) {
                                Location location = new Location();
                                location.setLocationCoordonateX(new BigDecimal(txtLocationX.getText()));
                                location.setLocationCoordonateY(new BigDecimal(txtLocationY.getText()));

                                if (isAdd) {
                                    LocationDAO.LocationINS(location);
                                } else if (isEdit) {
                                    location.setLocationID(tableViewAds.getSelectionModel().getSelectedItem().getLocationID());

                                    LocationDAO.LocationUPD(location);
                                }
                            }
                        }
                    }
                }
            }
        }
        loadTableView(-1, "", "", new GregorianCalendar(1990, 1, 1), new GregorianCalendar(2040, 12, 31));
    }
//endregion

    //region Load ComboBoxes
    @FXML
    void loadComboBoxLocationValues() {
        if (tableViewAds.getItems().size() != 0) {
            ArrayList<Location> locations = LocationDAO.LocationSEL(-1);
            comboBoxPrice.getItems().clear();
            if (!locations.isEmpty())
                for (Location location : locations) {
                    comboBoxLocation.getItems().addAll(location.getLocationID());
                }

        }
    }

    @FXML
    void loadComboBoxPriceValues() {
        if (tableViewAds.getItems().size() != 0) {
            ArrayList<Price> currentPriceItem = PriceDAO.PriceSEL(-1);
            for (Price price : currentPriceItem) {
                comboBoxPrice.getItems().addAll(price.getPriceID());
            }
        }
    }

    @FXML
    void loadComboBoxContentValues() {
        if (tableViewAds.getItems().size() != 0) {
            ArrayList<Content> currentContentItem = ContentDAO.ContentSEL(-1);
            for (Content content : currentContentItem) {
                comboBoxContent.getItems().addAll(content.getContentID());
            }
        }
    }

    @FXML
    void loadComboBoxClientValues() {
        if (tableViewAds.getItems().size() != 0) {
            ArrayList<Client> clients = ClientsDAO.ClientGET(-1, "", "", "", "", "");
            if (!clients.isEmpty())
                for (Client client : clients) {
                    comboBoxClient.getItems().addAll(client.getClientID());

                }

        }
    }

    @FXML
    void loadComboBoxAuthorValues() {
        if (tableViewAds.getItems().size() != 0) {
            ArrayList<Author> authors = AuthorsDAO.getAuthor(-1, "", "");
            if (!authors.isEmpty())
                for (Author author : authors) {
                    comboBoxAuthor.getItems().addAll(author.getAuthorID());
                    comboBoxAuthor.setAccessibleText(author.getAuthorFirstName() + " " + author.getAuthorLastName());
                }

        }
    }

//endregion

    //region Set values in details section
    @FXML
    private void setDetailsItemOnSelect() {
        Ad ad = tableViewAds.getSelectionModel().getSelectedItem();
        Location location = LocationDAO.LocationSEL(ad.getLocationID()).get(0);
        Content content = ContentDAO.ContentSEL(ad.getContentD()).get(0);
        Price price = PriceDAO.PriceSEL(ad.getPriceID()).get(0);

        ad.setAdTotalPrice(Float.valueOf((
                price.getPriceCostClick()
                        .multiply(new BigDecimal(ad.getAdClickNumber()))
                        .add(
                                price.getPriceCostView()
                                        .multiply(new BigDecimal(ad.getAdViewNumber())))
        ).toString()));

        loadLocationDetailsValue(ad, location);
        loadContentDetailsValue(content);
        loadPriceDetailsValue(ad, price);

        //Ad Titled Pane
        this.datePickerAdsStopDate.setValue(LocalDate.parse(dateFormat.format(ad.getStopDate().getTime()), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.datePickerAdsStartDate.setValue(LocalDate.parse(dateFormat.format(ad.getStartDate().getTime()), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        this.comboBoxPrice.setValue(ad.getPriceID());
        this.comboBoxContent.setValue(ad.getContentD());
        this.comboBoxLocation.setValue(ad.getLocationID());
        this.comboBoxClient.setValue(ad.getClientID());
        this.comboBoxAuthor.setValue(ad.getAuthorID());
    }
//endregion
//endregion

    //region Private Methods
    private void loadLocationDetailsValue(Ad ad, Location location) {
        this.txtLocationSize.setText(ad.getAdSize());
        this.txtLocationX.setText(String.valueOf(location.getLocationCoordonateX()));
        this.txtLocationY.setText(String.valueOf(location.getLocationCoordonateY()));
    }

    private void loadPriceDetailsValue(Ad ad, Price price) {
        this.txtPriceUnityPrice.setText(price.getPriceUnite());
        this.txtPricePriceClick.setText(String.valueOf(price.getPriceCostClick()));
        this.txtPricePriceView.setText(String.valueOf(price.getPriceCostView()));
        this.txtPriceTotal.setText(String.valueOf(ad.getAdTotalPrice()));
        this.txtPriceNumberViews.setText(String.valueOf(ad.getAdViewNumber()));
        this.txtPriceNumberClicks.setText(String.valueOf(ad.getAdClickNumber()));
    }

    private void loadContentDetailsValue(Content content) {
        this.txtContentTitle.setText(content.getContentTitle());
        this.txtContentBody.setText(content.getContentBody());
        this.txtContentMediaPath.setText(content.getContentMedia());
        // this.mediaContentMedia.setMediaPlayer(new MediaPlayer(new Media(content.getContentMedia())));

    }

    private void cleanDetailsFields() {
        //Price
        txtPriceNumberViews.setText("");
        txtPriceNumberClicks.setText("");
        txtPriceTotal.setText("");
        txtPricePriceClick.setText("");
        txtPricePriceView.setText("");
        txtPriceUnityPrice.setText("");

        //Content
        txtContentBody.setText("");
        txtContentMediaPath.setText("");
        txtContentTitle.setText("");

        //Location
        txtLocationSize.setText("");
        txtLocationX.setText("");
        txtLocationY.setText("");

        //Ad
        this.comboBoxAuthor.setValue(-1);
        this.comboBoxClient.setValue(-1);
        this.comboBoxLocation.setValue(-1);
        this.comboBoxContent.setValue(-1);
        this.comboBoxPrice.setValue(-1);
        this.lblAdAuthorNameValue.setText("");
        this.lblAdClientNameValue.setText("");

        //Status
        lblStatusValue.setText("");
    }

    private void cleanSearchFields() {
        txtSearchAdCode.setText("");
        txtSearchAuthorName.setText("");
        txtSearchClientName.setText("");
    }

    private void setDisableLocation(Boolean disableLocation) {
        this.txtLocationX.setDisable(disableLocation);
        this.txtLocationY.setDisable(disableLocation);
    }

    private void setDisableContent(Boolean disableContent) {
        this.txtContentTitle.setDisable(disableContent);
        this.txtContentMediaPath.setDisable(disableContent);
        this.txtContentBody.setDisable(disableContent);
    }

    private void setDisablePrice(Boolean disablePrice) {
        this.txtPriceUnityPrice.setDisable(disablePrice);
        this.txtPricePriceView.setDisable(disablePrice);
        this.txtPricePriceClick.setDisable(disablePrice);
    }

    private void setDisableAds(Boolean disableAds) {
        //Ad
        this.datePickerAdsStartDate.setDisable(disableAds);
        this.datePickerAdsStopDate.setDisable(disableAds);

        //Location
        this.txtLocationSize.setDisable(disableAds);

        //Price
        this.txtPriceNumberClicks.setDisable(disableAds);
        this.txtPriceNumberViews.setDisable(disableAds);

        //ComboBox
        this.comboBoxPrice.setDisable(disableAds);
        this.comboBoxContent.setDisable(disableAds);
        this.comboBoxLocation.setDisable(disableAds);
        this.comboBoxAuthor.setDisable(disableAds);
        this.comboBoxClient.setDisable(disableAds);
    }

    private void setDisableSelectedButtons(Boolean disableSaveCancel) {
        this.btnSave.setDisable(disableSaveCancel);
        this.btnCancel.setDisable(disableSaveCancel);

        this.btnAdd.setDisable(!disableSaveCancel);
        this.btnEdit.setDisable(!disableSaveCancel);
        this.btnDelete.setDisable(!disableSaveCancel);
    }

    private void loadTableView(Integer adCode, String clientName, String authorName, GregorianCalendar startDate, GregorianCalendar stopDate) {
        tableColumnAdCode.setCellValueFactory(new PropertyValueFactory<>("AdID"));
        tableColumnAdClientName.setCellValueFactory(new PropertyValueFactory<>("AdClientName"));
        tableColumnAdAuthorName.setCellValueFactory(new PropertyValueFactory<>("AdAuthorName"));
        tableColumnAdStartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        tableColumnAdStopDate.setCellValueFactory(new PropertyValueFactory<>("StopDate"));
        tableColumnAdTotalPrice.setCellValueFactory(new PropertyValueFactory<>("AdPriceTotal"));

        //Get values from database
        ArrayList<Ad> ads = AdsDAO.AdGET(adCode, clientName, authorName, new java.sql.Date(startDate.getTimeInMillis()), new java.sql.Date(stopDate.getTimeInMillis()));

        //Populate TableView from database
        tableViewAds.getItems().setAll(ads);
    }

    private void selectOptionAddEdit(Boolean onAd, Boolean onContent, Boolean onLocation, Boolean onPrice) {
        this.isLocation = onLocation;
        this.isContent = onContent;
        this.isPrice = onPrice;
        this.isAd = onAd;
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
