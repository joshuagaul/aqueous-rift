package main;

import controller.AppScreenController;
import controller.EditProfileController;
import controller.FindPasswordController;
import controller.LoginController;
import controller.MainScreenController;
import controller.RegisterController;
import controller.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import coredata.ConnectionFactory;
import coredata.UserDataObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs the main application and and switches the pages.
 **/
public class MainFXApplication extends Application {
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");
    private static User currentUser;

    //the main container for the application window
    private Stage mainScreen;

    //the main layout for the main window
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showWelcomeScreen();
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
                    getClassLoader().getResource("view/MainScreen.fxml"));
            rootLayout = loader.load();
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            // Set the Main App title
            mainScreen.setTitle("Aqueous Rift");
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
            mainScreen.setResizable(false);
            mainScreen.sizeToScene();
        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find "
                    + "the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to login page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource("view/LoginScreen.fxml"));
            AnchorPane showLoginScreen = loader.load();
            rootLayout.setCenter(showLoginScreen);
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find "
                    + "the fxml file for LoginScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to registration page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showRegisterScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource("view/RegisterScreen.fxml"));
            AnchorPane showRegisterPage = loader.load();
            rootLayout.setCenter(showRegisterPage);
            RegisterController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find "
                    + "the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to find password page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showFindPasswordScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource(
                            "view/FindPasswordScreen.fxml"));
            AnchorPane showFindPasswordScreen = loader.load();
            rootLayout.setCenter(showFindPasswordScreen);
            FindPasswordController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find"
                    + "the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to welcome screen.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showWelcomeScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource("view/WelcomeScreen.fxml"));
            AnchorPane showWelcomeScreen = loader.load();
            rootLayout.setCenter(showWelcomeScreen);
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the"
                    + "fxml file for WelcomeScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to main app page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showAppScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource("view/AppScreen.fxml"));
            AnchorPane showAppScreen = loader.load();
            rootLayout.setCenter(showAppScreen);
            AppScreenController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to "
                    + "find the fxml file for AppScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * sets the screen to editing profile page.
     *
     * @throws IOException throws an exception if fxml is not found.
     */
    public void showEditProfileScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.
                    getClassLoader().getResource(
                            "view/EditProfileScreen.fxml"));
            AnchorPane showEditProfileScreen = loader.load();
            rootLayout.setCenter(showEditProfileScreen);
            EditProfileController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find "
                    + "the fxml file for EditProfileScreen!!");
            e.printStackTrace();
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
     * @return border pane of the main screen
     */
    public BorderPane getRootLayout() {
        return rootLayout;
    }

    /**
     * runs the program.
     * @param args runs the program.
     */

    public static void main(String[] args) {
        //Instantiate/Initialize singletons and static classes
        ConnectionFactory.initializeFireBase();
        UserDataObject userDAO = UserDataObject.getInstance();
        launch(args);
    }
}
