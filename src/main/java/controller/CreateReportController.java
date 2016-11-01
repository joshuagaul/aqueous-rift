package controller;

/**
 * Created by ahjin on 10/7/2016.
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

import classes.State;
import classes.WaterCondition;
import classes.WaterType;
import classes.UserType;
import classes.Location;
import classes.WaterSourceReport;
import classes.WaterPurityReport;

import main.MainFXApplication;
import java.io.IOException;
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
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField zipCode;

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

    /**
     * Initializes items (combobox's)
     */
    @FXML
    private void initialize() {
        //Populate static combobox data
        waterType.getItems().setAll(WaterType.values());
        waterCondition.getItems().setAll(WaterCondition.values());
        state.getItems().setAll(State.values());
        //Bind event handler and set binding variables
        userType.set(UserType.GeneralUser);
        ppm1.visibleProperty().bind(showConfirm);
        ppm2.visibleProperty().bind(showConfirm);
        virusLabel.visibleProperty().bind(showConfirm);
        contaminationLabel.visibleProperty().bind(showConfirm);
        virus.visibleProperty().bind(showConfirm);
        contamination.visibleProperty().bind(showConfirm);
        confirmButton.visibleProperty().bind(isAuthorized);
    }

    /**
     * set the visibility of the textfields based on the user type.
     * @param set true if the user is authorized (worker, admin, manager).
     *            false if the user is not logged in or a general user.
     */
    public static void setAuthority(boolean set) {
        isAuthorized.set(set);
    }

    /**
      *Helper method to convert the current User type to a boolean property
      *for binding. Checks if the User is a GeneralUser.
      *@return BooleanProperty to bind to
     */
    /*

    private BooleanProperty checkUser() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals(UserType.GeneralUser));
        return res;
    }
    */

    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is an Admin.
     *
     * @return BooleanProperty to bind to
     **/
    /*
    private BooleanProperty checkAdmin() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Admin"));
        return res;
    }
    */
    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is a Worker.
     *
     * @return BooleanProperty to bind to
     */
    /*
    private BooleanProperty checkWorker() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Worker"));
        return res;
    }
    */
    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is a Manager.
     *
     * @return BooleanProperty to bind to
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
     * @throws IOException throws an exception if fxml file is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
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
                System.out.println(showConfirm.get());
            } else {
                //creating purity report
                purityReport.setValue(true);
                confirmButton.setId("button-delete");
                showConfirm.setValue(true);
                confirmButton.setText("Back to Source Report");
                System.out.println(showConfirm.get());
            }

        } else if (event.getSource() == submit) {
            ReportDataObject reportDAO = ReportDataObject.getInstance();
            String reporterId = mainApplication.getCurrentUsername();
            Location loc = new Location("0", "0");

            if (validateLatAndLon(latitude.getText(), longitude.getText())) {
                try {
                    loc.setLongitude(longitude.getText());
                    loc.setLatitude(latitude.getText());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            Date date = new Date();
            WaterType type = waterType.getValue();
            WaterCondition condition = waterCondition.getValue();
            if (!purityReport.get()) {
                WaterSourceReport report = new WaterSourceReport(reporterId,
                        loc, type, condition, date);
                reportDAO.addSourceReport(report);
            } else {
                WaterPurityReport report = new WaterPurityReport(reporterId,
                        date, loc, condition,
                        Double.parseDouble(virus.getText()),
                        Double.parseDouble(contamination.getText()));
                reportDAO.addPurityReport(report);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Report Submitted");
            alert.setHeaderText("Your report has been submitted.");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                mainApplication.showMap();
                mainApplication.showMainScreen();
            }
        }
    }

    /**
     * Validates the user Report inputs. (Checking null fields for now.)
     * @return An alert message describing the input errors.
     */
    private String validateWaterReport() {
        StringBuilder alertMessage = new StringBuilder();
        if (waterCondition.getValue() == null) {
            alertMessage.append("Water Condition\n");
        }
        if (waterType.getValue() == null) {
            alertMessage.append("Water Type\n");
        }
        if (street.getText().length() == 0) {
            alertMessage.append("Street\n");
        }
        if (city.getText().length() == 0) {
            alertMessage.append("City\n");
        }
        if (state.getValue() == null) {
            alertMessage.append("State\n");
        }
        if (zipCode.getText().length() == 0) {
            alertMessage.append("Zip Code\n");
        }
        if (isAuthorized.getValue()) {
            if (virus.getText().length() == 0) {
                alertMessage.append("Virus\n");
            }
            if (contamination.getText().length() == 0) {
                alertMessage.append("Contamination\n");
            }
        }
        return alertMessage.toString();
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
