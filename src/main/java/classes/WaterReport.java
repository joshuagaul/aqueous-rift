package classes;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class WaterReport {

    private String date;
    private String reporterId;
    private ObjectProperty<Location> location = new SimpleObjectProperty<>();

    /**
     * Instantiates an abstract WaterReport. WaterSourceReport and 
     * Water purity report extend this class.
     * @param  date     The date report was created
     * @param  reporterId The username of the report creator.
     * @param  location The location that the water report comes from.
     */
    public WaterReport(String date, String reporterId, Location location) {
        this.date = date;
        this.reporterId = reporterId;
        this.location.set(location);
    }

    /**
     * No args constructor for firebase
     */
    private WaterReport() {

    }

    /**
     * Get the location of the water source.
     *
     * @return the location of the water source
     */
    public Location getLocation() {
        return location.get();
    }

    /**
     * Set the location of the water source.
     *
     * @param location the location of the water source
     */
    public void setLocation(Location location) {
        this.location.set(location);
    }

    /**
     * Get the date when the report is created.
     *
     * @return the date when the report is created
     */
    public String getDate() {
        return date;
    }

    /**
     * Get the user ID of the reporter.
     *
     * @return the user ID of the reporter
     */
    public String getReporterId() {
        return reporterId;
    }

    /**
     * Get the total number of reports in specific WaterReports.
     * @return [description]
     */
    public abstract int getNumber();

}