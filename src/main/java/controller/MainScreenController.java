/*
 * Created by AhJin Noh on 9/22/2016.
 */
package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import main.MainFXApplication;
import java.util.Optional;

/**
 * Controller for main app screen page, which will be
 * "landing page" for all non-users and users.
 * All guests can view the reports by clicking the pins on map.
 * + Users can submit a report.
 * + Workers can submit a report with virus & contamination
 * level, and good water will show on the map.
 * + Managers can edit and delete a report, and view historical graph.
 * + Admins can ban users and view security logs.
 * Someone please detail this.
 */
public class MainScreenController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private Button report;

    @FXML
    private Button update;
    //TODO this is shown only when user is logged in as a manager

    @FXML
    private Button delete;
    //TODO this is shown only when user is logged in as a manager

    /**
     * Button handler for mainScreenPage.
     * Clicking "Submit" will redirect to water source
     * report if logged in. Login is required.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == report) {
            //TODO (see below)
            // if (!loggedin) {redirect to showLoginScreen}
            // else {redirect to showReportSourceScreen}
            mainApplication.showLoginScreen();
        } else if (event.getSource() == update) {
            // update the current report
            System.out.println("Update the current report.");
        } else if (event.getSource() == delete) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete this report");
            alert.setHeaderText("Are you sure you want to delete this report?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //TODO delete the report and clear the texts
                mainApplication.showMainScreen();
            }
        }
    }

    /**
     * Shows a report when the user clicks the pin on map.
     *
     * @param event user clicks a pin.
     */
    @FXML
    private void handlePinClicked(ActionEvent event) {
        //TODO (see below)
        //Set the text to the information pulled from the map & report
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
