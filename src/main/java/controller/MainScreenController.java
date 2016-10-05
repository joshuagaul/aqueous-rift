package controller;

import main.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


/**
 * Controller class for main border pane. Will contain file, help, etc
 */
public class MainScreenController {
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
