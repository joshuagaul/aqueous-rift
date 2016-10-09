package controller;

/**
 * Created by ahjin on 10/7/2016.
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.MainFXApplication;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import java.util.Optional;

/**
 * Controller class for reporting a water source
 *
 */
public class ReportWaterSourceController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField zipCode;

    @FXML
    private ComboBox state;

    @FXML
    private ComboBox waterType;

    @FXML
    private ComboBox waterCondition;

    @FXML
    private TextField virus;
    //TODO this is shown only when user is logged in as a worker or above

    @FXML
    private TextField contamination;
    //TODO this is shown only when user is logged in as a worker or above

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    /**
     * Initializes items (combobox's)
     */
    @FXML
    private void initialize() {
    }

    /**
     * Button handler for editing profile page.
     * Clicking OK button will ask for confirmation,
     * then update the information.
     * Clicking Cancel button will close the alert.
     *
     * @throws IOException throws an exception if fxml file is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == cancel) {
            mainApplication.showMainScreen();
        } else if (event.getSource() == submit) {
            //TODO virus and contamination level should only be shown/reported
            // when user is logged in as worker,manager,admin, etc
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Submission");
            alert.setHeaderText("Are you sure you want to "
                    + "submit this report?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //TODO show report has been submitted
                mainApplication.showMainScreen();
            } else {
                alert.close();
            }
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

