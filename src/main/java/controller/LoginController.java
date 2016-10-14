package controller;

import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import main.MainFXApplication;
import coredata.UserDataObject;
import model.User;

/**
 * Controller class for login page.
 */
public class LoginController implements IController {
    private MainFXApplication mainApplication;
    private UserDataObject userDAO = UserDataObject.getInstance();

    @FXML
    private Hyperlink createAccount;

    @FXML
    private Button cancel;

    @FXML
    private Hyperlink forgotPassword;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;


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
        if (event.getSource() == cancel) {
            mainApplication.showMainScreen();
        } else if (event.getSource() == login) {
            if (checkValid()) {
                //TODO show main screen logged as a user.
                mainApplication.showReportScreen();
            }
        }
    }


    /**
     * Link handler for login page.
     * Clicking "Create an Account" will redirect to register page.
     * Clicking "Forgot My Password" will redirect to find password page.
     *
     * @throws IOException throws an exception when fxml is not found.
     * @param event the button user clicks.
     */
    @FXML
    private void handleLinkClicked(ActionEvent event) throws IOException {
        if (event.getSource() == createAccount) {
            mainApplication.showRegisterScreen();
        } else if (event.getSource() == forgotPassword) {
            mainApplication.showFindPasswordScreen();
        }
    }

    /**
     * Checks if provided input matches stored data.
     * Will display an error message if input doesn't match.
     * This will check the info up to 3 times before user gets blocked.
     *
     * @return boolean true if input matches.
     * TODO blocking the user
     * TODO check null text fields
     * TODO keep track of 3 attempts log in
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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aqueous Rift");
            alert.setHeaderText("Login Failed. # out of 3 attempts.");
            alert.showAndWait();
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
