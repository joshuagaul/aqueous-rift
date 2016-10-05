package data;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import coredata.ConnectionFactory;

public class ConnectionFactoryTest {

    @Test
    public void getsDBInstance() {
        ConnectionFactory.initializeFireBase();
        assertNotNull(ConnectionFactory.getReference(""));
    }

}
