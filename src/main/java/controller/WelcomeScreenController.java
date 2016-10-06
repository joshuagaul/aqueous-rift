/**
 * Created by AhJin Noh on 9/22/2016.
 */
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.MainFXApplication;

import java.io.IOException;
/**
 * Controller for welcome screen page.
 */
public class WelcomeScreenController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private Button loginpage;

    @FXML
    private Button createAccount;

    /**
     * Button handler for welcome page.
     * Clicking "Login" will redirect to Login page.
     * Clicking "Register" will redirect to creating account page.
     * .
     *@throws IOException throws an exception if fxml is not found.
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

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }
}
