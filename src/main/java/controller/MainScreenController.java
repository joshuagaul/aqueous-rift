/*
 * Created by AhJin Noh on 9/22/2016.
 */
package controller;
import classes.WaterSourceReport;
import classes.WaterPurityReport;
import classes.WaterReport;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
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
    private static BooleanProperty isAuthorized
            = new SimpleBooleanProperty(false);
    private static BooleanProperty isLoggedIn
            = new SimpleBooleanProperty(false);
    private MainFXApplication mainApplication;
    @FXML private Text longitude;
    @FXML private Text latitude;
    @FXML private Button report;
    @FXML private Text condition;
    @FXML private Text type;
    @FXML private Text date;
    @FXML private Button update;
    @FXML private Button delete;
    @FXML private Label virusLabel;
    @FXML private Label contaminationLabel;
    @FXML private Text virus;
    @FXML private Text contamination;

    /**
     * Initializes the buttons
     */
    @FXML
    private void initialize() {
        delete.visibleProperty().bind(isAuthorized);
        update.visibleProperty().bind(isAuthorized);
        virus.visibleProperty().bind(isAuthorized);
        contamination.visibleProperty().bind(isAuthorized);
        virusLabel.visibleProperty().bind(isAuthorized);
        contaminationLabel.visibleProperty().bind(isAuthorized);

        // WaterSourceReport curReport = mainApplication.getCurrentReport();
        // if (curReport != null && isLoggedIn.get()) {
        //     date.setText(curReport.getDate());
        //     type.setText(curReport.getType().toString());
        //     condition.setText(curReport.getCondition().toString());
        //     longitude.setText(curReport.getLocation().getLongitude());
        //     latitude.setText(curReport.getLocation().getLatitude());
        // }
    }

    /**
     * set the button based on the usertypes
     * @param set true if the user is authorized (worker, admin, manager).
     *            false if the user is not logged in or a general user.
     */
    public static void setAuthority(boolean set) {
        isAuthorized.set(set);
    }


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
            if (mainApplication.getCurrentUser() == null) {
                mainApplication.showLoginScreen();
            } else {
                mainApplication.showReportScreen();
            }
        } else if (event.getSource() == update) {
            if (mainApplication.getCurrentSourceReport() != null
                    || mainApplication.getCurrentPurityReport() != null) {
                mainApplication.showEditReportScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Click a pin to update a report");
                alert.setHeaderText("Please click on a pin to select a report");
                alert.showAndWait();
            }
        } else if (event.getSource() == delete) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete this report");
            alert.setHeaderText("Are you sure you want to delete this report?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
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
     * NOTE: THIS IS TEMPORARY - to show the data info on the main screen
     * @param set true if the user is logged in.
     */
    public static void setLoggedIn(boolean set) {
        isLoggedIn.set(set);
    }

    /**
     * Sets the current report
     * @param report Report to set
     */
    public void setCurrentReport(WaterReport report) {
        if (report instanceof WaterSourceReport) {
            WaterSourceReport sourceReport = (WaterSourceReport) report;
            if (report != null && isLoggedIn.get()) {
                type.setText(sourceReport.getType().toString());
                condition.setText(sourceReport.getCondition().toString());
            }
        } else if (report instanceof WaterPurityReport) {
            WaterPurityReport purityReport = (WaterPurityReport) report;
            if (report != null && isLoggedIn.get()) {
                condition.setText(purityReport.getCondition().toString());
                contamination.setText(Double.toString(
                        purityReport.getContaminantPPM()));
                virus.setText(Double.toString(purityReport.getVirusPPM()));
            }
        }
        delete.visibleProperty().bind(isAuthorized);
        update.visibleProperty().bind(isAuthorized);
        date.setText(report.getDateAsString());
        longitude.setText(report.getLocation().getLongitude());
        latitude.setText(report.getLocation().getLatitude());
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
