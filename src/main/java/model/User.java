package model;

import model.Name;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Created by ahjin on 9/20/2016.
 */
public class User {

    private Name _name;
    private StringProperty _password;
    private StringProperty _email;
    private StringProperty _phoneNum;
    private StringProperty _userId;


    public User(String password, String email, String phoneNum, String userId, String firstName, String lastName, String prefix) {
        _password = new SimpleStringProperty();
        _password.set(password);
        _email = new SimpleStringProperty();
        _email.set(email);
        _phoneNum = new SimpleStringProperty();
        _phoneNum.set(phoneNum);
        _userId = new SimpleStringProperty();
        _userId.set(userId);
        _name = new Name(firstName, lastName, prefix);
    }

    public User(String password, String email, String phoneNum, String userId, String firstName, String lastName) {
        this(password, email, phoneNum, userId, firstName, lastName, "");
    }

    public Name getName() {
        return _name;
    }

    public String getPassword() {
        return _password.get();
    }

    public String getEmail() {
        return _email.get();
    }

    public String getPhoneNum() {
        return _phoneNum.get();
    }

    public String getUserId() {
        return _userId.get();
    }
}
