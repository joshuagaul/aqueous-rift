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
 * Created by ahjin on 9/22/2016.
 */
public class AppScreenController {
    private MainFXApplication mainApplication;
    @FXML
    private Button logout;
    @FXML
    private Button editProfile;

    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == logout) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText("Are you sure you want to logout?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                mainApplication.showWelcomeScreen();
            } else {
                alert.close();
            }
        } else if (event.getSource()==editProfile){
            mainApplication.showEditProfileScreen();
        }
    }
    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
