package data;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import org.junit.Test;
import org.junit.Before;
import model.DataManager;
import model.UserDataObject;
import com.google.firebase.database.DatabaseReference;

public class UserDataTest {
    private static UserDataObject userData;
    private DataManager sut = new DataManager();
    private static final int TIMEOUT = 200;
    
    @Before
    public void setUp() throws Exception {
        
    }

    @Test(timeout = TIMEOUT)
    public void testExistUser() {
        userData = UserDataObject.getInstance();
        String[] testUser = {"admin", "dkagmrdmldhk",
            "gmadmin", "jgaul", "josh"};
        for (String user : testUser) {
            if (userData.userExists(user))
            assertEquals("Should all test user exist." + user, user,
                         userData.getUser(user).getName());
        }
    }
    
    @Test(timeout = TIMEOUT)
    public void testNoExistUser() {
        userData = UserDataObject.getInstance();
        String[] testUser = {"aaaa", "bbbb",
            "cccc", "dddd", "eeee"};
        for (String user : testUser) {
            if (userData.userExists(user))
                assertNotEquals("Should all test user do not exist." + user,
                                user, userData.getUser(user).getName());
        }
    }
    
    @Test(timeout = TIMEOUT)
    public void testUserType() {
        userData = UserDataObject.getInstance();
        String[] userType = {"User", "Worker"
            ,"Manager", "Admin"};
        String[] testUser = {"user", "manager", "worker", "admin"};
        for (String user : testUser) {
            if (userData.userExists(user))
                assertEquals("Should match all user and type." + user,
                             user, userData.getUser(user).getUserType());
        }
    }
}
