package main;

import controller.LoginController;
import controller.MainScreenController;
import controller.RegisterController;
import controller.FindPasswordController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.User;
import coredata.Connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainFXApplication extends Application {
    //the java logger for this class
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");

    //the main container for the application window
    private Stage mainScreen;

    //the main layout for the main window
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showLoginScreen();
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
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getClassLoader().getResource("view/MainScreen.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("ConnectH2O");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();

            mainScreen.setResizable(false);
            mainScreen.sizeToScene();


        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * Shows login page - this will be the default screen when the app starts.
     * More details in LoginController.java
     *
     */

    public void showLoginScreen() {
        try {
            // Load loginPage
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getClassLoader().getResource("view/LoginScreen.fxml"));
            AnchorPane showLoginScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(showLoginScreen);

            // Give the controller access to the main app.
            LoginController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for LoginScreen!!");
            e.printStackTrace();
        }
    }

    /**
     * Shows page for creating a new account.
     * More details in RegisterController.java
     */
    public void showRegisterScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getClassLoader().getResource("view/RegisterScreen.fxml"));
            AnchorPane showRegisterPage = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(showRegisterPage);

            // Give the controller access to the main app.
            RegisterController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }

    public void showFindPasswordScreen() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getClassLoader().getResource("view/FindPasswordScreen.fxml"));
            AnchorPane showFindPasswordScreen = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(showFindPasswordScreen);

            // Give the controller access to the main app.
            FindPasswordController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }

    public void showMainScreen() {
        //
    }

    public static void main(String[] args) {
        Connection con = new Connection();
        con.sendTest("Hello, world!");
        launch(args);
    }
}
