package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import main.MainFXApplication;

/**
 * Controller class for splash screen page.
 */
public class SplashScreenController implements IController {
    private MainFXApplication mainApplication;
    @FXML private StackPane pane;


    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
