package controller;

/**
 * Created by ahjin on 9/19/2016.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.MainFXApplication;

import java.io.IOException;


public class FindPasswordController {
    private MainFXApplication mainApplication;
    @FXML
    private Button cancel;

    @FXML
    private TextField username;

    @FXML
    private TextField lname;

    @FXML
    private TextField email;

    @FXML
    private Button ok;

    /**
     * Button handler for find password page.
     * Clicking OK button will compare user input with stored data.
     *  If the input matches the data, user's password will be shown on screen.
     * Clicking Cancel button will redirect to the default (login) page.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == cancel) {
            mainApplication.showWelcomeScreen();
        } else if (event.getSource() == ok) {

            //temporary alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ConnectH2O");
            alert.setHeaderText("Find Password");
            alert.setContentText("This feature has not been implemented yet.");
            alert.showAndWait();
        }
    }

    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
