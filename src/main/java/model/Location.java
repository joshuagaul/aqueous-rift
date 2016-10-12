package model;

public class Location {
    private String street;
    private String city;
    private State state;
    private int zipCode;
    /**
     * Constructor with the address of the location.
     *
     * @param street the street of the location
     * @param city the city of the location
     * @param state the state of the location
     * @param zipCode the zipCode of the location
     */
    public Location(String street, String city, State state, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    /**
     * Get the street name of the location.
     *
     * @return the street of the location
     */
    public String getStreet() {
        return street;
    }
    /**
     * Get the city of the location.
     *
     * @return the city of the location
     */
    public String getCity() {
        return city;
    }
    /**
     * Get the state of the location.
     *
     * @return the state of the location
     */
    public State getState() {
        return state;
    }
    /**
     * Get the zip code of the location.
     *
     * @return the zip code of the location
     */
    public int getZipCode() {
        return zipCode;
    }
    /**
     * Return the string of the location class.
     *
     * @return the full address of the location
     */
    public String toString() {
        return street + " " + city + " " + state.toString() + " " + zipCode;
    }
}
