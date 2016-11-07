package controller;

import classes.WaterSourceReport;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Optional;

import javafx.scene.text.Text;
import main.MainFXApplication;
import model.ReportDataObject;
import classes.WaterReport;

/**
 * Created by ahjin on 10/17/2016.
 */
public class ViewAllReportsController implements IController {
    private static BooleanProperty isAuthorized
            = new SimpleBooleanProperty(false);
    private static BooleanProperty isLoggedIn
            = new SimpleBooleanProperty(false);
    private static BooleanProperty showPurityReports
            = new SimpleBooleanProperty(false);
    private MainFXApplication mainApplication;
    @FXML private static StackPane pane;
    @FXML private Button back;
    @FXML private Button submit;
    @FXML private Text header;
    @FXML
    private TableView<WaterReport> reportView;

    @FXML
    private Button delete;

    @FXML
    private Button switchTable;

    private TableColumn<WaterReport, String> user
            = new TableColumn<>("Username");
    private TableColumn<WaterReport, String> location
            = new TableColumn<>("Location");
    private TableColumn<WaterReport, String> date
            = new TableColumn<>("Date");
    private TableColumn<WaterReport, String> type
            = new TableColumn<>("Type");
    private TableColumn<WaterReport, String> condition
            = new TableColumn<>("Condition");
    private TableColumn<WaterReport, String> contamination
            = new TableColumn<>("Contamination (ppm)");
    private TableColumn<WaterReport, String> virus
            = new TableColumn<>("Virus (ppm)");
    private TableColumn<WaterReport, String> overallcondition
            = new TableColumn<>("Overall Condition");
    private ReportDataObject reportDAO;

    /**
     * Loads report data
     */
    @FXML
    private void initialize() {
        reportDAO = ReportDataObject.getInstance();
        delete.visibleProperty().bind(isAuthorized);
        setReportView(false);
        user.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("reporterId"));
        location.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("location"));
        date.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("date"));
        type.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("type"));
        condition.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("condition"));
        virus.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>("virusPPM"));
        contamination.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>(
                        "contaminantPPM"));
        overallcondition.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>(
                        "overallCondition"));
        switchViews();
    }

    /**
     * Button handler for view reports page
     *
     * @throws IOException throws an exception when fxml is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            MapController.setAllPins("All");
            mainApplication.showMap();
            mainApplication.showMainScreen();
        } else if (event.getSource() == submit) {
            if (isLoggedIn.get()) {
                mainApplication.showMap();
                mainApplication.showReportScreen();
            } else {
                mainApplication.showMap();
                mainApplication.showLoginScreen();
            }
        } else if (event.getSource() == switchTable) {
            if (showPurityReports.get()) {
                setReportView(false);
                header.setText("SOURCE REPORTS");
                switchTable.setText("View Confirmed Reports");
            } else {
                setReportView(true);
                header.setText("CONFIRMED REPORTS");
                switchTable.setText("View Source Reports");
            }
            switchViews();
        } else if (event.getSource() == delete) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete this report");
            alert.setHeaderText(
                    "Are you sure you want to delete this report?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ReportDataObject reportDAO = ReportDataObject.getInstance();
                WaterReport report = reportView.getSelectionModel().getSelectedItem();
                if (report instanceof WaterSourceReport) {
                    reportDAO.deleteSourceReport(report.getId());
                } else {
                    reportDAO.deletePurityReport(report.getId());
                }
                mainApplication.showViewAllReportsScreen();
            }



        }
    }

    /**
     * Updates the columns based on the view mode.
     *
     */
    private void switchViews() {
        reportView.getColumns().clear();
        if (isAuthorized.get() && showPurityReports.get()) {
            reportView.getColumns().addAll(user, location, date,
                    overallcondition, contamination, virus);
        } else if (showPurityReports.get()) {
            reportView.getColumns().addAll(user, location, date,
                    overallcondition);
        } else {
            reportView.getColumns().addAll(user, location, date, type,
                    condition);
        }
        reportView.getItems().setAll(parseReportList(reportDAO));
        reportView.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Parses the reportList from the given Report Data Object
     * @param  reportDAO Report Data Object to parse
     * @return           ArrayList of water reports
     */
    private List<WaterReport> parseReportList(ReportDataObject reportDAO) {
        if (showPurityReports.get()) {
            return new ArrayList<WaterReport>(
                    reportDAO.getAllPurityReports().values());
        } else {
            return new ArrayList<WaterReport>(
                    reportDAO.getAllSourceReports().values());
        }
    }

    /**
     * set the visibility of the buttons based on the user type.
     * @param set true if the user is authorized (worker, admin, manager).
     *            false if the user is not logged in or a general user.
     */
    public static void setAuthority(boolean set) {
        isAuthorized.set(set);
    }

    /**
     * set the functionality of the buttons based on the login status.
     * @param set true if the user is logged in.
     */
    public static void setLoggedIn(boolean set) {
        isLoggedIn.set(set);
    }

    /**
     * switches the view by clicking the button.
     * @param set true if purity reports are shown.
     */
    public static void setReportView(boolean set) {
        showPurityReports.setValue(set);
    }


    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     * */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }
}
