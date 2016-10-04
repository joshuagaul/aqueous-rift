package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import java.io.IOException;

import main.MainFXApplication;
import coredata.UserDataObject;
import model.User;

public class LoginController {
    /** a link back to the main application class */
    private MainFXApplication mainApplication;
    private UserDataObject userDAO = UserDataObject.getInstance();

    @FXML
    private Button createAccount;
    @FXML
    private Button back;
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
       } else if (event.getSource() == back){
           mainApplication.showWelcomeScreen();
       }else if (event.getSource() == login) {
          if (checkValid()) {
               mainApplication.showAppScreen();
          } else {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("ConnectH2O");
               alert.setHeaderText("Login Failed");
               alert.showAndWait();
          }

           // allows log in if ----    if(checkValid());
       }
    }

    /**
     * Check if username/password are correct
     */
    @FXML
    protected boolean checkValid () {
        if (username.getText().equals("user") && password.getText().equals("pass")) {
            return true;
        }
               /*
        String user = username.getText();
        if (userDAO.userExists(user)) {
            //Check login information
            User queriedUser = userDAO.getUser(user);
            System.out.println(queriedUser.getPassword());
            return queriedUser.getPassword().equals(password.getText());
        }*/ else {
            error.setText("Invalid username or password. Please try again.");
            return false;
        }
    }


    // Give the controller access to the main app.
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
