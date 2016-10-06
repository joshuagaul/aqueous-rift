package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import main.MainFXApplication;
import coredata.UserDataObject;
import model.User;
import model.Name;
import model.UserType;

import java.io.IOException;
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
     * Initializes item (combobox)
     */

    @FXML
    private void initialize() {
        usertype.setItems(userType);
        usertype.setValue((UserType.GeneralUser));
    }


    /**
     * Button handler for creating an account page.
     * Clicking OK button will store new user information and create an account.
     * Clicking Cancel button will redirect to the welcome page.
     *
     * @throws IOException throws an exception if fxml not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == cancel) {
            mainApplication.showWelcomeScreen();
        } else if (event.getSource() == ok) {
            //TODO Get rid of or randomize/increment userId
            //TODO check & display error if userid or email is already in use.
            //TODO Do not accept null values!
            Name name = new Name(fname.getText(), lname.getText(),
                prefix.getText());
            User testUser = new User(password.getText(), email.getText(),
                pnumber.getText(), "4", name,
                usertype.getValue().toString());
            UserDataObject userDAO = UserDataObject.getInstance();
            userDAO.addSingleUser(testUser, username.getText());

            //TODO Notify success / go to another page / etc
            mainApplication.showWelcomeScreen();
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
