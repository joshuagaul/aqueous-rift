package classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private Name name;
    private StringProperty password = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phoneNum = new SimpleStringProperty();
    private StringProperty userId = new SimpleStringProperty();
    private StringProperty userType  = new SimpleStringProperty();

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
        this.name = name;
        this.password.set(password);
        this.email.set(email);
        this.phoneNum.set(phoneNum);
        this.userId.set(userId);
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
     * getter
     * @return name
     */
    public Name getName() {
        return name;
    }

    /**
     * Public setter for Name.
     * @param name Multi-attribute name object.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * getter
     * @return password
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Public setter for password.
     * @param password New password.
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    /**
     * getter
     * @return email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * Public setter for email.
     * @param email New email.
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * getter
     * @return phone number
     */
    public String getPhoneNum() {
        return phoneNum.get();
    }

    /**
     * Public setter for phoneNum.
     * @param phoneNum New phone number.
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum.set(phoneNum);
    }

    /**
     * getter
     * @return userId
     */
    public String getUserId() {
        return userId.get();
    }

    /**
     * Public setter for userId.
     * @param userId New user ID.
     */
    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    /**
     * getter
     * @return userType
     */
    public String getUserType() {
        return userType.get();
    }

    /**
     * Public setter for UserType.
     * @param userType New type of User.
     */
    public void setUserType(String userType) {
        this.userType.set(userType);
    }

    /**
     * No Args constructor for FireBase
     */
    private User() {

    }
}
