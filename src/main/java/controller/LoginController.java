package controller;

import main.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import model.User;

public class LoginController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    @FXML 
    private Text error;
    @FXML 
    protected void handleInvalidLogin(ActionEvent event) {
        error.setText("Invalid username or password. Please try again.");
    }

    @FXML
    protected void handleUsernameInUse(ActionEvent event) {
        error.setText("Username is already in use. Please choose another username.");
    }
    public void setMainApp(MainFXApplication mainFXApplication) {

        mainApplication = mainFXApplication;

    }

    @FXML
    public void registerPressed() {

        User tempUser = new User();
        boolean okClicked = mainApplication.showRegisterDialog(tempUser);

    }



}
