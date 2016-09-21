package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.MainFXApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.io.IOException;

public class LoginController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;

    @FXML
    private Button createAccount;

    @FXML
    private Button findpassword;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Text error;


    /**
     * Button handler for login page.
     * Clicking "Create an Account" will redirect to Register page.
     * Clicking "Forgot My Password" will redirect to Finding password page.
     * Clicking "Login" will redirect to main application.
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
       if(event.getSource() == createAccount) {
           mainApplication.showRegisterScreen();
       } else if (event.getSource() == findpassword) {
           mainApplication.showFindPasswordScreen();
       } else if (event.getSource() == login) {


           //temporary alert
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("ConnectH2O");
           alert.setHeaderText("Login");
           alert.setContentText("This feature has not been implemented yet.");
           alert.showAndWait();

           // allows log in if ----    if(checkValid());
       }
    }

    /**
     * Check if username/password are correct
     */

    protected boolean checkValid () {
        if (username.getText().equals("stored username") && password.getText().equals("stored pw")){
            return true;
        } else{
            error.setText("Invalid username or password. Please try again.");
            return false;
        }
    }


    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
