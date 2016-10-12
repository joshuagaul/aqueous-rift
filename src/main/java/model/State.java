package model;

public enum State {
    ALABAMA("Alabama", "AL")
    , ALASKA("Alaska", "AK")
    , AMERICAN_SAMOA("American Samoa", "AS")
    , ARIZONA("Arizona", "AZ")
    , ARKANSAS("Arkansas", "AR")
    , CALIFORNIA("California", "CA")
    , COLORADO("Colorado", "CO")
    , CONNECTICUT("Connecticut", "CT")
    , DELAWARE("Delaware", "DE")
    , DISTRICT_OF_COLUMBIA("District of Columbia", "DC")
    , FEDERATED_STATES_OF_MICRONESIA("Federated States of Micronesia", "FM")
    , FLORIDA("Florida", "FL")
    , GEORGIA("Georgia", "GA")
    , GUAM("Guam", "GU")
    , HAWAII("Hawaii", "HI")
    , IDAHO("Idaho", "ID")
    , ILLINOIS("Illinois", "IL")
    , INDIANA("Indiana", "IN")
    , IOWA("Iowa", "IA")
    , KANSAS("Kansas", "KS")
    , KENTUCKY("Kentucky", "KY")
    , LOUISIANA("Louisiana", "LA")
    , MAINE("Maine", "ME")
    , MARYLAND("Maryland", "MD")
    , MARSHALL_ISLANDS("Marshall Islands", "MH")
    , MASSACHUSETTS("Massachusetts", "MA")
    , MICHIGAN("Michigan", "MI")
    , MINNESOTA("Minnesota", "MN")
    , MISSISSIPPI("Mississippi", "MS")
    , MISSOURI("Missouri", "MO")
    , MONTANA("Montana", "MT")
    , NEBRASKA("Nebraska", "NE")
    , NEVADA("Nevada", "NV")
    , NEW_HAMPSHIRE("New Hampshire", "NH")
    , NEW_JERSEY("New Jersey", "NJ")
    , NEW_MEXICO("New Mexico", "NM")
    , NEW_YORK("New York", "NY")
    , NORTH_CAROLINA("North Carolina", "NC")
    , NORTH_DAKOTA("North Dakota", "ND")
    , NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands", "MP")
    , OHIO("Ohio", "OH")
    , OKLAHOMA("Oklahoma", "OK")
    , OREGON("Oregon", "OR")
    , PALAU("Palau", "PW")
    , PENNSYLVANIA("Pennsylvania", "PA")
    , PUERTO_RICO("Puerto Rico", "PR")
    , RHODE_ISLAND("Rhode Island", "RI")
    , SOUTH_CAROLINA("South Carolina", "SC")
    , SOUTH_DAKOTA("South Dakota", "SD")
    , TENNESSEE("Tennessee", "TN")
    , TEXAS("Texas", "TX")
    , UTAH("Utah", "UT")
    , VERMONT("Vermont", "VT")
    , VIRGIN_ISLANDS("Virgin Islands", "VI")
    , VIRGINIA("Virginia", "VA")
    , WASHINGTON("Washington", "WA")
    , WEST_VIRGINIA("West Virginia", "WV")
    , WISCONSIN("Wisconsin", "WI")
    , WYOMING("Wyoming", "WY")
    , UNKNOWN("Unknown", "");

    /**
     * The full string representation of the State
     */
    private final String name;
    /**
     * The representation of the State abbreviation
     */
    private final String code;

    /**
     * Constructor for the enumeration
     *
     * @param name the full representation of the State
     * @param code the abbreviation of the State
     */
    State(String name, String code) {
        this.name = name;
        this.code = code;
    }
    /**
     * Return the full representation of the State
     *
     * @return the full representation of the State
     */
    public String getName() {
        return name;
    }
    /**
     * Return the abbreviation of the State
     *
     * @return the abbreviation of the State
     */
    public String getCode() {
        return code;
    }
    /**
     * Return the String of the class
     *
     * @return the string of the abbreviation of the State
     */
    public String toString() {
        return code;
    }
}
