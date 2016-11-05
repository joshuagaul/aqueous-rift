package controller;

import java.util.Date;
import classes.WaterCondition;
import classes.WaterType;
import classes.Location;
import classes.WaterSourceReport;
import classes.WaterPurityReport;
import classes.WaterReport;
import classes.OverallCondition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.MainFXApplication;
import java.io.IOException;
import java.util.Optional;
import model.ReportDataObject;

/**
 * Controller class for reporting a water source
 *
 */
public class EditReportController implements IController {

    private BooleanProperty isSourceReport
            = new SimpleBooleanProperty(true);
    private static BooleanProperty showConfirm
            = new SimpleBooleanProperty(false);
    private MainFXApplication mainApplication;

    @FXML private ComboBox<WaterType> waterType = new ComboBox<>();
    @FXML private ComboBox<WaterCondition> waterCondition = new ComboBox<>();
    @FXML private ComboBox<OverallCondition> overallCondition =
        new ComboBox<>();
    @FXML private TextField longitude;
    @FXML private TextField latitude;
    @FXML private TextField virus;
    @FXML private TextField contamination;
    @FXML private Text ppm1;
    @FXML private Text ppm2;
    @FXML private Label virusLabel;
    @FXML private Label contaminationLabel;
    @FXML private Label typeLabel;
    @FXML private Label conditionLabel;
    @FXML private Label overallConditionLabel;
    @FXML private Button confirmButton;
    @FXML private Button submit;
    @FXML private Button cancel;

    /**
     * Initializes items (combobox's)
     */
    @FXML
    private void initialize() {
        waterType.getItems().setAll(WaterType.values());
        waterCondition.getItems().setAll(WaterCondition.values());
        overallCondition.getItems().setAll(OverallCondition.values());
        ppm1.visibleProperty().bind(isSourceReport.not().or(showConfirm));
        ppm2.visibleProperty().bind(isSourceReport.not().or(showConfirm));
        virusLabel.visibleProperty().bind(isSourceReport.not().or(showConfirm));
        contaminationLabel.visibleProperty().bind(isSourceReport.not()
            .or(showConfirm));
        virus.visibleProperty().bind(isSourceReport.not().or(showConfirm));
        contamination.visibleProperty().bind(isSourceReport.not()
            .or(showConfirm));
        overallCondition.visibleProperty().bind(isSourceReport.not()
            .or(showConfirm));
        overallConditionLabel.visibleProperty().bind(isSourceReport.not()
            .or(showConfirm));
        waterType.visibleProperty().bind(isSourceReport);
        waterCondition.visibleProperty().bind(isSourceReport);
        typeLabel.visibleProperty().bind(isSourceReport);
        conditionLabel.visibleProperty().bind(isSourceReport);
    }

    /**
     * Button handler for editing report page.
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
        } else if (event.getSource() == confirmButton) {
            if (showConfirm.get()) {
                //create resource report
                confirmButton.setId("button-confirm");
                showConfirm.setValue(false);
                confirmButton.setText("Confirm This Report");
            } else {
                //creating purity report
                confirmButton.setId("button-delete");
                showConfirm.setValue(true);
                confirmButton.setText("Back to Source Report");
            }

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
                    WaterReport prevReportInfo =
                            mainApplication.getCurrentReport();
                    String reporterId = prevReportInfo.getReporterId();
                    Location loc = new Location("0", "0");
                    try {
                        loc.setLongitude(longitude.getText());
                        loc.setLatitude(latitude.getText());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    Date date = new Date();
                    WaterType type = waterType.getValue();
                    WaterCondition condition = waterCondition.getValue();
                    if (isSourceReport.get()) {
                        //update source report
                        WaterSourceReport report = new WaterSourceReport(
                                reporterId, loc, type,
                                condition, date);
                        reportDAO.editSourceReport(report,
                                prevReportInfo.getId());
                    } else {
                        //update as a purity report
                        WaterPurityReport report = new WaterPurityReport(
                                reporterId, date, loc, OverallCondition.Safe,
                                Double.parseDouble(virus.getText()),
                                Double.parseDouble(contamination.getText()));
                        reportDAO.confirmPurityReport(report,
                                prevReportInfo.getId());
                    }

                    mainApplication.showMap();
                    mainApplication.showMainScreen();
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
     * @param  report Report object that contains information for UI.
     *
     */
    public void populateReportInformation(WaterReport report) {
        //TODO Add reportedBy to report information
        String latitude = report.getLocation().getLatitude();
        String longitude = report.getLocation().getLongitude();
        WaterType watertype = null;
        Double virus = null;
        Double contamination = null;
        WaterCondition condition = null;
        OverallCondition ovrCondition = null;
        if (report instanceof WaterSourceReport) {
            WaterSourceReport sourceReport = (WaterSourceReport) (report);
            watertype = sourceReport.getType();
            condition = sourceReport.getCondition();
            isSourceReport.set(true);
        } else {
            WaterPurityReport purityReport = (WaterPurityReport) (report);
            virus = purityReport.getVirusPPM();
            contamination = purityReport.getContaminantPPM();
            ovrCondition = purityReport.getOverallCondition();
            isSourceReport.set(false);
        }
        this.longitude.setText(longitude);
        this.latitude.setText(latitude);
        if (waterType != null) {
            this.waterType.setValue(watertype);
        }
        if (condition != null) {
            this.waterCondition.setValue(condition);
        }
        if (virus == null) {
            this.virus.setText("");
        } else {
            this.virus.setText(Double.toString(virus));
        }
        if (contamination == null) {
            this.contamination.setText("");
        } else {
            this.contamination.setText(Double.toString(contamination));
        }
        if (overallCondition != null) {
            this.overallCondition.setValue(ovrCondition);
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
