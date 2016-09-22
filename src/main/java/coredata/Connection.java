package coredata;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Connection {

    private static final String DATABASE_URL = "https://aqueous-rift.firebaseio.com/";
    private static DatabaseReference db;

    public Connection() {
        // Initialize the app with a service account, granting admin privileges
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
              .setServiceAccount(new FileInputStream("config.json"))
              .setDatabaseUrl(DATABASE_URL)
              .build();
              FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException error) {
            System.out.println(error);
        }



        // The app only has access as defined in the Security Rules
        db = FirebaseDatabase
            .getInstance()
            .getReference("/some_resource");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //String res = dataSnapshot.getValue();
                System.out.println(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError e) {
                System.out.println(e);
            }
        });
    }

    public void sendTest(String message) {
        System.out.println(message);
        db.setValue(message);
    }

}
