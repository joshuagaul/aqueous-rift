package controller;
  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

public class LoginController {
    @FXML 
    private Text error;
    @FXML 
    protected void handleInvalidLogin(ActionEvent event) {
        error.setText("Invalid username or password. Please try again.");
    }

    @FXML
    protected void handleUsernameInUse(ActionEvent event) {
        error.setText("Username is already in use. Please choose another username.");
    }

    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

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
        alert.setContentText("someone update this: This program is made by: \n\n Graham McAllister \n AhJin Noh \nJoshua Gaul \nKwangHee Kim \nAakanksha Patel");
        alert.showAndWait();

    }

}
