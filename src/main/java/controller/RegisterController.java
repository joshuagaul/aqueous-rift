package controller;

/**
 * Created by ahjin on 9/19/2016.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class RegisterController {

    private Stage dialogStage;
    //private User user;
    private boolean okClicked = false;

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

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }
    /**
     * Called when the user clicks back.
     */
    @FXML
    private void handleCancelPressed() {System.exit(0);   // close stage, not exit :/
    }

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
/*
    @FXML
    private void handleOKPressed() {
        if (isInputValid()) {
            user.setUsername(username.getText());
            user.setPassword(password.getPassword());
            user.setFname(fname.getText());
            user.setLname(lname.getText());
            user.setEmail(email.getText());
            user.setPnumber(pnumber.getText());
            if (prefix.getText() != null){
                user.setPrefix(prefix.getText());
                }

            okClicked = true;
            dialogStage.close();
        }
    }
*/



    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ConnectH2O");
        alert.setHeaderText("About");
        alert.setContentText("Update : This is a project for CS2340. Someone update this please");
        alert.showAndWait();

    }
    @FXML
    private void handleContactMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ConnectH2O");
        alert.setHeaderText("Contact");
        alert.setContentText("someone update this: This program is made by: \n\n Graham McAllister \n AhJin Noh \nJoshua Gaul \nKwangHee Kim \nAakanksha Patel");
        alert.showAndWait();

    }

}
