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
import model.User;
import model.UserType;

import java.io.IOException;


public class RegisterController {
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
           //TODO Get rid of or randomize/increment userId
           User testUser = new User(password.getText(), email.getText(), pnumber.getText(), "4", fname.getText(), lname.getText(), prefix.getText(), usertype.getValue().toString());
           UserDataObject userDAO = UserDataObject.getInstance();
           userDAO.addSingleUser(testUser, username.getText());

           //TODO Notify success / go to another page / etc
       }
    }

    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

///////////////////////extra stuff///////////////////////////////////

    /**
     * Displays an error message if username is already in use. ??
     *
     * @param event string of input username to be compared with stored data.
     */

/*
    @FXML
    protected void handleUsernameInUse(ActionEvent event) {
        //if (event == already in file) { ...display below text}
        error.setText("Username is already in use. Please choose another username.");
    }
    */

    /*
    private boolean isInputValid() {


        String errorMessage = "";
        if (username.getText() == null || username.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        //should we check for length/special chars/etc
        // can someone write code for checking username already in use and display error message
        if (password.getText() == null || password.getText().length() == 0) {
            errorMessage += "No valid password entered!\n";
        }
        // possibly check for password length?
        if (fname.getText() == null || fname.getText().length() == 0) {
            errorMessage += "No first name entered!\n";
        }

        if (lname.getText() == null || lname.getText().length() == 0) {
            errorMessage += "No valid last name entered!\n";
        }

        if (email.getText() == null || email.getText().length() == 0) {
            errorMessage += "No valid email entered!\n";
        }
        // follow __@___ format?

        if (pnumber.getText() == null || pnumber.getText().length() == 0) {
            errorMessage += "No valid phone number entered!\n";
        }

        //no error message means success / good input
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message if bad data
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    */



}
