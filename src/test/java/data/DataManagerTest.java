package data;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.Before;
import model.DataManager;
import com.google.firebase.database.DatabaseReference;

public class DataManagerTest {

    private static String reference = "/users";
    private DataManager sut = new DataManager();

    @Before
    public void init() {

    }

    @Test
    public void getsDBInstance() {
        //Can't run this more than once, so just put in the first test to run.
        DataManager.initializeFireBase();
        DatabaseReference root = DataManager.getReference("");
        String rootKey = root.getKey();
        assertNotNull(root);
        // Root key should be null
        assertNull(rootKey);
    }

    @Test
    public void setReferenceChangesRootReference() {
        DatabaseReference beforeChange = DataManager.getReference("");
        sut.setReference(reference);
        DatabaseReference test = DataManager.getReference("");
        assertNotNull(test);
        String key = reference.substring(1);
        assertNotEquals("Root Key: " + test.getKey() + "is not " + key
            + ".", beforeChange.getKey(), test.getKey());
        assertEquals("Root Key: " + test.getKey() + "is not " + key
            + ".", key, test.getKey());
    }

}
