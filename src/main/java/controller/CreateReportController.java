package controller;

/*
  Created by AhJin on 10/7/2016.
 */

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import classes.WaterCondition;
import classes.WaterType;
import classes.UserType;
import classes.Location;
import classes.WaterSourceReport;
import classes.WaterPurityReport;
import classes.OverallCondition;

import main.MainFXApplication;

import java.util.Optional;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.ReportDataObject;

/**
 * Controller class for reporting a water source
 *
 */
public class CreateReportController implements IController {

    private BooleanProperty purityReport
            = new SimpleBooleanProperty(false);
    private static BooleanProperty isAuthorized
            = new SimpleBooleanProperty(false);
    private static BooleanProperty showConfirm
            = new SimpleBooleanProperty(false);
    private ObjectProperty<UserType> userType = new SimpleObjectProperty<>();
    private MainFXApplication mainApplication;

    @FXML
    private ComboBox<WaterType> waterType = new ComboBox<>();

    @FXML
    private ComboBox<WaterCondition> waterCondition = new ComboBox<>();

    @FXML
    private ComboBox<OverallCondition> overallCondition = new ComboBox<>();

    @FXML
    private TextField longitude;

    @FXML
    private TextField latitude;

    @FXML
    private Label typeLabel;

    @FXML
    private Label conditionLabel;

    @FXML
    private TextField virus;

    @FXML
    private TextField contamination;

    @FXML
    private Text ppm1;

    @FXML
    private Text ppm2;

    @FXML
    private Label virusLabel;

    @FXML
    private Label contaminationLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    @FXML
    private Label overallConditionLabel;

    /**
     * Initializes items (comboBox's)
     */
    @FXML
    private void initialize() {
        //Populate static comboBox data
        waterType.getItems().setAll(WaterType.values());
        waterType.visibleProperty().bind(showConfirm.not());
        typeLabel.visibleProperty().bind(showConfirm.not());
        waterCondition.getItems().setAll(WaterCondition.values());
        waterCondition.visibleProperty().bind(showConfirm.not());
        conditionLabel.visibleProperty().bind(showConfirm.not());
        //Bind event handler and set binding variables
        userType.set(UserType.GeneralUser);
        ppm1.visibleProperty().bind(isAuthorized.and(showConfirm));
        ppm2.visibleProperty().bind(isAuthorized.and(showConfirm));
        virusLabel.visibleProperty().bind(isAuthorized.and(showConfirm));
        contaminationLabel.visibleProperty().bind(
                isAuthorized.and(showConfirm));
        virus.visibleProperty().bind(isAuthorized.and(showConfirm));
        contamination.visibleProperty().bind(isAuthorized.and(showConfirm));
        overallCondition.getItems().setAll(OverallCondition.values());
        overallCondition.visibleProperty().bind(isAuthorized.and(showConfirm));
        overallConditionLabel.visibleProperty().bind(
                isAuthorized.and(showConfirm));
        confirmButton.visibleProperty().bind(isAuthorized);
    }

    /**
     * set the visibility of the text fields based on the user type.
     * @param set true if the user is authorized (worker, admin, manager).
     *            false if the user is not logged in or a general user.
     */
    public static void setAuthority(boolean set) {
        isAuthorized.set(set);
    }

    /*

    private BooleanProperty checkUser() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals(UserType.GeneralUser));
        return res;
    }
    */

    /*
    private BooleanProperty checkAdmin() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Admin"));
        return res;
    }
    */
    /*
    private BooleanProperty checkWorker() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Worker"));
        return res;
    }
    */
    /*
    private BooleanProperty checkManager() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Manager"));
        return res;
    }
    */

