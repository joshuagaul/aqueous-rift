//Schemas

//userlist
    //username
        //password
        //Name
            //firstName
            //lastName
            //prefix
        //Email
        //phoneNum
        //userId

//users
    //size
    //gmcallister
        //true
    //userId2
        //true
    //userId3
        //true
    //...


package coredata;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.util.Map;
import java.util.HashMap;

import coredata.ConnectionFactory;
import model.User;


public class UserDataObject {

    private DatabaseReference dbCon;

    public UserDataObject() {
        dbCon = ConnectionFactory.getReference("/userList");
    }

    public void addSingleUser(User user, String userName) {
        //Map the user with the userName
        dbCon = dbCon.child("/" + userName);
        dbCon.setValue(user);

        //TODO update users key
        //TODO logging/postback data
        //TODO Document
    }
}
