package model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import classes.User;
import classes.Name;

public class UserDataObject {

    private Set<String> usernames = new HashSet<>();
    private Map<String, User> userMap = new HashMap<>();
    private AtomicInteger size = new AtomicInteger();
    private static final UserDataObject INSTANCE = new UserDataObject();

    /**
     * Private constructor for the UserDataObject to implement
     * the Singleton pattern
     */
    private UserDataObject() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated the"
                + "UserDataObject.  Please getInstance().");
        }
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
                //update users here
                updateUsers(dataSnapshot.getKey(), newSize);
                updateUserList(dataSnapshot.getKey(),
                    (HashMap<String, Object>) dataSnapshot.getValue());
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                System.out.println("Edited " + dataSnapshot.getKey()
                    + ", count is " + size);
                // updateUsers(dataSnapshot.getKey(), newSize);
                // updateUserList(dataSnapshot.getValue(User.class));
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                // User removedUser = dataSnapshot.getValue(User.class);
                // System.out.println("The user "
                //     + removedUser.getUserId() + " has been deleted");
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
     * Returns the singleton instance
     * @return UserDataObject singleton
     */
    public static UserDataObject getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a reference to the "UserList" key in the database
     * @return UserList database reference
     */
    private DatabaseReference getUserList() {
        return DataManager.getReference("/userList");
    }

    /**
     * Returns a reference to the "Users" key in the database
     * @return Users database reference
     */
    private DatabaseReference getUsers() {
        return DataManager.getReference("/users");
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
     * Determines if the queried userName is in the database
     * @param  userName The username to query
     * @return          True if the user exists in the database
     */
    public Boolean userExists(String userName) {
        return usernames.contains(userName);
    }

    /**
     * Returns the User object mapped to the specified username
     * @param  username The username key in the mapping
     * @return          User object
     */
    public User getUser(String username) {
        return userMap.get(username);
    }

    /**
     * Overwrites the existing mapping associated with the userName key
     * @param  userToAdd New user object to add to the database
     * @param  userName Key to map the user with
     */
    public void editSingleUser(User userToAdd, String userName) {
        addSingleUser(userToAdd, userName);
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

    /**
     * Since I was having trouble using the FireBase object inference
     * this is a hacky way to cast the mapped userList object to a User
     * @param  username Key to map the User object with
     * @param  objUser Data structure that FireBase returns
     */
    private void updateUserList(String username, Map<String, Object> objUser) {
        //Hacky solution to copy Object into User because of problems with
        //Firebase Object mapping
        Map<String, String> nameObj = (HashMap<String, String>)
            objUser.get("name");
        String firstName = (String) nameObj.get("firstName");
        String lastName = (String) nameObj.get("lastName");
        String prefix = (String) nameObj.get("prefix");
        String password = (String) objUser.get("password");
        String email = (String) objUser.get("email");
        String phoneNum = (String) objUser.get("phoneNum");
        String usertype = (String) objUser.get("userType");
        Name name = new Name(firstName, lastName, prefix);
        User user = new User(password, email, phoneNum, "4", name, usertype);
        userMap.put(username, user);
    }
}
