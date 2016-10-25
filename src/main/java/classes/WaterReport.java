package classes;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class WaterReport {

    private Date date;
    private StringProperty reporterId = new SimpleStringProperty();
    private ObjectProperty<Location> location = new SimpleObjectProperty<>();

    /**
     * Instantiates an abstract WaterReport. WaterSourceReport and
     * Water purity report extend this class.
     * @param  date     The date report was created
     * @param  reporterId The username of the report creator.
     * @param  location The location that the water report comes from.
     */
    public WaterReport(Date date, String reporterId, Location location) {
        this.date = date;
        this.reporterId.set(reporterId);
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
    public Date getDate() {
        return date;
    }

    /**
     * Get the date as a string when the report is created.
     *
     * @return the date when the report was created as a string.
     */    
    public String getDateAsString() {
        java.text.SimpleDateFormat dateFormat =
            new java.text.SimpleDateFormat ("mm/dd/yyyy 'at' hh:mm:ss a zzz");
        return dateFormat.format(date);
    }

    /**
     * Get the user ID of the reporter.
     *
     * @return the user ID of the reporter
     */
    public String getReporterId() {
        return reporterId.get();
    }

    /**
     * Get the total number of reports in specific WaterReports.
     * @return [description]
     */
    public abstract int getNumber();

}
