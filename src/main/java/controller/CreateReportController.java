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
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import classes.State;
import classes.WaterCondition;
import classes.WaterType;
import classes.UserType;
import classes.Location;
import classes.WaterSourceReport;
import java.util.Optional;
import model.ReportDataObject;

/**
 * Controller class for reporting a water source
 *
 */
public class CreateReportController implements IController {


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
        ppm1.visibleProperty().bind(checkUser().not());
        ppm2.visibleProperty().bind(checkUser().not());
        virusLabel.visibleProperty().bind(checkUser().not());
        contaminationLabel.visibleProperty().bind(checkUser().not());
        virus.visibleProperty().bind(checkUser().not());
        contamination.visibleProperty().bind(checkUser().not());
    }

    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the User is a GeneralUser.
     *
     * @return BooleanProperty to bind to
     */
    private BooleanProperty checkUser() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals(UserType.GeneralUser));
        return res;
    }

    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is an Admin.
     *
     * @return BooleanProperty to bind to
     */
    private BooleanProperty checkAdmin() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Admin"));
        return res;
    }

    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is a Worker.
     *
     * @return BooleanProperty to bind to
     */
    private BooleanProperty checkWorker() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Worker"));
        return res;
    }

    /**
     * Helper method to convert the current User type to a boolean property
     * for binding. Checks if the user is a Manager.
     *
     * @return BooleanProperty to bind to
     */
    private BooleanProperty checkManager() {
        BooleanProperty res = new SimpleBooleanProperty();
        res.setValue(userType.get().equals("Manager"));
        return res;
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
            String alertMessage = validateWaterReport();
            if (alertMessage.length() != 0) {
                Alert emptyAlert = new Alert(Alert.AlertType.WARNING);
                emptyAlert.setTitle("Empty fields");
                emptyAlert.setContentText(alertMessage);
                emptyAlert.setHeaderText("Please fill out all "
                        + "the required fields.");
                emptyAlert.showAndWait();
            } else {
                //TODO validate the input
                // Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                // alert.setTitle("Confirm Submission");
                // alert.setHeaderText("Are you sure you want to "
                //         + "submit this report?");
                // Optional<ButtonType> result = alert.showAndWait();
                // if (result.get() == ButtonType.OK) {
                ReportDataObject reportDAO = ReportDataObject.getInstance();
                String reporterId = mainApplication.getCurrentUsername();
                //Hard-coded latitude and longitude so I don't alter UI
                //***Need to decide on how we enter location (or use both ways)
                Location loc = new Location("55.2", "65.0");
                String date = "10/15/2016";
                // try {
                //     String target = "Sat Oct 15 20:29:30 2016";
                //     DateFormat df = new SimpleDateFormat
                //  ("EEE MMM dd kk:mm:ss yyyy", Locale.ENGLISH);
                //     date =  df.parse(target).toString();
                // } catch (ParseException e) {
                //     System.out.println(e.getMessage());
                // }
                WaterSourceReport report = new WaterSourceReport(reporterId,
                        loc, WaterType.Bottled, WaterCondition.Potable, date);
                reportDAO.addCandidateReport(report);
                //TODO show a toast message that the report has been submitted

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Report Submitted");
                alert.setHeaderText("Your report has been submitted.");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    mainApplication.showMainScreen();
                }
            }
        }
    }

    /**
     * Validates the user Report inputs. (Checking null fields for now.)
     * @return An alert message describing the input errors.
     */
    private String validateWaterReport() {
        StringBuilder alertMessage = new StringBuilder();
        /*
        if (virus.getText().length() == 0) {
            alertMessage.append("Virus\n");
        }
        if (contamination.getText().length() == 0) {
            alertMessage.append("Contamination\n");
        }
        */
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

}
