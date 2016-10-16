package classes;

public class Location {

    private String latitude;
    private String longitude;
    private String street;
    private String city;
    private State state;
    //private int zipCode;

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
        //this.zipCode = zipCode;
    }

    /**
     * Constructor for GPS coordinates.
     *
     * @param latitude The latitude GPS coordinate.
     * @param longitude The longitude GPS coordinate.
     */
    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        //TODO
        //Do some calulations/query google maps to approximate street address
    }

    /**
     * Get the latitude of the location.
     *
     * @return the latitude of the location
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Get the longitude of the location.
     *
     * @return the longitude of the location
     */
    public String getLongitude() {
        return longitude;
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
    // public int getZipCode() {
    //     return zipCode;
    // }

    /**
     * Return the string of the location class.
     *
     * @return the full address of the location
     */
    public String toString() {
        return street + " " + city + " " + state.toString() + " "; //+ zipCode;
    }
}
