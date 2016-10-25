package controller;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private MenuBar header;

    @FXML
    private Menu hello;

    @FXML
    private Menu login;

    @FXML
    private Menu userOptions;

    @FXML
    private Menu reports;

    @FXML
    private Menu help;

    /**
     * Initializes variable bindings and login handler
     */
    @FXML
    private void initialize() {
        //Help is a static Menu
        userOptions.visibleProperty().bind(userLoggedIn);
        reports.visibleProperty().bind(userLoggedIn);
        login.visibleProperty().bind(userLoggedIn.not());
        hello.textProperty().bind(username);
        userOptions.textProperty().bind(userType);
        //TODO Make the menu options in userOptions dynamic based on userType
        Label loginLabel = new Label("Login");
        loginLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mainApplication.showLoginScreen();
            }
        });
        login.setGraphic(loginLabel);
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
     * go back to the main screen
     */
    @FXML
    private void handleMainMenu() {
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
        reports.setText("Reports");
    }
}
