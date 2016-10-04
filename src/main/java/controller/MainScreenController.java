package controller;

import main.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 * The controller for the root/main window
 *
 */
public class MainScreenController {
    /** reference back to mainApplication if needed */
    private MainFXApplication mainApplication;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

    /**
     * About menu item event handler
     */

    @FXML
    private void handleAboutMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ConnectH2O");
        alert.setHeaderText("About");
        alert.setContentText("Update : This is a project for CS2340. Someone update this please");
        alert.showAndWait();

    }

    @FXML
    private void handleContactMenu() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ConnectH2O");
        alert.setHeaderText("Contact");
        alert.setContentText("someone update this: This program is made by: \n\nGraham McAllister\nAhJin Noh\nJoshua Gaul\nKwangHee Kim\nAakanksha Patel");
        alert.showAndWait();

    }


}
