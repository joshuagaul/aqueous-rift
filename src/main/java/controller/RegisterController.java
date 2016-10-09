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

    private final ObservableList<UserType> userTypeList
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
        usertype.setItems(userTypeList);
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
            mainApplication.showLoginScreen();
        } else if (event.getSource() == ok) {
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

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
