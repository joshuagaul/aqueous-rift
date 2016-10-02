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
    //username2
        //true
    //username3
        //true
    //...
package coredata;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import model.User;

public class UserDataObject {

    private Set<String> usernames = new HashSet<>();
    private AtomicInteger size = new AtomicInteger();

    /**
     * Constructor for the UserDataObject
     */
    public UserDataObject() {
        DatabaseReference users = getUsers();
        DatabaseReference userList = getUserList();
        //Add listeners to update usernames and size when added
        //**May not be the most efficient, looks like it is called for every
        //child, not just the one added
        //**But may need it this way to keep all local data synced
        userList.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                // New child added, increment count
                int newSize = size.incrementAndGet();
                System.out.println("Added " + dataSnapshot.getKey()
                    + ", count is " + newSize);
                System.out.println(size);
                //update users here
                updateUsers(dataSnapshot.getKey(), newSize);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,
                    String prevChildKey) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                User removedUser = dataSnapshot.getValue(User.class);
                System.out.println("The blog post titled "
                    + removedUser.getUserId() + " has been deleted");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,
                String prevChildKey) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("FireBase error:"
                    + databaseError.getMessage());
            }
        });
    }

    /**
     * Returns a reference to the "UserList" key in the database
     * @return UserList database reference
     */
    private DatabaseReference getUserList() {
        return ConnectionFactory.getReference("/userList");
    }

    /**
     * Returns a reference to the "Users" key in the database
     * @return Users database reference
     */
    private DatabaseReference getUsers() {
        return ConnectionFactory.getReference("/users");
    }

    /**
     * Adds a user to the firebase database
     * @param  userToAdd - user instance to add
     * @param  userName - the key in the database entry
     */
    public void addSingleUser(User userToAdd, String userName) {
        //Map the user with the userName
        DatabaseReference user = getUserList().child("/" + userName);
        user.setValue(userToAdd);
    }

    /**
     * Helper method from an add callback to userList
     * @param  username - key to update
     * @param  count - the new count of usernames
     */
    private void updateUsers(String username, int count) {
        //Update database
        DatabaseReference users = getUsers();
        users.child("/size").setValue(count);
        users.child("/" + username).setValue(true);
        //Update local storage
        usernames.add(username);
    }
}
