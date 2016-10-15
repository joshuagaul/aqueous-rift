package controller;

import javafx.scene.control.ButtonType;
import main.MainFXApplication;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import java.util.Optional;

/**
 * Controller class for main border pane. Will contain file, help, etc
 */
public class MenuBarController implements IController {

    private MainFXApplication mainApplication;

    @FXML
    private Menu userOptions;

    @FXML
    private Menu help;

    @FXML
    private MenuBar

    @FXML
    private void initialize() {
        if (checkUserLoggedIn()) {
            User currentUser = mainApplication.getCurrentUser();
            String currentUsername = mainApplication.getCurrentUsername();
            fillDynamicElements(currentUser, currentUsername);
        } else {
            fillDynamicElements(null, "Guest");
        }
        //Fill in current user information
        username.setText(currentUsername);
        usertype.setItems(userType);
        usertype.setValue((UserType.GeneralUser));
        prefix.getItems().setAll("Mr", "Ms", "Mrs");
    }

    /**
     * Helper method to check if a user is logged into the application.
     * @return True if a user is logged in, False otherwise.
     */
    private boolean checkUserLoggedIn() {
        return mainApplication.getCurrentUser != null;
    }

    /**
     * Helper method to dynamically populate the Menu and Menu options.
     * @param  user User logged in
     * @param  uName username of the User logged in
     */
    private void fillDynamicElements(User user, String uName) {

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
            //TODO show the app as a guest
            mainApplication.showMainScreen();
        }
    }

    /**
     * Lets the user edit profile
     */
    @FXML
    private void handleEditMenu() {
        mainApplication.showEditProfileScreen();
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
}
