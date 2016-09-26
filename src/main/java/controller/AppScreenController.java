package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import main.MainFXApplication;

import java.io.IOException;

/**
 * Created by ahjin on 9/22/2016.
 */
public class AppScreenController {
    private MainFXApplication mainApplication;
    @FXML
    private Button logout;

    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == logout) {
            mainApplication.showWelcomeScreen();
        }
    }
    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
