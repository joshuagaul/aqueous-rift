package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import main.MainFXApplication;
import model.ReportDataObject;
import classes.WaterReport;
import classes.WaterSourceReport;


/**
 * Created by ahjin on 10/17/2016.
 */
public class ViewAllReportsController implements IController {

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

    // @FXML
    // private TableColumn<WaterSourceReport, String> virus;
    //
    // @FXML
    // private TableColumn<WaterSourceReport, String> contamination;
    //
    @FXML
    private TableColumn<WaterReport, String> user;

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
        TableColumn<WaterReport, String> latitude  = new TableColumn<>("Latitude");
        TableColumn<WaterReport, String> longitude  = new TableColumn<>("Longitude");
        TableColumn<WaterReport, String> date  = new TableColumn<>("Date");
        TableColumn<WaterReport, String> type  = new TableColumn<>("Type");
        TableColumn<WaterReport, String> condition  = new TableColumn<>("Condition");
        ReportDataObject reportDAO = ReportDataObject.getInstance();

        //TODO SHOW LONGITUDE AND LATITUDE
        latitude.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("location"));
        longitude.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("location"));
        date.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("date"));
        type.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("type"));
        condition.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("condition"));
        // date.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("virus"));
        //location.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("contamination"));
        user.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("reporterId"));
        System.out.println(parseReportList(reportDAO));
        reportView.getColumns().addAll(latitude, longitude, date, type, condition);
        reportView.getItems().setAll(parseReportList(reportDAO));

    }

    private List<WaterReport> parseReportList(ReportDataObject reportDAO) {
        System.out.println(reportDAO.getAllCandidateReports().values());
        return new ArrayList<WaterReport>(reportDAO.getAllCandidateReports().values());
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
