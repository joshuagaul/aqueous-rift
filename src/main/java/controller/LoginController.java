package controller;
  
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LoginController {
    @FXML 
    private Text actiontarget;
    @FXML 
    protected void handleSignInButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
    @FXML 
    protected void handleSignUpButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }
}
