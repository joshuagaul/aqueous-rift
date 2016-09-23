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
    //userId1
        //gmcallister
    //userId2
        //username2
    //...


package coredata;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import coredata.Connection;
import model.User;
import java.util.Map;
import java.util.HashMap;

public class UserDataObject {

    private DatabaseReference dbCon;

    public UserDataObject() {
        Connection con = new Connection();
        con.setReference("/userList");
        dbCon = con.getReference();
    }

    public void addSingleUser(User u, String userName) {
        //Map the user with the userName
        Map<String, User> userMapping = new HashMap<>();
        userMapping.put(userName, u);
        //Or could just set a child reference of /userList
        //con.setReference("/userName")
        //con.getResource  would return "/aqueous-rift/userList/userName"
        
        dbCon.setValue(userMapping);
    }
}
