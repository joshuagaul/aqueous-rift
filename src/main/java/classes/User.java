package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private Name name;
    private StringProperty password;
    private StringProperty email;
    private StringProperty phoneNum;
    private StringProperty userId;
    private StringProperty userType;

    /**
     * User object constructor
     * @param   password - new user password
     * @param   email - new user email
     * @param   phoneNum - new user phone number
     * @param   userId - new user ID
     * @param   name - new user Name object
     * @param   userType - new user type (general user, manager, admin, worker)
     */
    public User(String password, String email, String phoneNum, String userId,
            Name name, String userType) {
        this.password = new SimpleStringProperty();
        this.password.set(password);
        this.email = new SimpleStringProperty();
        this.email.set(email);
        this.phoneNum = new SimpleStringProperty();
        this.phoneNum.set(phoneNum);
        this.userId = new SimpleStringProperty();
        this.userId.set(userId);
        this.name = name;
        this.userType = new SimpleStringProperty();
        this.userType.set(userType);
    }

    /**
     * User object constructor w/o prefix
     * @param   password New user password
     * @param   email New user email
     * @param   phoneNum New user phone number
     * @param   userId New user ID
     * @param   name New user Name object
     */
    public User(String password, String email, String phoneNum, String userId,
            Name name) {
        this(password, email, phoneNum, userId, name, "");
    }

    /**
     * No Args constructor for FireBase
     */
    private User() {

    }

    /**
     * getter
     * @return name
     */
    public Name getName() {
        return name;
    }

    /**
     * getter
     * @return password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * getter
     * @return email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * getter
     * @return phone number
     */
    public String getPhoneNum() {
        return phoneNum.get();
    }

    /**
     * getter
     * @return userId
     */
    public String getUserId() {
        return userId.get();
    }

    /**
     * getter
     * @return userType
     */
    public String getUserType() {
        return userType.get();
    }
}
