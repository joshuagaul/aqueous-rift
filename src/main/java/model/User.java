package model;

import model.Name;
/**
 * Created by ahjin on 9/20/2016.
 */
public class User {

    public Name name;
    public String password;
    public String email;
    public String phoneNum;
    public String userId;

    public User(String password, String email, String phoneNum, String userId) {
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.userId = userId;
    }

    public void setName(String firstName, String lastName) {
        name = new Name(firstName, lastName);
    }

    public void setName(String firstName, String lastName, String prefix) {
        name = new Name(firstName, lastName, prefix);
    }

}
