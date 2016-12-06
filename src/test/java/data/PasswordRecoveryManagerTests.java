package data;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertNull;
// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.Before;
import classes.PasswordRecoveryManager;

public class PasswordRecoveryManagerTests {

    @Before
    public void init() {

    }

    @Test
    public void test1() {
        try {
            PasswordRecoveryManager pwm = new PasswordRecoveryManager("678575"
                + "9126", "Hello This is a test!");
            pwm.sendPassword();
        } catch (com.twilio.exception.ApiException e) {
            System.out.println(e);
        }
    }
}
