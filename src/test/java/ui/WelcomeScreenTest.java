package ui;

import org.junit.Test;
import javafx.scene.Parent;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.junit.BeforeClass;
// import static org.hamcrest.CoreMatchers.is;
// import static org.hamcrest.CoreMatchers.not;
// import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.Assertions.assertNodeExists;
// import static org.loadui.testfx.GuiTest.waitUntil;
// import static org.loadui.testfx.controls.Commons.hasText;
// import static org.loadui.testfx.controls.impl.EnabledMatcher.disabled;
// import static org.loadui.testfx.controls.impl.EnabledMatcher.enabled;
// import static org.loadui.testfx.controls.impl.NodeExistsMatcher.exists;
// import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;

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
    public void loginButtonNavigatesCorrectly() {
        controller.click("#loginpage");
        assertNodeExists("#login");
    }
}
