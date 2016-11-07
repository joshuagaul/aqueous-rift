package controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import main.MainFXApplication;


/**
 * Created by ahjin on 11/6/2016.
 */
public class ViewHistoricalGraphController implements IController {
    private MainFXApplication mainApplication;
    private BooleanProperty viewByVirus
            = new SimpleBooleanProperty(false);
    private BooleanProperty viewByContaminant
            = new SimpleBooleanProperty(false);
    @FXML private ComboBox year = new ComboBox<>();
    @FXML private RadioButton virusButton;
    @FXML private RadioButton contaminantButton;
    @FXML private Button ok;
    @FXML private Button back;


    /**
     * Initializes variable bindings and login handler
    */

    @FXML
    private void initialize() {
        viewByVirus.setValue(virusButton.isSelected());
        viewByContaminant.setValue(contaminantButton.isSelected());
        //initialize year combobox
    }

    /**
     * switches the view of the graph.
     */
    @FXML
    private void setView() {
        if (virusButton.isSelected()) {
            System.out.println("view graph by virus");
        } else {
            System.out.println("view graph by contaminant");
        }
    }

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(MainFXApplication main) {
        mainApplication = main;
    }

    /**
     * Button handler for graph page.
     * Clicking "Back to Main" will redirect to main screen
     * Clicking "OK" will update the graph on left side
     *
     * @param event the button user clicks.
     */
    @FXML
    private void handleButtonClicked(ActionEvent event) {
        if (event.getSource() == ok) {
            System.out.println("update the graph.");
            //TODO update the graph
        } else if (event.getSource() == back) {
            mainApplication.showMap();
            mainApplication.showMainScreen();
        }
    }
}
