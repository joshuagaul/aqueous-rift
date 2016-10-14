package data;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import model.DataManager;

public class DataManagerTest {

    @Test
    public void getsDBInstance() {
        DataManager.initializeFireBase();
        assertNotNull(DataManager.getReference(""));
    }

}
