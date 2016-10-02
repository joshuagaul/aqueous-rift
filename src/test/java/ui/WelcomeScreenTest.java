package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.Test;

import org.loadui.testfx.*;
import org.loadui.testfx.utils.FXTestUtils;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.loadui.testfx.utils.FXTestUtils;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.Commons.hasText;
import static org.loadui.testfx.controls.impl.EnabledMatcher.disabled;
import static org.loadui.testfx.controls.impl.EnabledMatcher.enabled;
import static org.loadui.testfx.controls.impl.NodeExistsMatcher.exists;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;

import main.MainFXApplication;

// public class WelcomeScreenTest extends GuiTest {
//
//     @Override
//     protected Parent getRootNode() {
//         MainFXApplication appUnderTest = new MainFXApplication();
//         appUnderTest.showWelcomeScreen();
//         return appUnderTest.getRootLayout();
//     }
//
//   @Test
//   public void login_button_navigates_correctly() {
//       this.click("#loginpage");
//       verifyThat("#loginpage", hasText("was clicked"));
//   }
// }
//
//

//I think threat death is happening because the javafx application doesn't completely loader
//before the test is run (sleeping the thread doesn't work though)
//OR it is because the loadui has a bug/outdated, could change dependency
public class WelcomeScreenTest {

    private static GuiTest controller;

    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        FXTestUtils.launchApp(MainFXApplication.class);

        controller = new GuiTest() {
            @Override
            protected Parent getRootNode() {
                MainFXApplication appUnderTest = new MainFXApplication();
                appUnderTest.showWelcomeScreen();
                return appUnderTest.getRootLayout();
            }
        };
        Thread.sleep(1000);
    }

    @Test
    public void login_button_navigates_correctly() {
        controller.click("#loginpage");
        verifyThat("#loginpage", hasText("was clicked"));
    }
}
