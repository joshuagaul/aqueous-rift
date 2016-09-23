package model;

public class Name {

    public String firstName;
    public String lastName;
    public String prefix;

    public Name(String firstName, String lastName, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.prefix = prefix;
    }

    public Name(String firstName, String lastName) {
        this(firstName, lastName, "");
    }
}