    /**
     * Button handler for editing profile page.
     * Clicking OK button will ask for confirmation,
     * then update the information.
     * Clicking Cancel button will close the alert.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == cancel) {
            mainApplication.showMainScreen();
        } else if (event.getSource() == confirmButton) {
            System.out.println(showConfirm.get());
            if (showConfirm.get()) {
                //create resource report
                purityReport.setValue(false);
                confirmButton.setId("button-confirm");
                showConfirm.setValue(false);
                confirmButton.setText("Confirm This Report");
            } else {
                //creating purity report
                purityReport.setValue(true);
                confirmButton.setId("button-delete");
                showConfirm.setValue(true);
                confirmButton.setText("Back to Source Report");
            }

        } else if (event.getSource() == submit) {
            latitude.setStyle(
                "-fx-border-width: 0px ;");
            longitude.setStyle(
                "-fx-border-width: 0px ;");
            waterType.setStyle(
                "-fx-border-width: 0px ;");
            waterCondition.setStyle(
                "-fx-border-width: 0px ;");
            virus.setStyle(
                "-fx-border-width: 0px ;");
            contamination.setStyle(
                "-fx-border-width: 0px ;");
            overallCondition.setStyle(
                "-fx-border-width: 0px ;");
            
            ReportDataObject reportDAO = ReportDataObject.getInstance();
            String reporterId = mainApplication.getCurrentUsername();
            Location loc = new Location("0", "0");

            if (emptyFieldsExist()) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else if (!correctDataType()) {
                Alert wrongTypeAlert = new Alert(Alert.AlertType.WARNING);
                wrongTypeAlert.setTitle("Invalid data");
                wrongTypeAlert.setHeaderText("Please enter valid data "
                        + " in the required fields.");
                wrongTypeAlert.showAndWait();
            } else {
                waterCondition.setStyle("-fx-border-width: 0px ;");
                waterType.setStyle("-fx-border-width: 0px ;");
                virus.setStyle("-fx-border-width: 0px ;");
                contamination.setStyle("-fx-border-width: 0px ;");

                if (validateLatAndLon(latitude.getText(),
                    longitude.getText())) {
                    try {
                        loc.setLongitude(longitude.getText());
                        loc.setLatitude(latitude.getText());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    Date date = new Date();
                    WaterType type = waterType.getValue();
                    WaterCondition condition = waterCondition.getValue();
                    OverallCondition overallcondition =
                            overallCondition.getValue();
                    if (!purityReport.get()) {
                        WaterSourceReport report
                            = new WaterSourceReport(reporterId,
                            loc, type, condition, date);
                        reportDAO.addSourceReport(report);
                    } else {
                        WaterPurityReport report
                            = new WaterPurityReport(reporterId,
                            date, loc, overallcondition,
                            Double.parseDouble(virus.getText()),
                            Double.parseDouble(contamination.getText()));
                        reportDAO.addPurityReport(report);
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Report Submitted");
                    alert.setHeaderText("Your report has been submitted.");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        mainApplication.showMap();
                        mainApplication.showMainScreen();
                    }
                }
            }
        }
    }

    /**
     * checks all fields for null values
     *
     * @return A boolean value showing if there are null values.
     */
    private Boolean emptyFieldsExist() {
        int count = 0;
        
        if (longitude.getText().length() == 0) {
            longitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            count++;
        }
        if (latitude.getText().length() == 0) {
            latitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            count++;
        }
        if (showConfirm.not().get()) {
            if (waterCondition.getValue() == null) {
                waterCondition.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
            if (waterType.getValue() == null) {
                waterType.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
        } else {
            if (overallCondition.getValue() == null) {
                overallCondition.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
            if (virus.getText() == null) {
                virus.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
            if (contamination.getText() == null) {
                contamination.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
        }
        System.out.println(count);
        return (count != 0);
    }


    /**
     * Checks if all the fields are of the correct data type.
     *
     * @return whether it is true
     */
    private boolean correctDataType() {
        int count = 0;

        if (!isNumeric(longitude.getText())) {
            longitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            count++;
        }

        if (!isNumeric(latitude.getText())) {
            latitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            count++;
        }
        if (showConfirm.get()) {
            if (!isNumeric(virus.getText())) {
                virus.setStyle(
                        "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }

            if (!isNumeric(contamination.getText())) {
                contamination.setStyle(
                        "-fx-border-color: red ; -fx-border-width: 2px ;");
                count++;
            }
        }

        return (count == 0);
    }



    /**
     * helper method to see if a certain field
     * is populated with a numeric string
     *
     * @param str string to be examined
     * @return true if it is numeric
     */
    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);  
        } catch (NumberFormatException nfe) {  
            return false;  
        }
        return true;  
    }

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }


    /**
     * Validates latitude and longitude values
     * to make sure they are in range.
     *
     * @param lat String value for latitude
     * @param lon String value for longitude
     * @return boolean value representing if valid data was given.
     */
    private Boolean validateLatAndLon(String lat, String lon) {
        int latVal = Integer.parseInt(lat);
        int lonVal = Integer.parseInt(lon);

        if ((latVal >= -90) && (latVal <= 90)
            && (lonVal >= -180) && (lonVal <= 180)) {
            latitude.setStyle("-fx-border-width: 0px ;");
            longitude.setStyle("-fx-border-width: 0px ;");

            return true;

        } else {
            if ((latVal < -90) || (latVal > 90)) {
                latitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            }
            if ((lonVal < -180) || (lonVal > 180)) {
                longitude.setStyle(
                    "-fx-border-color: red ; -fx-border-width: 2px ;");
            }

            return false;
        }
    }
}
