package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.MainFXApplication;

import java.io.IOException;

/**
 * Created by ahjin on 9/22/2016.
 */
    public class WelcomeScreenController {
    /**
     * a link back to the main application class
     */
    private MainFXApplication mainApplication;

    @FXML
    private Button loginpage;

    @FXML
    private Button createAccount;

    /**
     * Button handler for login page.
     * Clicking "Create an Account" will redirect to Register page.
     * Clicking "Forgot My Password" will redirect to Finding password page.
     * Clicking "Login" will redirect to main application.
     *
     * @param event the button user clicks.
     */

    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == loginpage) {
            mainApplication.showLoginScreen();
        } else if (event.getSource() == createAccount) {
            mainApplication.showRegisterScreen();
        }
    }

    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }


}