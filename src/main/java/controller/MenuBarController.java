package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Optional;
import main.MainFXApplication;
import classes.UserType;
import classes.User;



/**
 * Controller class for main border pane. Will contain file, help, etc
 */
public class MenuBarController implements IController {

    private BooleanProperty userLoggedIn = new SimpleBooleanProperty(false);
    private MainFXApplication mainApplication;
    private StringProperty username = new SimpleStringProperty("Hello, Guest");
    private StringProperty userType
            = new SimpleStringProperty(UserType.GeneralUser.toString());

    @FXML
    private Menu hello;

    @FXML
    private Menu login;

    @FXML
    private Menu userOptions;

    @FXML
    private Menu reports;

    @FXML
    private Menu home;

    @FXML
    private MenuItem bottled;
    @FXML
    private MenuItem well;
    @FXML
    private MenuItem stream;
    @FXML
    private MenuItem lake;
    @FXML
    private MenuItem spring;
    @FXML
    private MenuItem other;

    @FXML
    private MenuItem waste;
    @FXML
    private MenuItem treatableClear;
    @FXML
    private MenuItem treatableMuddy;
    @FXML
    private MenuItem potable;

    /**
     * Initializes variable bindings and login handler
     */
    @FXML
    private void initialize() {
        //Help is a static Menu
        userOptions.visibleProperty().bind(userLoggedIn);
        reports.setText("Reports");
        login.visibleProperty().bind(userLoggedIn.not());
        hello.textProperty().bind(username);
        userOptions.textProperty().bind(userType);
        Label loginLabel = new Label("Login");
        loginLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mainApplication.showLoginScreen();
            }
        });
        login.setGraphic(loginLabel);
        Label homeLabel = new Label("Home");
        homeLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mainApplication.showMap();
                mainApplication.showMainScreen();
            }
        });
        home.setGraphic(homeLabel);

    }

    /**
     * Helper method to check if a user is logged into the application.
     * @return True if a user is logged in, False otherwise.
     */
    private boolean checkUserLoggedIn() {
        return mainApplication.getCurrentUser() != null;
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Logout the user and show the menu as a guest
     */
    @FXML
    private void handleLogoutMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Logout");
        alert.setHeaderText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainApplication.showMap();
            mainApplication.showMainScreen();
            mainApplication.setCurrentUser(null);
            mainApplication.setCurrentUsername("");
            username.set("Hello, Guest");
            userLoggedIn.set(false);
            MainScreenController.setAuthority(false);
            MainScreenController.setLoggedIn(false);
            CreateReportController.setAuthority(false);
            ViewAllReportsController.setLoggedIn(false);
            ViewAllReportsController.setAuthority(false);
        }
    }

    /**
     * Lets the user edit profile
     */
    @FXML
    private void handleEditMenu() {
        mainApplication.showMap();
        mainApplication.showEditProfileScreen();
    }

    /**
     * filters by water type
     *
     * @param event clic    king the water type
     */
    @FXML
    private void handleWaterType(ActionEvent event) {
        MapController.setWaterType("null");
        if (event.getSource() == bottled) {
            MapController.setWaterType("Bottled");
        } else if (event.getSource() == well) {
            MapController.setWaterType("Well");
        } else if (event.getSource() == stream) {
            MapController.setWaterType("Stream");
        } else if (event.getSource() == lake) {
            MapController.setWaterType("Lake");
        } else if (event.getSource() == spring) {
            MapController.setWaterType("Spring");
        } else if (event.getSource() == other) {
            MapController.setWaterType("Other");
        }
        MapController.setWaterCondition("null");
        MapController.setAllPins("null");
        mainApplication.showMap();
        mainApplication.showMainScreen();
    }

    /**
     * filters by water condition
     *
     * @param event clicking the condition of the water
     */
    @FXML
    private void handleWaterCondition(ActionEvent event) {
        MapController.setWaterCondition("null");
        if (event.getSource() == waste) {
            MapController.setWaterCondition("Waste");
        } else if (event.getSource() == treatableClear) {
            MapController.setWaterCondition("Treatable_Clear");
        } else if (event.getSource() == treatableMuddy) {
            MapController.setWaterCondition("Treatable_Muddy");
        } else if (event.getSource() == potable) {
            MapController.setWaterCondition("Potable");
        }
        MapController.setWaterType("null");
        MapController.setAllPins("null");
        mainApplication.showMap();
        mainApplication.showMainScreen();
    }

    /**
     * Show all water source report pins on the map.
     */
    @FXML
    private void handleViewAllPins() {
        MapController.setAllPins("All");
        MapController.setWaterType("null");
        MapController.setWaterCondition("null");
        mainApplication.showMap();
        mainApplication.showMainScreen();
    }

    /**
     * Lets the user edit profile
     */
    @FXML
    private void handleViewAllMenu() {
        mainApplication.showViewAllReportsScreen();
    }



    /**
     * About menu item event handler.
     * Provide information on our project, version, etc
     */
    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aqueous Rift");
        alert.setHeaderText("About");
        alert.setContentText("Update : This is a project for CS2340.");
        alert.showAndWait();
    }

    /**
     * Contact menu item event handler.
     * Provide credit and contact information of developers.
     */
    @FXML
    private void handleContactMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aqueous Rift");
        alert.setHeaderText("Contact");
        alert.setContentText("someone update this: This program is made by: "
                + "\n\nGraham McAllister\nAhJin Noh\nJoshua Gaul"
                + "\nKwangHee Kim\nAakanksha Patel");
        alert.showAndWait();
    }

    /**
     * Changes the MenuBar when a user logs in.
     *
     * @param user User that logs into the application
     */
    public void userLogsIn(User user) {
        username.set("Hello, " + mainApplication.getCurrentUsername());
        userLoggedIn.set(true);
        userType.set(user.getUserType());
    }
}
