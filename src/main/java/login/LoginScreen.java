package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public Parent root;
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(LoginScreen.class.getResource("../view/LoginScreen.fxml"));
        stage.setTitle("Login Screen");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}
