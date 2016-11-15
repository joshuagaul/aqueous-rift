package data;

import static org.junit.Assert.assertEquals;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

import java.util.HashMap;
import java.util.ArrayList;

import java.text.SimpleDateFormat;
import java.text.ParseException;

import classes.Location;
import classes.HistoricalReport;
import classes.WaterPurityReport;
import org.junit.Test;
import org.junit.Before;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HistoricalReportTests {
    private HashMap<String, WaterPurityReport> mockPurityReports;
    private static final int TIMEOUT = 200;

    /**
     * Makes a mock water purity report
     * @param  date           Fake date of report creation
     * @param  location       Location of report
     * @param  virusPPM       virus count in ppm of report
     * @param  contaminantPPM contaminant count in ppm of report
     * @return                Mock water purity report
     * @throws ParseException throws a parse exception if date wasn't
     * of the right format.
     */
    private WaterPurityReport makeMockReport(String date, Location location,
        double virusPPM, double contaminantPPM) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        return new WaterPurityReport(null, df.parse(date), location, null,
            virusPPM, contaminantPPM);
    }

    @Before
    public void setup() throws ParseException {
        mockPurityReports = new HashMap<>();
        mockPurityReports.put("1", makeMockReport("08/10/2016",
            new Location("45", "45"), 10, 10));

        mockPurityReports.put("2", makeMockReport("09/05/2016",
            new Location("45", "45.1"), 20, 12));

        mockPurityReports.put("3", makeMockReport("10/05/2016",
            new Location("50", "50"), 20, 20));

        mockPurityReports.put("4", makeMockReport("10/05/2011",
            new Location("45", "45"), 45, 45));

        mockPurityReports.put("5", makeMockReport("10/15/2011",
            new Location("50", "51"), 45, 30));
    }

    @Test(timeout = TIMEOUT)
    public void test1FilterOnYear() {
        HistoricalReport hr = new HistoricalReport(new Location("45", "45"),
            80000000.0, "virusppm", "2016", mockPurityReports);

        ArrayList<Double> data = hr.getDataByMonth();
        int count = 0;
        for (Double elem : data) {
            if (elem != 0) {
                count++;
            }
        }

        assertEquals("The size of the resulting data should be three", 3,
            (int) count);
    }

    @Test(timeout = TIMEOUT)
    public void test2FilterOnLocation() {
        HistoricalReport hr = new HistoricalReport(new Location("45", "45"),
            10.0, "virusppm", "2016", mockPurityReports);

        ArrayList<Double> data = hr.getDataByMonth();
        int count = 0;
        for (Double elem : data) {
            if (elem != 0) {
                count++;
            }
        }
        assertEquals("The size of the resulting data should be two", 2,
            (int) count);
    }

    @Test(timeout = TIMEOUT)
    public void test3FilterOnVirusPPM() {
        HistoricalReport hr = new HistoricalReport(new Location("45", "45"),
            80000000.0, "virusppm", "2011", mockPurityReports);

        ArrayList<Double> data = hr.getDataByMonth();
        assertEquals("The average in the tenth month should be 45",
            new Double(45), data.get(9));
    }

    @Test(timeout = TIMEOUT)
    public void test4FilterOnContaminantPPM() {
        HistoricalReport hr = new HistoricalReport(new Location("45", "45"),
            80000000.0, "contaminantPPM", "2011", mockPurityReports);

        ArrayList<Double> data = hr.getDataByMonth();

        assertEquals("the average in the tenth month should be 37.5",
            new Double(37.5), data.get(9));
    }
}