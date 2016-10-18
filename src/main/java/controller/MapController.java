package controller;

import main.MainFXApplication;

/**
 * Created by ahjin on 10/17/2016.
 * Controller class for login page.
 */
public class MapController implements IController {
    private MainFXApplication mainApplication;

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}

