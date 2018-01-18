package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class LinkAdAuthor {
    //Declare Employees Table Columns
    private SimpleIntegerProperty AdID;
    private SimpleIntegerProperty AuthorID;

    //Constructor
    public LinkAdAuthor() {
        this.AdID = new SimpleIntegerProperty();
        this.AuthorID = new SimpleIntegerProperty();
    }

    //region AdID
    public int getAdID() {
        return AdID.get();
    }

    public void setAdID(int adID) {
        this.AdID.set(adID);
    }

    public IntegerProperty adIdProperty() {
        return AdID;
    }
    //endregion

    //region AuthorID
    public int getAuthorID() {
        return AuthorID.get();
    }

    public void setAuthorID(int authorID) {
        this.AuthorID.set(authorID);
    }

    public IntegerProperty authorIdProperty() {
        return AuthorID;
    }
    //endregion
}
