package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import main.MainFXApplication;
import model.UserDataObject;
import classes.User;
import classes.Name;
import classes.UserType;
import java.util.Optional;

/**
 * Controller class for registration page.
 */
public class RegisterController implements IController {

    private MainFXApplication mainApplication;

    private UserDataObject userDAO = UserDataObject.getInstance();
    
    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;
    //TODO Tabbing out of the passwordField doesn't work

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private ComboBox<String> prefix;
    //TODO I think this should be a dropdown menu

    @FXML
    private TextField email;

    @FXML
    private TextField pnumber;
    @FXML
    private ComboBox<UserType> usertype = new ComboBox<UserType>();

    /**
     * Initializes item (combobox)
     */
    @FXML
    private void initialize() {
        usertype.getItems().setAll(UserType.values());
        usertype.setValue((UserType.GeneralUser));
        prefix.getItems().setAll("Mr", "Ms", "Mrs");
    }


    /**
     * Button handler for creating an account page.
     * Clicking OK button will store new user information and create an account.
     * Clicking Cancel button will redirect to the welcome page.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == cancel) {
            mainApplication.showLoginScreen();
        } else if (event.getSource() == ok) {
            Boolean emptyFieldsExist = validateUserRegistration();
            if (emptyFieldsExist) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else if (!password.getText().equals(
                    confirmPassword.getText())) {
                Alert passAlert = new Alert(Alert.AlertType.WARNING);
                passAlert.setTitle("Password");
                passAlert.setHeaderText("Your confirmed password "
                        + "doesn't match the new password field!!");
                password.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                confirmPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                passAlert.showAndWait();
            } else if (userDAO.userExists(username.getText())) {
                password.setStyle("-fx-border-width: 0px ;");
                confirmPassword.setStyle("-fx-border-width: 0px ;");
                Alert takenAlert = new Alert(Alert.AlertType.WARNING);
                takenAlert.setTitle("Password");
                username.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                takenAlert.setHeaderText("Your chosen username "
                        + "is already taken! Please choose "
                        + "another username for yourself.");
                takenAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm registration");
                alert.setHeaderText("Are you sure above"
                        + " information is correct?\n"
                        + "Click \"OK\" to confirm.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //TODO check & display error if userid or email
                    // is already in use.
                    String pre = prefix.getValue();
                    if (pre == null) {
                        pre = "";
                    }
                    Name name = new Name(fname.getText(), lname.getText(),
                            pre);
                    User testUser = new User(password.getText(),
                            email.getText(),
                            pnumber.getText(), "4", name,
                            usertype.getValue().toString());
                    UserDataObject userDAO = UserDataObject.getInstance();
                    userDAO.addSingleUser(testUser, username.getText());
                    mainApplication.showLoginScreen();
                } else {
                    alert.close();
                }
            }
        }
    }

    /**
     * Validates the user Registration inputs.  Simple as of now, just checks
     * that required fields aren't empty.
     * TODO - REGEX matching
     * @return An alert message describing the input errors, empty String
     * if input is valid.
     */
    private Boolean validateUserRegistration() {
        int emptyFields = 0;
        if (username.getText().length() == 0) {
            username.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
        } else {
            username.setStyle("-fx-border-width: 0px ;");
        }

        if (password.getText().length() == 0) {
            password.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
            confirmPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            password.setStyle("-fx-border-width: 0px ;");
            confirmPassword.setStyle("-fx-border-width: 0px ;");
        }

        if (fname.getText().length() == 0) {
            fname.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
        } else {
            fname.setStyle("-fx-border-width: 0px ;");
        }
        if (lname.getText().length() == 0) {
            lname.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
        } else {
            lname.setStyle("-fx-border-width: 0px ;");
        }
        if (email.getText().length() == 0) {
            email.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
        } else {
            email.setStyle("-fx-border-width: 0px ;");
        }
        return (emptyFields != 0);
    }

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
