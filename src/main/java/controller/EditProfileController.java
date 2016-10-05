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
import main.MainFXApplication;
import coredata.UserDataObject;
import model.UserType;
import model.User;
import model.Name;
import java.util.Optional;
import java.io.IOException;

/**
 * Controller class for editing profile screen.
 */
public class EditProfileController {

    private MainFXApplication mainApplication;

    @FXML
    private Button cancel;

    @FXML
    private Button ok;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

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
     * Initializes items (combobox)
     */
    @FXML
    private void initialize() {
        usertype.setItems(userType);
        usertype.setValue((UserType.GeneralUser));
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
            mainApplication.showAppScreen();
        } else if (event.getSource() == ok) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Profile Update");
            alert.setHeaderText("Are you sure you want to"
                    + "update above information?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //TODO Make username not changeable
                //TODO Do not accept null values!
                //Create new user with the current user's userID and userType
                UserDataObject userDAO = UserDataObject.getInstance();
                User prevUserInfo = mainApplication.getCurrentUser();
                String uId = prevUserInfo.getUserId();
                String userType = prevUserInfo.getUserType();
                Name name = new Name(fname.getText(), lname.getText(),
                    prefix.getText());
                User editedUser = new User(password.getText(), email.getText(),
                    pnumber.getText(), uId, name, userType);
                userDAO.editSingleUser(editedUser, username.getText());
                mainApplication.showWelcomeScreen();
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
