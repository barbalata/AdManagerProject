package sample.Module;

import javafx.beans.property.*;

import java.math.BigDecimal;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class Location {
    //Declare Employees Table Columns
    private SimpleIntegerProperty LocationID;
    private SimpleObjectProperty<BigDecimal> LocationCoordonateX;
    private SimpleObjectProperty<BigDecimal> LocationCoordonateY;

    //Constructor
    public Location(){
        this.LocationID = new SimpleIntegerProperty();
        this.LocationCoordonateX = new SimpleObjectProperty<>();
        this.LocationCoordonateY = new SimpleObjectProperty<>();
    }

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

    //region LocationCoordonateX
    public BigDecimal getLocationCoordonateX() {
        return LocationCoordonateX.get();
    }

    public void setLocationCoordonateX(BigDecimal locationCoordonateX){
        this.LocationCoordonateX.set(locationCoordonateX);
    }

    public ObjectPropertyBase<BigDecimal> locationCoordonateXProperty(){
        return LocationCoordonateX;
    }
    //endregion

    //region LocationCoordonateY
    public BigDecimal getLocationCoordonateY() {
        return LocationCoordonateY.get();
    }

    public void setLocationCoordonateY(BigDecimal locationCoordonateY){
        this.LocationCoordonateY.set(locationCoordonateY);
    }

    public ObjectPropertyBase<BigDecimal> locationCoordonateYProperty(){
        return LocationCoordonateY;
    }
    //endregion
}
