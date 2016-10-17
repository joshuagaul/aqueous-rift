package main;

import controller.IController;
import controller.MainScreenController;
import controller.MenuBarController;
import controller.CreateReportController;
import controller.EditProfileController;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import classes.User;
import model.DataManager;
import model.UserDataObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs the main application and and switches the pages.
 **/
public class MainFXApplication extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");
    private static User currentUser;
    private static String currentUsername;
    private static MenuBarController menuBarController;
    private static MainScreenController mainScreenController;

    //the main container for the application window
    private Stage mainScreen;

    //the main layout for the main window
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showMainScreen();
    }

    /**
     * return a reference to the main window stage
     *
     * @return reference to main stage
     */
    public Stage getMainScreen() {
        return mainScreen;
    }

    /**
     * initialize the main screen which will also have other pages.
     *
     * @param mainScreen screen to be displayed.
     * @throws IOException throws an exception if fxml is not found.
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource("view/MenuBar.fxml"));
            rootLayout = loader.load();
            menuBarController = loader.getController();
            menuBarController.setMainApp(this);
            mainScreen.setTitle("Aqueous Rift");
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
            mainScreen.setResizable(false);
            mainScreen.sizeToScene();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find "
                    + "the fxml file for MenuBar!!");
            e.printStackTrace();
        }
    }

    /**
     * Helper method to show screen from given fxmlfilepath
     * @param fxmlFilePath relative path of fxml file
     * @param screenType   Type of app screen that the fxml file represents
     * @param location   where this screen will be displayed
     */
    private void showScreen(String fxmlFilePath,
                            String screenType, String location) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource(fxmlFilePath));
            Pane showPage = loader.load();
            animate(showPage);
            if (location.equals("LEFT")) {
                rootLayout.setLeft(showPage);
            } else if (location.equals("CENTER")) {
                rootLayout.setCenter(showPage);
            } else if (location.equals("RIGHT")) {
                rootLayout.setRight(showPage);
            }
            IController controller = loader.getController();
            controller.setMainApp(this);
            if (controller instanceof EditProfileController) {
                EditProfileController c = (EditProfileController) (controller);
                c.populateUserInformation(currentUser, currentUsername);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find "
                + "the fxml file for " + screenType);
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to login page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showLoginScreen() {
        showScreen("view/Login.fxml", "LoginScreen", "RIGHT");
    }

    /**
     * sets the screen to registration page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showRegisterScreen() {
        showScreen("view/Register.fxml", "RegisterScreen", "RIGHT");
    }

    /**
     * sets the screen to find password page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showFindPasswordScreen() {
        showScreen("view/FindPassword.fxml", "PasswordScreen", "RIGHT");
    }

    /**
     * sets the screen to welcome screen.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showMainScreen() {
        showScreen("view/MainScreen.fxml", "MainScreen", "RIGHT");
    }

    /**
     * sets the screen to editing profile page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showEditProfileScreen() {
        showScreen("view/EditProfile.fxml", "EditProfileScreen", "RIGHT");
    }


    /**
     * sets the screen to reporting water source page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showReportScreen() {
        showScreen("view/CreateReport.fxml",
                "ReportWaterSourceScreen", "RIGHT");
    }

    /**
     * Passes login information from LoginController to MenuBarController.
     * Main Application calls a controller, not ideal but only solution I
     * could think of.  And this prevents controllers from calling each other
     * which would be even worse.
     */
    public void updateMenuBar() {
        menuBarController.userLogsIn(currentUser);
    }

    /**
     * update the buttons for main screen based on the user type.
     */
    public void checkAuthority() {
        if (currentUser.getUserType().equals("Manager")
                || currentUser.getUserType().equals("Worker")
                || currentUser.getUserType().equals("Admin")) {
            MainScreenController.setAuthority(true);
            CreateReportController.setAuthority(true);
        } else {
            MainScreenController.setAuthority(false);
            CreateReportController.setAuthority(false);
        }
    }

    /**
     * gets the current userinfo
     * @return current User using the app
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * sets the current user when logged in
     * @param u user currently using the app
     */
    public void setCurrentUser(User u) {
        currentUser = u;
    }

    /**
     * Gets the username of the user logged into the application.
     * @return current username
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Sets the username of the user logged into the application.
     * @param username username who is currently using the app
     */
    public void setCurrentUsername(String username) {
        currentUsername = username;
    }

    /**
     * @return border pane of the main screen
     */
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * Slide in the workbench as screen is switched.
     * @param pane pane to be animated
     */
    private void animate(Pane pane) {
        TranslateTransition transition
                = new TranslateTransition(Duration.seconds(0.3), pane);
        transition.setFromX(400);
        transition.setToX(0);
        transition.play();
    }

    /**
     * runs the program.
     * @param args runs the program.
     */
    public static void main(String[] args) {
        //Instantiate/Initialize singletons and static classes
        DataManager.initializeFireBase();
        UserDataObject userDAO = UserDataObject.getInstance();
        launch(args);
    }
}
