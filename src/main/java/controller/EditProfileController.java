package controller;

/**
 * Created by ahjin on 9/19/2016.
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.MainFXApplication;
import coredata.UserDataObject;
import model.UserType;
import model.User;

import java.io.IOException;
import java.util.Optional;


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
    private final ObservableList<UserType> userType = FXCollections.observableArrayList(
            UserType.GeneralUser,
            UserType.Manager,
            UserType.Admin,
            UserType.Worker);


    @FXML
    private void initialize() {
        usertype.setItems(userType);
        usertype.setValue((UserType.GeneralUser));
    }


    /**
     * Button handler for creating an account page.
     * Clicking OK button will store new user information and create an account.
     * Clicking Cancel button will redirect to the default (login) page.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource()==cancel) {
            mainApplication.showWelcomeScreen();
        } else if (event.getSource()==ok){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Profile Update");
            alert.setHeaderText("Are you sure you want to update above information?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                //TODO I don't think they should be able to edit username (if we want that, will need to change data structure)
                //Create new user with the current user's userID and userType
                UserDataObject userDAO = UserDataObject.getInstance();
                User prevUserInfo = mainApplication.getCurrentUser();
                String uId = prevUserInfo.getUserId();
                String userType = prevUserInfo.getUserType();
                User editedUser = new User(password.getText(), email.getText(), pnumber.getText(), uId, fname.getText(), lname.getText(), prefix.getText(), userType);
                userDAO.editSingleUser(editedUser, username.getText());
                mainApplication.showWelcomeScreen();
            } else {
                alert.close();
            }
        }
    }

    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        this.mainApplication = mainFXApplication;
    }


}
