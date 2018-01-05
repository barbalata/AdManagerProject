package sample.Module;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Author {
    //Declare Employees Table Columns
    private SimpleIntegerProperty AuthorID;
    private SimpleStringProperty AuthorFirstName;
    private SimpleStringProperty AuthorLastName;
    private SimpleStringProperty AuthorPhone;
    private SimpleStringProperty AuthorEmailAdrress;

    //Constructor
    public Author(){
        this.AuthorID = new SimpleIntegerProperty();
        this.AuthorFirstName = new SimpleStringProperty();
        this.AuthorLastName = new SimpleStringProperty();
        this.AuthorPhone = new SimpleStringProperty();
        this.AuthorEmailAdrress = new SimpleStringProperty();
    }

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

    //region AuthorFirstName
    public String getAuthorFirstName() {
        return AuthorFirstName.get();
    }

    public void setAuthorFirstName(String authorFirstName){
        this.AuthorFirstName.set(authorFirstName);
    }

    public StringProperty authorFirstNameProperty(){
        return AuthorFirstName;
    }
    //endregion

    //region AuthorLastName
    public String getAuthorLastName() {
        return AuthorLastName.get();
    }

    public void setAuthorLastName(String authorLastName){
        this.AuthorLastName.set(authorLastName);
    }

    public StringProperty authorLastNameProperty(){
        return AuthorLastName;
    }
    //endregion

    //region AuthorPhone
    public String getAuthorPhone() {
        return AuthorPhone.get();
    }

    public void setAuthorPhone(String authorPhone){
        this.AuthorPhone.set(authorPhone);
    }

    public StringProperty authorPhoneProperty(){
        return AuthorPhone;
    }
    //endregion

    //region AuthorEmailAdrress
    public String getAuthorEmailAdrress() {
        return AuthorEmailAdrress.get();
    }

    public void setAuthorEmailAdrress(String authorEmailAdrress){
        this.AuthorEmailAdrress.set(authorEmailAdrress);
    }

    public StringProperty authorEmailAdrressProperty(){
        return AuthorEmailAdrress;
    }
    //endregion
}
