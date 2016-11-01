package controller;

import classes.WaterPurityReport;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import main.MainFXApplication;
import model.ReportDataObject;
import classes.WaterSourceReport;
import netscape.javascript.JSObject;

public class MapController implements IController,
        MapComponentInitializedListener {


    private static StringProperty filterType = new SimpleStringProperty();
    private static StringProperty filterCondition = new SimpleStringProperty();
    private static StringProperty filterAll = new SimpleStringProperty();
    private InfoWindow opened = null;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    private MainFXApplication mainApplication;

    /**
     * Initializes the controller
     */
    @FXML
    public void initialize() {
        //Defaults to all pins
        filterAll.set("All");
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(35, 120))
                //.mapType(MapTypeIdEnum.TERRAIN)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(3);

        map = mapView.createMap(mapOptions);
        map.addUIEventHandler(map, UIEventType.click, (JSObject obj) -> {
            if (opened != null) {
                opened.close();
            }
            opened = null;
        });

        //Because the reportDAO loads the reports asynchronously
        //  must pause execution to guarantee loading
        //  TODO think about other solutions to fix this
        //      Maybe don't load locations on map initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        ReportDataObject reportDAO = ReportDataObject.getInstance();
        for (WaterSourceReport report
                : reportDAO.getAllSourceReports().values()) {
            String type = report.getType().toString();
            String condition = report.getCondition().toString();
            if (type.equals(filterType.get())) {
                putSourcePins(report);
            } else if (condition.equals(filterCondition.get())) {
                putSourcePins(report);
            } else if ("All".equals(filterAll.get())) {
                putSourcePins(report);
            }
        }
        for (WaterPurityReport report
                : reportDAO.getAllPurityReports().values()) {
            String condition = report.getCondition().toString();
            if (condition.equals(filterCondition.get())) {
                putPurePins(report);
            } else if ("All".equals(filterAll.get())) {
                putPurePins(report);
            }
        }
    }

    /**
     * place the pins for water purity reports.
     *
     * @param report water purity report
     */
    private void putPurePins(WaterPurityReport report) {
        System.out.println(report.getLocation());
        double lat = Double.parseDouble(report.getLocation().getLatitude());
        double lng = Double.parseDouble(report.getLocation()
                .getLongitude());
        LatLong location = new LatLong(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location)
            .icon("http://maps.google.com/mapfiles/ms/icons/green-dot.png");
        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Water Condition: "
                + report.getCondition() + "<br>Water Type: ");
        InfoWindow window = new InfoWindow(infoWindowOptions);

        map.addUIEventHandler(marker,
            UIEventType.click,
            (JSObject obj) -> {
                mainApplication.setCurrentReport(report);
                if (opened != null) {
                    opened.close();
                }
                window.open(map, marker);
                opened = window;
            }
        );
    }

    /**
     * place the pins for water source reports.
     *
     * @param report water source report
     */
    private void putSourcePins(WaterSourceReport report) {
        System.out.println(report.getLocation());
        double lat = Double.parseDouble(report.getLocation().getLatitude());
        double lng = Double.parseDouble(report.getLocation()
                .getLongitude());
        LatLong location = new LatLong(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(location);
        Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("Water Condition: "
                + report.getCondition() + "<br>Water Type: "
                + report.getType());
        InfoWindow window = new InfoWindow(infoWindowOptions);

        map.addUIEventHandler(marker,
            UIEventType.click,
            (JSObject obj) -> {
                mainApplication.setCurrentReport(report);
                if (opened != null) {
                    opened.close();
                }
                window.open(map, marker);
                opened = window;
            }
        );
    }

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

    /**
     * Sets the water type of the report for filtering the pins.
     *
     * @param inputType type of the water
     */
    public static void setWaterType(String inputType) {
        filterType.set(inputType);
    }

    /**
     * Sets the water type of the report for filtering the pins.
     *
     * @param inputType condition of the water
     */
    public static void setWaterCondition(String inputType) {
        filterCondition.set(inputType);
    }

    /**
     * Sets the water type of the report for filtering the pins.
     *
     * @param inputType sets the pins to show all.
     */
    public static void setAllPins(String inputType) {
        filterAll.set(inputType);
    }
}
