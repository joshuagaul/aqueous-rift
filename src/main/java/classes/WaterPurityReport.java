package classes;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class WaterPurityReport extends WaterReport {

    private ObjectProperty<WaterCondition> condition =
        new SimpleObjectProperty<>();
    private DoubleProperty virusPPM = new SimpleDoubleProperty();
    private DoubleProperty contaminantPPM = new SimpleDoubleProperty();

    /**
     * Creates a WaterPurityReportObject
     * @param  reporterId Username of the person creating the report
     * @param  date       Date report was created
     * @param  location   Location of the water
     * @param  condition  Overall condition of the water
     * (Safe/Treatable/Unsafe)
     * @param virusPPM The parts per million of viruses in the water.
     * @param  contaminantPPM The parats per million
     *                        of contaminants in the water.
     */
    public WaterPurityReport(String reporterId, Date date, Location location,
        WaterCondition condition, double virusPPM, double contaminantPPM) {
        super(date, reporterId, location);
        this.condition.set(condition);
        this.virusPPM.set(virusPPM);
        this.contaminantPPM.set(contaminantPPM);
    }

    /**
     * Sets condition of the water
     * @param condition OverallCondition of the water to be set.
     */
    public void setCondition(WaterCondition condition) {
        this.condition.set(condition);
    }

    /**
     * Gets OverallCondition
     * @return Returns overall condition of water
     */
    public WaterCondition getCondition() {
        return this.condition.get();
    }

    /**
     * Sets virusPPM
     * @param virusPPM Virus parts per million to be set
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM.set(virusPPM);
    }

    /**
     * Gets this report's virusPPM
     * @return Virus data in parts per million
     */
    public double getVirusPPM() {
        return virusPPM.get();
    }

    /**
     * Sets this report's contaminant parts per million.
     * @param contaminantPPM contaminant parts per million to be set.
     */
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM.set(contaminantPPM);
    }

    /**
     * Gets this report's contaminant parts per million.
     * @return Contaminant data in parts per million.
     */
    public double getContaminantPPM() {
        return contaminantPPM.get();
    }

    /**
     * For FireBase
     */
    private WaterPurityReport() {
        super();
    }
}
