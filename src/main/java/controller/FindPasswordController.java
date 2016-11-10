package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.MainFXApplication;

/**
 * Controller class for finding password page.
 */
public class FindPasswordController implements IController {
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
     * Clicking OK button will compare user input with stored model.
     * If the input matches the model, user's password will be [...]
     * //TODO password will be displayed or emailed?
     * Clicking Cancel button will redirect to the welcome page.
     *
     * @param event the button user clicks.
     */

    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == cancel) {
            mainApplication.showLoginScreen();
        } else if (event.getSource() == ok) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aqueous Rift");
            alert.setHeaderText("Find Password");
            alert.setContentText("This feature has not been implemented yet.");
            alert.showAndWait();
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
