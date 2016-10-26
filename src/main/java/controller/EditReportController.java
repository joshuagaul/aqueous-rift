package controller;

/**
 * Created by ahjin on 10/25/2016.
 */

import java.util.Date;
import classes.WaterType;
import classes.WaterCondition;
import classes.State;
import classes.Location;
import classes.WaterSourceReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import main.MainFXApplication;
import java.io.IOException;
import java.util.Optional;
import model.ReportDataObject;

/**
 * Controller class for reporting a water source
 *
 */
public class EditReportController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private ComboBox<State> state = new ComboBox();

    @FXML
    private ComboBox<WaterType> waterType = new ComboBox<>();

    @FXML
    private ComboBox<WaterCondition> waterCondition = new ComboBox<>();

    @FXML
    private TextField longitude;

    @FXML
    private TextField latitude;

    @FXML
    private TextField virus;

    @FXML
    private TextField contamination;

    @FXML
    private CheckBox checkbox;
    //TODO checking this will confirm the report
    // and change the pin on the map

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    /**
     * Initializes items (combobox's)
     */
    @FXML
    private void initialize() {
        waterType.getItems().setAll(WaterType.values());
        waterCondition.getItems().setAll(WaterCondition.values());
        state.getItems().setAll(State.values());
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
            String alertMessage = validateEditReport();
            if (alertMessage.length() != 0) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setContentText(alertMessage);
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm Report Update");
                alert.setHeaderText("Are you sure you want to"
                        + " update above information?\n"
                        + "Click \"OK\" to confirm.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    ReportDataObject reportDAO = ReportDataObject.getInstance();
                    WaterSourceReport prevReportInfo =
                            mainApplication.getCurrentReport();
                    String reporterId = prevReportInfo.getReporterId();
                    Location loc = new Location(longitude.getText(),
                            latitude.getText());
                    Date date = new Date();
                    WaterType type = waterType.getValue();
                    WaterCondition condition = waterCondition.getValue();
                    WaterSourceReport report = new WaterSourceReport(reporterId,
                            loc, type, condition, date);
                    reportDAO.editCandidateReport(report, reporterId);
                    mainApplication.showMainScreen();
                    mainApplication.showMap();
                }
            }
        }
    }

    /**
     * Validates the user edited Report inputs. (Checking null fields for now.)
     * @return An alert message describing the input errors.
     */
    private String validateEditReport() {
        StringBuilder alertMessage = new StringBuilder();
        if (longitude.getText().length() == 0) {
            alertMessage.append("Longitude\n");
        }
        if (latitude.getText().length() == 0) {
            alertMessage.append("Latitude\n");
        }
        if (waterCondition.getValue() == null) {
            alertMessage.append("Water Condition\n");
        }
        if (waterType.getValue() == null) {
            alertMessage.append("Water Type\n");
        }
        return alertMessage.toString();
    }




    /**
     * populates the report data from the pin
     * @param  longitude longitude of the report
     * @param  latitude latitude of the report
     * @param watertype water type
     * @param condition water condition
     * @param virus virus
     * @param contamination contamination
     *
     */
    public void populateReportInformation(String longitude,
                                          String latitude,
                                          WaterType watertype,
                                          WaterCondition condition,
                                          double virus,
                                          double contamination) {
        this.longitude.setText(longitude);
        this.latitude.setText(latitude);
        waterType.setValue(watertype);
        waterCondition.setValue(condition);
        this.virus.setText(Double.toString(virus));
        this.contamination.setText(Double.toString(contamination));
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
