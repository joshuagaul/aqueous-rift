package ui;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import main.MainFXApplication;
import classes.User;
import classes.Name;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.JavascriptRuntime;
import com.lynden.gmapsfx.javascript.JavaFxWebEngine;
import javafx.scene.web.WebView;


public class MainFXApplicationTests {

    private MainFXApplication sut;
    private User testUser;
    private LatLong testLoc;
    private double oldLat = 35.0;
    private double oldLong = 140.0;
    private double newLat = 45.0;
    private double newLong = 120.0;
    private WebView webview = new WebView();

    @Before
    public void init() {
        JavaFxWebEngine webengine = new JavaFxWebEngine(webview.getEngine());
        JavascriptRuntime.setDefaultWebEngine(webengine);
        sut = new MainFXApplication();
        Name name = new Name("first", "last", "prefix");
        testUser = new User("password", "email", "phoneNum", "userId", name);
        testLoc = new LatLong(oldLat, oldLong);
    }

    @Test
    public void applicationInitializes() {
        assertNotNull(sut);
    }

    @Test
    public void applicationTracksCurrentUser() {
        assertNull(sut.getCurrentUser());
        sut.setCurrentUser(testUser);
        assertEquals("Current user is not equal to testUser", testUser,
            sut.getCurrentUser());
    }

    @Test
    public void applicationTracksCenterOfMap() {
        assertNull(sut.getMapCenter());
        sut.setMapCenter(testLoc);
        assertEquals("Center of map was not changed correctly", testLoc,
            sut.getMapCenter());
    }

    @Test
    public void applicationChangesLatitude() {
        sut.setMapCenter(testLoc);
        sut.changeCenterLatitude(newLat);
        LatLong newLatLoc = sut.getMapCenter();
        assertEquals("Latitude was not changed correctly",
            new LatLong(newLat, oldLong), newLatLoc);
    }

    @Test
    public void applicationChangesLongitude() {
        sut.setMapCenter(testLoc);
        sut.changeCenterLatitude(newLong);
        LatLong newLongLoc = sut.getMapCenter();
        assertEquals("Latitude was not changed correctly",
            new LatLong(oldLat, newLong), newLongLoc);
    }
}
