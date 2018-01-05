package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Region {
    //Declare Employees Table Columns
    private SimpleIntegerProperty RegionID;
    private SimpleStringProperty RegionName;

    //Constructor
    public Region() {
        this.RegionID = new SimpleIntegerProperty();
        this.RegionName = new SimpleStringProperty();
    }

    //region RegionID
    public int getRegionID() {
        return RegionID.get();
    }

    public void setRegionID(int regionID) {
        this.RegionID.set(regionID);
    }

    public IntegerProperty regionIDProperty() {
        return RegionID;
    }
    //endregion

    //region RegionName
    public String getRegionName() {
        return RegionName.get();
    }

    public void setRegionName(String regionName) {
        this.RegionName.set(regionName);
    }

    public StringProperty regionNameProperty() {
        return RegionName;
    }
    //endregion
}
