package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import main.MainFXApplication;
import model.UserDataObject;
import classes.UserType;
import classes.User;
import classes.Name;
import java.util.Optional;
import java.io.IOException;

/**
 * Controller class for editing profile screen.
 */
public class EditProfileController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    @FXML
    private Text username;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private ComboBox prefix;

    @FXML
    private TextField email;

    @FXML
    private TextField pnumber;

    @FXML
    private ComboBox<UserType> usertype = new ComboBox<UserType>();

    private final ObservableList<UserType> userType
            = FXCollections.observableArrayList(
            UserType.GeneralUser,
            UserType.Manager,
            UserType.Admin,
            UserType.Worker);

    /**
     * Initializes choices in comboboxes
     */
    @FXML
    private void initialize() {
        prefix.getItems().setAll("Mr", "Ms", "Mrs");
    }

    /**
     * Button handler for editing profile page.
     * Clicking OK button will ask for confirmation,
     * then update the information.
     * Clicking Cancel button will close the alert.
     *
     * @throws IOException throws an exception if fxml file is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == cancel) {
            mainApplication.showMainScreen();
        } else if (event.getSource() == ok) {
            Boolean emptyFieldsExist = validateEditProfile();
            if (emptyFieldsExist) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else if (!newPassword.getText().equals(
                    confirmPassword.getText())) {
                Alert passAlert = new Alert(Alert.AlertType.WARNING);
                passAlert.setTitle("Password");
                passAlert.setHeaderText("Your confirmed password "
                        + "doesn't match the new password field!!");

                fname.setStyle("-fx-border-width: 0px ;");
                lname.setStyle("-fx-border-width: 0px ;");
                email.setStyle("-fx-border-width: 0px ;");
                newPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                confirmPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");

                passAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Profile Update");
                alert.setHeaderText("Are you sure you want to"
                        + " update above information?\n"
                        + "Click \"OK\" to confirm.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    UserDataObject userDAO = UserDataObject.getInstance();
                    User prevUserInfo = mainApplication.getCurrentUser();
                    String uId = prevUserInfo.getUserId();
                    String userType = prevUserInfo.getUserType();
                    Name name = new Name(fname.getText(), lname.getText(),
                            prefix.getValue().toString());
                    User editedUser = new User(newPassword.getText(),
                            email.getText(), pnumber.getText(),
                            uId, name, userType);
                    userDAO.editSingleUser(editedUser, username.getText());
                    mainApplication.showMainScreen();
                } else {
                    alert.close();
                }
            }
        }
    }

    /**
     * Validates the edit profile inputs.  Simple as of now, just checks
     * that required fields aren't empty.
     * TODO - REGEX matching
     * @return An alert message describing the input errors, empty String
     * if input is valid.
     */
    private Boolean validateEditProfile() {
        int emptyFields = 0;
        
        if (newPassword.getText().length() == 0) {
            newPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
            confirmPassword.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
        } else {
            newPassword.setStyle("-fx-border-width: 0px ;");
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

        if (pnumber.getText().length() == 0) {
            pnumber.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            emptyFields++;
        } else {
            pnumber.setStyle("-fx-border-width: 0px ;");
        }

        return (emptyFields != 0);
    }


    /**
     * Helper method to dynamically populate the User's profile.
     * @param  user User logged in
     * @param  username username of the User logged in
     */
    public void populateUserInformation(User user, String username) {
        this.username.setText(username);
        //Don't fill password sections
        prefix.setValue(user.getName().getPrefix());
        fname.setText(user.getName().getFirstName());
        lname.setText(user.getName().getLastName());
        email.setText(user.getEmail());
        pnumber.setText(user.getPhoneNum());
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
