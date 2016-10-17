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
            String alertMessage = validateEditProfile();
            if (alertMessage.length() != 0) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setContentText(alertMessage);
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else if (!newPassword.getText().equals(confirmPassword.getText())) {
                Alert passAlert = new Alert(Alert.AlertType.WARNING);
                passAlert.setTitle("Password");
                passAlert.setHeaderText("Your confirmed password "
                        + "doesn't match the new password field!!");
                passAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Profile Update");
                alert.setHeaderText("Are you sure you want to"
                        + " update above information?\n"
                        + "Click \"OK\" to confirm.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //TODO check for matching passwords
                    //TODO Do not accept null values!
                    //Create new user with the current user's userID and userType
                    UserDataObject userDAO = UserDataObject.getInstance();
                    User prevUserInfo = mainApplication.getCurrentUser();
                    String uId = prevUserInfo.getUserId();
                    String userType = prevUserInfo.getUserType();
                    Name name = new Name(fname.getText(), lname.getText(),
                            prefix.getValue().toString());
                    User editedUser = new User(newPassword.getText(),
                            email.getText(), pnumber.getText(), uId, name, userType);
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
    private String validateEditProfile() {
        StringBuilder alertMessage = new StringBuilder();
        if (username.getText().length() == 0) {
            alertMessage.append("Username\n");
        }
        if (newPassword.getText().length() == 0) {
            alertMessage.append("Password\n");
        }
        if (fname.getText().length() == 0) {
            alertMessage.append("First name\n");
        }
        if (lname.getText().length() == 0) {
            alertMessage.append("Last name\n");
        }
        if (email.getText().length() == 0) {
            alertMessage.append("Email\n");
        }
        if (pnumber.getText().length() == 0) {
            alertMessage.append("P number\n");
        }
        return alertMessage.toString();
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
