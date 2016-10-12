package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.MainFXApplication;
import coredata.UserDataObject;
import model.User;
import model.Name;
import model.UserType;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller class for registration page.
 */
public class RegisterController implements IController {

    private MainFXApplication mainApplication;

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
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField prefix;
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
            //code to validate data
            String alertMessage = "";
            if (username.getText().length() == 0) {
                alertMessage = alertMessage
                        + "The username field was left blank.\n";
            }
            if (password.getText().length() == 0) {
                alertMessage = alertMessage
                        + "The password field was left blank.\n";
            }
            if (fname.getText().length() == 0) {
                alertMessage = alertMessage
                        + "The first name field was left blank.\n";
            }
            if (lname.getText().length() == 0) {
                alertMessage = alertMessage
                        + "The last name field was left blank.\n";
            }
            if (email.getText().length() == 0) {
                alertMessage = alertMessage
                        + "The email field was left blank.\n";
            }
            if (alertMessage.length() != 0) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setContentText(alertMessage);
                emptyAlert.setHeaderText("Please fill out all the required fields.");
                emptyAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm registration");
                alert.setHeaderText("Are you sure above information is correct?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //TODO Get rid of or randomize/increment userId
                    //TODO check & display error if userid or email
                    // is already in use.
                    //TODO Do not accept null values! (maybe i'll disable
                    // the ok button until all fields are filled)


                    //code after all data is validated
                    Name name = new Name(fname.getText(), lname.getText(),
                            prefix.getText());
                    User testUser = new User(password.getText(), email.getText(),
                            pnumber.getText(), "4", name,
                            usertype.getValue().toString());
                    UserDataObject userDAO = UserDataObject.getInstance();
                    userDAO.addSingleUser(testUser, username.getText());
                    mainApplication.showMainScreen();
                } else {
                    alert.close();
                }
            }
        }
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
