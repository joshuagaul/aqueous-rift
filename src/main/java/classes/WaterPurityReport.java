package classes;

// import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class WaterPurityReport extends WaterReport {
    private ObjectProperty<OverallCondition> condition = 
        new SimpleObjectProperty<>();
    private static int number = 0;
    private double virusPPM;
    private double contaminantPPM;

    /**
     * Creates a WaterPurityReportObject
     * @param  reporterId Username of the person creating the report
     * @param  date       Date report was created
     * @param  location   Location of the water
     * @param  condition  Overall condition of the water 
     * (Safe/Treatable/Unsafe)
     * @param virusPPM The parts per million of viruses in the water.
     * @param  contaminantPPM The parats per million of contaminants in the water.
     */
    public WaterPurityReport(String reporterId, String date, Location location,
        OverallCondition condition, double virusPPM, double contaminantPPM) {
        super(date, reporterId, location);
        number++;
        this.condition.set(condition);
    }

    /**
     * Gets number of WaterPurityReports
     * @return Returns number of water source reports
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets condition of the water
     * @param condition OverallCondition of the water to be set.
     */
    public void setCondition(OverallCondition condition) {
        this.condition.set(condition);
    }

    /**
     * Gets OverallCondition
     * @return Returns overall condition of water
     */
    public OverallCondition getCondition() {
        return this.condition.get();
    }

    /**
     * Sets virusPPM
     * @param virusPPM Virus parts per million to be set
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * Gets this report's virusPPM
     * @return Virus data in parts per million
     */
    public double getVirusPPM() {
        return virusPPM;
    }

    /**
     * Sets this report's contaminant parts per million.
     * @param contaminantPPM contaminant parts per million to be set.
     */
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }

    /**
     * Gets this report's contaminant parts per million.
     * @return Contaminant data in parts per million.
     */
    public double getContaminantPPM() {
        return contaminantPPM;
    }
}
