package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import javafx.fxml.FXML;
import java.util.Map;
import main.MainFXApplication;
import model.ReportDataObject;
import classes.WaterSourceReport;

public class MapController implements IController,
        MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    private MainFXApplication mainApplication;

    @FXML
    public void initialize() {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(66, 45.2))
                //.mapType(MapTypeIdEnum.TERRAIN)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

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
        for (WaterSourceReport report :
                reportDAO.getAllCandidateReports().values()) {
            System.out.println("reports!");
            double lat = Double.parseDouble(report.getLocation().getLatitude());
            double lng = Double.parseDouble(report.getLocation()
                .getLongitude());
            LatLong location = new LatLong(lat, lng);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(location);
            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
        }

        LatLong fredWilkieLocation = new LatLong(66.55, 45.333);
        //Marker example
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(fredWilkieLocation);
        Marker fredWilkieMarker = new Marker(markerOptions);
        map.addMarker(fredWilkieMarker);
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Russia?<br>"
                                + "Report..." );
        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
        //It centers the map on either the last marker added
        //  or on the window opening
    }

    /**
     * Gives the controller access to mainApplication.
     *
     * @param mainFXApplication mainFXApplication
     */
    public void setMainApp(MainFXApplication mainFXApplication) {
        mainApplication = mainFXApplication;
    }

}
