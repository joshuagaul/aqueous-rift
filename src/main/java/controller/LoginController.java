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

/**
 * Controller class for login page.
 */
public class LoginController {
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
     * Clicking "Create an Account" will redirect to register page.
     * Clicking "Forgot My Password" will redirect to find password page.
     * Clicking "Login" will redirect to app page.
     * Cllicking "Back" will redirect to welcome screen.
     *
     * @throws IOException throws an exception when fxml is not found.
     * @param event the button user clicks.
     */
    @FXML
     private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == createAccount) {
            mainApplication.showRegisterScreen();
        } else if (event.getSource() == findpassword) {
            mainApplication.showFindPasswordScreen();
        } else if (event.getSource() == back) {
            mainApplication.showWelcomeScreen();
        } else if (event.getSource() == login) {
            if (checkValid()) {
                mainApplication.showAppScreen();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aqueous Rift");
                alert.setHeaderText("Login Failed");
                alert.showAndWait();
            }
        }
    }

    /**
     * Checks if provided input matches stored data.
     * Will display an error message if input doesn't match.
     * This will check the info up to 3 times before user gets blocked.
     *
     * @return boolean true if input matches.
     * TODO blocking the user
     */
    @FXML
    protected boolean checkValid() {
        String user = username.getText();
        if (userDAO.userExists(user)) {
            User queriedUser = userDAO.getUser(user);
            System.out.println(queriedUser.getPassword());
            if (queriedUser.getPassword().equals(password.getText())) {
                mainApplication.setCurrentUser(queriedUser);
                return true;
            }
            return false;
        } else {
            error.setText("Invalid username or password. Please try again.");
            return false;
        }
    }


    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
