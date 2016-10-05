package coredata;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ConnectionFactory {

    private static final String DATABASE_URL = "https://aqueous-rift.firebaseio.com/";
    private static DatabaseReference db;

    public static void initializeFireBase() {
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
            .getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError e) {
                System.out.println(e);
            }
        });
    }

    //General methods for CRUD operations to be used for any domain (user, map data, etc)

    //set() / setValue() are equivalent
    //update() updates keys w/o replacing data
    //push() adds a list of data, and generates unique keys for each
    //ref.child()
    //Delete by reference.set(null)

    /**
    * Sets the path to data map
    * @param key - the key to search in DB
    */
    public void setReference(String key) {
        db = db.child(key);
    }

    /**
    * Gets the current databaseReference
    * @return databaseReference - DB object to execute on
    */
    public static DatabaseReference getReference(String key) {
        return db.child(key);
    }
}