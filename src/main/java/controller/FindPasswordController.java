package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import main.MainFXApplication;

import java.util.Optional;

/**
 * Controller class for finding password page.
 */
public class FindPasswordController implements IController {
    private MainFXApplication mainApplication;
    private BooleanProperty showSecurityQuestion= new SimpleBooleanProperty();

    @FXML private Button cancel;
    @FXML private Label usernameLabel;
    @FXML private Label lastNameLabel;
    @FXML private TextField username;
    @FXML private TextField lastName;
    @FXML private Button viaSecurityQuestion;
    @FXML private Button viaEmail;
    @FXML private GridPane viaQuestionGrid;
    @FXML private Button securityOK;
    @FXML private Button securityCancel;
    @FXML private Text question;
    @FXML private TextField answer;

    @FXML
    private void initialize() {
        viaQuestionGrid.visibleProperty().bind(showSecurityQuestion);
        usernameLabel.visibleProperty().bind(showSecurityQuestion.not());
        lastNameLabel.visibleProperty().bind(showSecurityQuestion.not());
        username.visibleProperty().bind(showSecurityQuestion.not());
        lastName.visibleProperty().bind(showSecurityQuestion.not());
        cancel.visibleProperty().bind(showSecurityQuestion.not());
        viaEmail.visibleProperty().bind(showSecurityQuestion.not());
        viaSecurityQuestion.visibleProperty().bind(showSecurityQuestion.not());
    }

    /**
     * Button handler for find password page.
     * Clicking OK button will compare user input with stored model.
     * If the input matches the model, user's password will be [...]
     * //TODO password will be displayed or emailed?
     * Clicking Cancel button will redirect to the welcome page.
     *
     * @param event the button user clicks.
     */

    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == cancel) {
            mainApplication.showLoginScreen();
        } else if (event.getSource() == viaSecurityQuestion) {
            showSecurityQuestion.setValue(true);
            question.setText("question to be displayed");
            //TODO then set the text for question
        } else if (event.getSource() == viaEmail) {
            //TODO send email to the user and notify the user
            //TODO display partial email for users who may not remember the email
            //change the alerttext
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email sent");
            alert.setHeaderText("New password has been sent via your email : a***0@mail.com");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                mainApplication.showLoginScreen();
            }
        }
    }

    @FXML
    private void handleSecurityButton(ActionEvent event) {
        if (event.getSource() == cancel) {
            mainApplication.showLoginScreen();
        } else if (event.getSource() == securityCancel) {
            showSecurityQuestion.setValue(false);
        } else if (event.getSource() == securityOK) {
            //TODO implement this
            //if user gets the answer wrong too many times,
            //block the user
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
