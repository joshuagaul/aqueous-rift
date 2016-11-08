package classes;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import model.ReportDataObject;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public class HistoricalReport {
    private ArrayList<Double> dataByMonth;
    private Location radiusCenter;
    private Double radiusSize;

    /**
     * Constructor for Historical report
     * @param  radiusCenter Center of the search for reports
     * @param  radiusSize   Size of the radius to search for reports in
     * @param  type         virusPPM or contaminantPPM
     * @param  year         year to filter the reports on
     */
    public HistoricalReport(Location radiusCenter, Double radiusSize,
        String type, String year) {
        if (radiusSize < 0) {
            throw new IllegalArgumentException("Radius can't be less " 
                + "than zero");
        }

        if (year == null || year.length() != 4) {
            throw new IllegalArgumentException("Year is either null or not of "
                + "the right length");
        }
        this.radiusCenter = radiusCenter;
        this.radiusSize = radiusSize;

        dataByMonth = findReports(radiusCenter,
            radiusSize, type, year);
    }

    /**
     * Returns the xy pairings for the data
     * @return  xy coordinate pairs
     */
    public ArrayList<Double> getDataByMonth() {
        return dataByMonth;
    }

    /**
     * Getter for center of the search
     * @return radiusCenter
     */
    public Location getCenter() {
        return radiusCenter;
    }

    /**
     * Getter for size of radius
     * @return radiusSize
     */
    public Double getRadiusSize() {
        return radiusSize;
    }

    /**
     * Finds all of the reports within the given area and returns 
     * an ArrayList of xy pairings. The x coordinate is time in long
     * and the y coordinate is the ppm as a double
     * @param radiusCenter Center of the radius to search for
     * @param  radiusSize Size of search radius
     * @param  type       virusPPM or contaminantPPM
     * @param  year       year to filter the reports on
     * @return  ArrayList arrayList of xy pairings
     */
    private ArrayList<Double> findReports(Location radiusCenter,
        Double radiusSize, String type, String year) {

        Circle main = new Circle(Double.parseDouble(radiusCenter.getLatitude()),
            Double.parseDouble(radiusCenter.getLongitude()), radiusSize / 69);

        ArrayList<Double> retList = new ArrayList<>(12);
        Map<String, Double[]> monthDataMap = new HashMap<>();

        ReportDataObject rdo = ReportDataObject.getInstance();

        Map<String, WaterPurityReport> purityReports = rdo
            .getAllPurityReports();

        for (WaterPurityReport wpr : purityReports.values()) {

            SimpleDateFormat yearF = new SimpleDateFormat("yyyy");
            SimpleDateFormat month = new SimpleDateFormat("MM");

            if (yearF.format(wpr.getDate()).equals(year)) {
                Location l = wpr.getLocation();
                Circle c = new Circle(Double.parseDouble(l.getLatitude()),
                    Double.parseDouble(l.getLongitude()), .01);

                if (intersects(main, c)) {
                    double data = 0;

                    if (type.equalsIgnoreCase("virusppm")) {
                        data = wpr.getVirusPPM();
                    } else if (type.equalsIgnoreCase("contaminantppm")) {
                        data = wpr.getContaminantPPM();
                    } else {
                        throw new IllegalArgumentException("Can't organize the"
                            + " data by " + type);
                    }

                    Double[] monthData = monthDataMap.get(month.format(wpr
                        .getDate()));

                    if (monthData == null) {
                        monthData = new Double[]{data, new Double(1)};
                        monthDataMap.put(month.format(wpr.getDate()),
                            monthData);

                    } else {
                        data = (Double) ((monthData[0] * monthData[1]) + data)
                            / (monthData[1] + 1);
                        monthData[0] = data;
                        monthData[1] = monthData[1] + 1;
                        monthDataMap.put(month.format(wpr.getDate()),
                            monthData);

                    }
                }
            }
        }

        for (String month : monthDataMap.keySet()) {
            double dataToAdd = monthDataMap.get(month) != null 
                ? monthDataMap.get(month)[0] : -1;

            retList.add(Integer.parseInt(month) - 1, dataToAdd);
        }

        return retList;
    }

    /**
     * Determines if the two circles intersesct. Used to determine if report
     * is in the area given by the initial point and radius size
     * @param  a First circle
     * @param  b Second Circle
     * @return   True if the two circles intersect each other
     */
    private boolean intersects(Circle a, Circle b) {
        double distance = Math.sqrt(Math.pow((b.getCenterX()
            - a.getCenterX()), 2) - Math.pow(b.getCenterY()
            - a.getCenterY(), 2));

        return distance <= (a.getRadius() + b.getRadius());
    }

}