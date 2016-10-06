package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import main.MainFXApplication;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Ahjin Noh on 9/22/2016.
 */

/**
 * Controller class for app main screen when user is logged in.
 */
public class AppScreenController implements IController {
    private MainFXApplication mainApplication;

    @FXML
    private Button logout;

    @FXML
    private Button editProfile;

    /**
     * Lets the user logout when the button is clicked.
     *
     * @throws IOException throws an exception when fxml is not found.
     * @param event logout button user clicks
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == logout) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText("Are you sure you want to logout?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                mainApplication.showWelcomeScreen();
            } else {
                alert.close();
            }
        } else if (event.getSource() == editProfile) {
            mainApplication.showEditProfileScreen();
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
