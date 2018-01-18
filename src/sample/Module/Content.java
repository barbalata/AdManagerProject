package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Catalin-Razvan BARBALATA on 17/01/2018.
 */

public class Content {
    //Declare Employees Table Columns
    private SimpleIntegerProperty ContentID;
    private SimpleStringProperty ContentTitle;
    private SimpleStringProperty ContentBody;
    private SimpleStringProperty ContentMedia;

    //Constructor
    public Content() {
        this.ContentID = new SimpleIntegerProperty();
        this.ContentTitle = new SimpleStringProperty();
        this.ContentBody = new SimpleStringProperty();
        this.ContentMedia = new SimpleStringProperty();
    }

    //region ContentID
    public int getContentID() {
        return ContentID.get();
    }

    public void setContentID(int contentID) {
        this.ContentID.set(contentID);
    }

    public IntegerProperty contentIDProperty() {
        return ContentID;
    }
    //endregion

    //region ContentTitle
    public String getContentTitle() {
        return ContentTitle.get();
    }

    public void setContentTitle(String contentTitle) {
        this.ContentTitle.set(contentTitle);
    }

    public StringProperty contentTitleProperty() {
        return ContentTitle;
    }
    //endregion

    //region ContentBody
    public String getContentBody() {
        return ContentBody.get();
    }

    public void setContentBody(String contentBody) {
        this.ContentBody.set(contentBody);
    }

    public StringProperty contentBodyProperty() {
        return ContentBody;
    }
    //endregion

    //region ContentMedia
    public String getContentMedia() {
        return ContentMedia.get();
    }

    public void setContentMedia(String contentMedia) {
        this.ContentMedia.set(contentMedia);
    }

    public StringProperty contentMediaProperty() {
        return ContentMedia;
    }
    //endregion
}
