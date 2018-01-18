package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class Client {
    //Declare Employees Table Columns
    private SimpleIntegerProperty ClientID;
    private SimpleIntegerProperty CityID;
    private SimpleStringProperty ClientName;
    private SimpleStringProperty ClientType;
    private SimpleStringProperty ClientStreet;
    private SimpleStringProperty ClientBuildingNumber;
    private SimpleStringProperty ClientUniqueCode;
    private SimpleStringProperty ClientURL;
    private SimpleStringProperty ClientEmailAddress;
    private SimpleStringProperty ClientCity;
    private SimpleStringProperty ClientRegion;


    //Constructor
    public Client(){
        this.ClientID = new SimpleIntegerProperty();
        this.CityID = new SimpleIntegerProperty();
        this.ClientName = new SimpleStringProperty();
        this.ClientType = new SimpleStringProperty();
        this.ClientStreet = new SimpleStringProperty();
        this.ClientBuildingNumber = new SimpleStringProperty();
        this.ClientUniqueCode = new SimpleStringProperty();
        this.ClientURL = new SimpleStringProperty();
        this.ClientEmailAddress = new SimpleStringProperty();
        this.ClientCity = new SimpleStringProperty();
        this.ClientRegion = new SimpleStringProperty();

    }

    //region CityID
    public int getCityID() {
        return CityID.get();
    }

    public void setCityID(int cityID){
        this.CityID.set(cityID);
    }

    public IntegerProperty cityIDProperty(){
        return CityID;
    }
    //endregion

    //region ClientID
    public int getClientID() {
        return ClientID.get();
    }

    public void setClientID(int clientID){
        this.ClientID.set(clientID);
    }

    public IntegerProperty clientIDProperty(){
        return ClientID;
    }
    //endregion

    //region ClientName
    public String getClientName() {
        return ClientName.get();
    }

    public void setClientName(String clientName) {
        this.ClientName.set(clientName);
    }

    public StringProperty clientNameProperty() {
        return ClientName;
    }
    //endregion

    //region ClientType
    public String getClientType() {
        return ClientType.get();
    }

    public void setClientType(String clientType){
        this.ClientType.set(clientType);
    }

    public StringProperty clientTypeProperty(){
        return ClientType;
    }
    //endregion

    //region ClientStreet
    public String getClientStreet() {
        return ClientStreet.get();
    }

    public void setClientStreet(String clientStreet){
        this.ClientStreet.set(clientStreet);
    }

    public StringProperty clientStreetProperty(){
        return ClientStreet;
    }
    //endregion

    //region ClientBuildingNumber
    public String getClientBuildingNumber() {
        return ClientBuildingNumber.get();
    }

    public void setClientBuildingNumber(String buildingNumber){
        this.ClientBuildingNumber.set(buildingNumber);
    }

    public StringProperty clientBuildingNumberProperty(){
        return ClientBuildingNumber;
    }
    //endregion

    //region ClientUniqueCode
    public String getClientUniqueCode() {
        return ClientUniqueCode.get();
    }

    public void setClientUniqueCode(String clientUniqueCode){
        this.ClientUniqueCode.set(clientUniqueCode);
    }

    public StringProperty clientUniqueCodeProperty(){
        return ClientUniqueCode;
    }
    //endregion

    //region ClientURL
    public String getClientURL() {
        return ClientURL.get();
    }

    public void setClientURL(String clientURL){
        this.ClientURL.set(clientURL);
    }

    public StringProperty clientURLProperty(){
        return ClientURL;
    }
    //endregion

    //region ClientEmailAddress
    public String getClientEmailAddress() {
        return ClientEmailAddress.get();
    }

    public void setClientEmailAddress(String clientEmailAddress){
        this.ClientEmailAddress.set(clientEmailAddress);
    }

    public StringProperty clientEmailAddressProperty(){
        return ClientEmailAddress;
    }
    //endregion

    //region ClientCity
    public String getClientCity() {
        return ClientCity.get();
    }

    public void setClientCity(String clientCity) {
        this.ClientCity.set(clientCity);
    }

    public StringProperty clientCityProperty() {
        return ClientCity;
    }
    //endregion

    //region ClientRegion
    public String getClientRegion() {
        return ClientRegion.get();
    }

    public void setClientRegion(String clientRegion) {
        this.ClientRegion.set(clientRegion);
    }

    public StringProperty clientRegionProperty() {
        return ClientRegion;
    }
    //endregion
}
