package model;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WaterSourceReport {
    private static int number = 0;
    private Date date;
    private String userId;
    private ObjectProperty<Location> location = new SimpleObjectProperty<>();
    private ObjectProperty<WaterType> type = new SimpleObjectProperty<>();
    private ObjectProperty<WaterCondition> condition =
        new SimpleObjectProperty<>();

    /**
     * Get the total number of the reports.
     *
     * @return total number of the reports
     */
    public int getNumber() {
        return number;
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
     * Get the user ID of the reporter.
     *
     * @return the user ID of the reporter
     */
    public String getUserId() {
        return userId;
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
     * Get the object property of the location of the water source.
     *
     * @return the object property of the location of the water source
     */
    public ObjectProperty getLocationProperty() {
        return location;
    }

    /**
     * Get the type of the water source.
     *
     * @return the type of the water source
     */
    public WaterType getType() {
        return type.get();
    }

    /**
     * Set the type of the water source.
     *
     * @param type the type of the water source
     */
    public void setType(WaterType type) {
        this.type.set(type);
    }

    /**
     * Get the object property of the water type source.
     *
     * @return the object property of the location of the water source
     */
    public ObjectProperty getTypeProperty() {
        return type;
    }

    /**
     * Get the condition of the water source.
     *
     * @return the condition of the water source
     */
    public WaterCondition getCondition() {
        return condition.get();
    }

    /**
     * Set the condition of the water source.
     *
     * @param condition the condition of the water source
     */
    public void setCondition(WaterCondition condition) {
        this.condition.set(condition);
    }

    /**
     * Get the object property of the water condition source.
     *
     * @return the object property of the condition of the water source
     */
    public ObjectProperty getConditionProperty() {
        return condition;
    }
    /**
     * Make a new water source report.
     *
     * @param user the user information who login the app
     * @param location the location information
     * @param type the type of the water
     * @param condition the condition of the water
     */
    public WaterSourceReport(User user,
                            Location location,
                            WaterType type,
                            WaterCondition condition) {
        number++;
        date = new Date();
        userId = user.getUserId();
        this.location.set(location);
        this.type.set(type);
        this.condition.set(condition);
    }
}
