package sample.Module;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class Price {
    //Declare Employees Table Columns
    private SimpleIntegerProperty PriceID;
    private SimpleStringProperty PriceUnite;
    private SimpleObjectProperty<BigDecimal> PriceCostClick;
    private SimpleObjectProperty<BigDecimal> PriceCostView;


    //Constructor
    public Price(){
        this.PriceID = new SimpleIntegerProperty();
        this.PriceUnite = new SimpleStringProperty();
        this.PriceCostClick = new SimpleObjectProperty<>();
        this.PriceCostView = new SimpleObjectProperty<>();
    }

    //region PriceID
    public int getPriceID() {
        return PriceID.get();
    }

    public void setPriceID(int priceID){
        this.PriceID.set(priceID);
    }

    public IntegerProperty priceIDProperty(){
        return PriceID;
    }
    //endregion

    //region PriceUnite
    public String getPriceUnite() {
        return PriceUnite.get();
    }

    public void setPriceUnite(String priceUnite){
        this.PriceUnite.set(priceUnite);
    }

    public StringProperty priceUniteProperty(){
        return PriceUnite;
    }
    //endregion

    //region PriceCostClick
    public BigDecimal getPriceCostClick() {
        return PriceCostClick.get();
    }

    public void setPriceCostClick(BigDecimal priceCostClick){
        this.PriceCostClick.set(priceCostClick);
    }

    public SimpleObjectProperty<BigDecimal> priceCostClickProperty() {
        return PriceCostClick;
    }
    //endregion

    //region PriceCostView
    public BigDecimal getPriceCostView() {
        return PriceCostView.get();
    }

    public void setPriceCostView(BigDecimal priceCostView){
        this.PriceCostView.set(priceCostView);
    }

    public SimpleObjectProperty<BigDecimal> priceCostViewProperty() {
        return PriceCostView;
    }
    //endregion
}
