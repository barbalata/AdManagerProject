package sample.Module;

import javafx.beans.property.*;
import javafx.scene.control.DatePicker;

import java.sql.Date;
import java.sql.Time;
import java.util.GregorianCalendar;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class Ad {
    //Declare Employees Table Columns
    private SimpleIntegerProperty AdID;
    private SimpleIntegerProperty LocationID;
    private SimpleIntegerProperty ClientID;
    private SimpleIntegerProperty ContentID;
    private SimpleIntegerProperty AuthorID;
    private SimpleIntegerProperty PriceID;
    private SimpleObjectProperty<java.util.Date> StartDate;
    private SimpleObjectProperty<java.util.Date> StopDate;
    private SimpleStringProperty AdSize;
    private SimpleIntegerProperty AdClickNumber;
    private SimpleIntegerProperty AdViewNumber;
    private SimpleStringProperty AdClientName;
    private SimpleStringProperty AdAuthorName;
    private SimpleFloatProperty AdTotalPrice;

    public Ad() {
        this.AdID = new SimpleIntegerProperty();
        this.LocationID = new SimpleIntegerProperty();
        this.ClientID = new SimpleIntegerProperty();
        this.ContentID = new SimpleIntegerProperty();
        this.AuthorID = new SimpleIntegerProperty();
        this.PriceID = new SimpleIntegerProperty();
        this.StartDate = new SimpleObjectProperty<>();
        this.StopDate = new SimpleObjectProperty<>();
        this.AdSize = new SimpleStringProperty();
        this.AdClickNumber = new SimpleIntegerProperty();
        this.AdViewNumber = new SimpleIntegerProperty();
        this.AdClientName = new SimpleStringProperty();
        this.AdAuthorName = new SimpleStringProperty();
        this.AdTotalPrice = new SimpleFloatProperty();
    }

    //region AdID
    public int getAdID() {
        return AdID.get();
    }

    public void setAdID(int adID){
        this.AdID.set(adID);
    }

    public IntegerProperty adIdProperty(){
        return AdID;
    }
    //endregion

    //region LocationID
    public int getLocationID() {
        return LocationID.get();
    }

    public void setLocationID(int locationID){
        this.LocationID.set(locationID);
    }

    public IntegerProperty locationIDProperty(){
        return LocationID;
    }
    //endregion

    //region ClientID
    public int getClientID() {
        return AdID.get();
    }

    public void setClientID(int clientID){
        this.ClientID.set(clientID);
    }

    public IntegerProperty clientIdProperty(){
        return ClientID;
    }
    //endregion

    //region ContentID
    public int getContentD() {
        return ContentID.get();
    }

    public void setContentID(int contentID){
        this.ContentID.set(contentID);
    }

    public IntegerProperty contentIdProperty(){
        return ContentID;
    }
    //endregion

    //region AuthorID
    public int getAuthorID() {
        return AuthorID.get();
    }

    public void setAuthorID(int authorID){
        this.AuthorID.set(authorID);
    }

    public IntegerProperty authorIdProperty(){
        return AuthorID;
    }
    //endregion

    //region PriceID
    public int getPriceID() {
        return PriceID.get();
    }

    public void setPriceID(int priceID){
        this.PriceID.set(priceID);
    }

    public IntegerProperty priceIdProperty(){
        return PriceID;
    }
    //endregion

    //region StartDate
    public java.util.Date getStartDate() {
        return StartDate.get();
    }

    public void setStartDate(java.util.Date startDate){
        this.StartDate.set(startDate);
    }

    public SimpleObjectProperty<java.util.Date> startDateProperty(){
        return StartDate;
    }
    //endregion

    //region StartDate
    public java.util.Date getStopDate() {
        return StopDate.getValue();
    }

    public void setStopDate(java.util.Date stopDate){
        this.StopDate.set(stopDate);
    }

    public SimpleObjectProperty<java.util.Date> stopDateProperty(){
        return StopDate;
    }
    //endregion

    //region AdSize
    public String getAdSize() {
        return AdSize.get();
    }

    public void setAdSize(String adSize){
        this.AdSize.set(adSize);
    }

    public StringProperty adSizeProperty(){
        return AdSize;
    }
    //endregion

    //region AdClickNumber
    public int getAdClickNumber() {
        return AdClickNumber.get();
    }

    public void setAdClickNumber(int adClickNumber){
        this.AdClickNumber.set(adClickNumber);
    }

    public IntegerProperty adClickNumberProperty(){
        return AdClickNumber;
    }
    //endregion

    //region AdViewNumber
    public int getAdViewNumber() {
        return AdViewNumber.get();
    }

    public void setAdViewNumber(int adViewNumber){
        this.AdViewNumber.set(adViewNumber);
    }

    public IntegerProperty adViewNumberProperty(){
        return AdViewNumber;
    }
    //endregion

    //region AdSize
    public String getAdClientName() {
        return AdClientName.get();
    }

    public void setAdClientName(String clientName){
        this.AdClientName.set(clientName);
    }

    public StringProperty adClientNameProperty(){
        return AdClientName;
    }
    //endregion

    //region AdSize
    public String getAdAuthorName() {
        return AdAuthorName.get();
    }

    public void setAdAuthorName(String authorName){
        this.AdAuthorName.set(authorName);
    }

    public StringProperty adAuthorNameProperty(){
        return AdAuthorName;
    }
    //endregion

    //region AdTotalPrice
    public Float getAdTotalPrice() {
        return AdTotalPrice.get();
    }

    public void setAdTotalPrice(Float totalPrice){
        this.AdTotalPrice.set(totalPrice);
    }

    public FloatProperty adtotalPriceProperty(){
        return AdTotalPrice;
    }
    //endregion
}
