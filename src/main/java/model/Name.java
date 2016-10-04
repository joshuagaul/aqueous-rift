package model;

public class Name {

    private String firstName;
    private String lastName;
    private String prefix;

    /**
     * Name constructor
     * @param   firstName - firstName attribute
     * @param   lastName - lastName attribute
     * @param   prefix - prefix attribute
     */
    public Name(String firstName, String lastName, String prefix) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.prefix = prefix;
    }

    /**
     * Constructor without prefix
     * @param   firstName - firstName attribute
     * @param   lastName - lastName attribute
     */
    public Name(String firstName, String lastName) {
        this(firstName, lastName, "");
    }

    public Name() {
        //for firebase?
    }

    /**
     * Public getter
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Public getter
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Public getter
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }
}
