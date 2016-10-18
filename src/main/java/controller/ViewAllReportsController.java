package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import main.MainFXApplication;

import java.io.IOException;

/**
 * Created by ahjin on 10/17/2016.
 */
public class ViewAllReportsController implements IController {

    private MainFXApplication mainApplication;
    @FXML private static StackPane pane;
    @FXML private Button back;
    @FXML private Button submit;

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
        if (event.getSource() == back) {
            mainApplication.showMap();
            mainApplication.showMainScreen();
        } else if (event.getSource() == submit) {
            mainApplication.showMap();
            mainApplication.showReportScreen();
        }
    }

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     * */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }
}


