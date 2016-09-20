package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public Parent root;
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(LoginScreen.class.getResource("../view/RegisterScreen.fxml"));
        stage.setTitle("ConnectH2O");
        stage.setScene(new Scene(root, 720, 512));
        stage.show();
    }

}
