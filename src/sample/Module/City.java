package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class City {
    //Declare Employees Table Columns
    private SimpleIntegerProperty CityID;
    private SimpleIntegerProperty RegionID;
    private SimpleStringProperty CityName;

    //Constructor
    public City(){
        this.CityID = new SimpleIntegerProperty();
        this.RegionID = new SimpleIntegerProperty();
        this.CityName = new SimpleStringProperty();
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

    //region RegionID
    public int getRegionID() {
        return RegionID.get();
    }

    public void setRegionID(int regionID){
        this.RegionID.set(regionID);
    }

    public IntegerProperty regionIDProperty(){
        return RegionID;
    }
    //endregion

    //region CityName
    public String getCityName() {
        return CityName.get();
    }

    public void setCityName(String cityName){
        this.CityName.set(cityName);
    }

    public StringProperty cityNameProperty(){
        return CityName;
    }
    //endregion
}
