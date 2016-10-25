package classes;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WaterSourceReport extends WaterReport {

    private static int number = 0;
    private ObjectProperty<WaterType> type = new SimpleObjectProperty<>();
    private ObjectProperty<WaterCondition> condition =
        new SimpleObjectProperty<>();

    /**
     * Gets the number of WaterSourceReports.
     * @return Returns the number of waterSourceReports.
     */
    public int getNumber() {
        return number;
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
    // public ObjectProperty getTypeProperty() {
    //     return type;
    // }

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
     * Constructor used specifically for the editing call chain in
     * ReportDataObject
     *
     * @param   reporterId The username of the user who created the report
     * @param   location The location of the report
     * @param   type The type of water found
     * @param   condition The condition of water found
     * @param   date The date the report was submitted
     */
    public WaterSourceReport(String reporterId, Location location,
            WaterType type, WaterCondition condition, Date date) {
        super(date, reporterId, location);
        number++;
        this.type.set(type);
        this.condition.set(condition);
    }
}
