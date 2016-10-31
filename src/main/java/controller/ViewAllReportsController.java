package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import main.MainFXApplication;
import model.ReportDataObject;
import classes.WaterReport;
import classes.WaterSourceReport;
import classes.WaterPurityReport;

/**
 * Created by ahjin on 10/17/2016.
 */
public class ViewAllReportsController implements IController {
    private static BooleanProperty isAuthorized = new SimpleBooleanProperty(false);
    private MainFXApplication mainApplication;

    @FXML private static StackPane pane;
    @FXML private Button back;
    @FXML private Button submit;

    @FXML
    private TableView<WaterReport> reportView;

    // @FXML
    // private TableColumn<WaterSourceReport, String> num;
    //
    @FXML
    private TableColumn<WaterSourceReport, String> date;
    //
    // @FXML
    // private TableColumn<WaterSourceReport, String> location;
    //
    @FXML
    private TableColumn<WaterSourceReport, String> type;

    @FXML
    private TableColumn<WaterSourceReport, String> condition;

    /**
     * Button handler for login page.
     * Clicking "Login" should check validity of info and count login attempts.
     * Cllicking "Cancel" will redirect to main screen.
     *
     * @throws IOException throws an exception when fxml is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == back) {
            mainApplication.showMap();
            mainApplication.showMainScreen();
        } else if (event.getSource() == submit) {
            mainApplication.showMap();
            mainApplication.showReportScreen();
        }
    }

    /**
     * Loads report data
     */
    @FXML
    private void initialize() {
        TableColumn<WaterReport, String> reportType
                = new TableColumn<>("Report Type");
        TableColumn<WaterReport, String> user
                = new TableColumn<>("Username");
        TableColumn<WaterReport, String> location
            = new TableColumn<>("Location");
        TableColumn<WaterReport, String> date  
            = new TableColumn<>("Date");
        TableColumn<WaterReport, String> type  
            = new TableColumn<>("Type");
        TableColumn<WaterReport, String> condition  
            = new TableColumn<>("Condition");
        TableColumn<WaterReport, String> contamination
                = new TableColumn<>("Contamination");
        TableColumn<WaterReport, String> virus
                = new TableColumn<>("Virus");

        ReportDataObject reportDAO = ReportDataObject.getInstance();
/*
        reportType.setCellValueFactory(
                new PropertyValueFactory<WaterReport, String>(""));*/
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
        reportView.getColumns().addAll(reportType, user, location, date,
            type, condition);
        reportView.getItems().setAll(parseReportList(reportDAO));

    }

    /**
     * Parses the reportList from the given Report Data Object
     * @param  reportDAO Report Data Object to parse
     * @return           ArrayList of water reports
     */
    private List<WaterReport> parseReportList(ReportDataObject reportDAO) {
        return new ArrayList<WaterReport>(
                reportDAO.getAllSourceReports().values());
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
