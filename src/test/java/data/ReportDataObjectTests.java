package data;

import classes.Location;
import classes.OverallCondition;
import classes.WaterPurityReport;
import classes.WaterSourceReport;
import classes.WaterType;
import classes.WaterCondition;
import model.DataManager;
import model.ReportDataObject;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.Map;


public class ReportDataObjectTests {
    private ReportDataObject reportDAO;
    private WaterPurityReport testPReport;
    private WaterSourceReport testSReport;

    @Before
    public void initialize() throws Exception {

    }

    @Test
    public void checkAddReportWorks() {
        DataManager.initializeFireBase();
        reportDAO = ReportDataObject.getInstance();
        Map pMap = reportDAO.getAllPurityReports();
        Map sMap = reportDAO.getAllSourceReports();

        testPReport = new WaterPurityReport("id", new Date(),
                new Location("10", "10"), OverallCondition.Safe, 0, 0);
        testSReport = new WaterSourceReport("id", new Location("10", "10"),
                WaterType.Bottled, WaterCondition.Potable, new Date());
        assertFalse(reportDAO.reportExists(testPReport.getId()));
        assertFalse(reportDAO.reportExists(testSReport.getId()));
        reportDAO.addPurityReport(testPReport);
        reportDAO.addSourceReport(testSReport);
        assertNotNull(reportDAO.getAllPurityReports());
        assertNotNull(reportDAO.getAllSourceReports());
        assertTrue(reportDAO.reportExists(testPReport.getId()));
        assertTrue(reportDAO.reportExists(testSReport.getId()));
        assertEquals(pMap.size() + 1,
                reportDAO.getAllPurityReports().size());
        assertEquals(sMap.size() + 1, reportDAO.getAllSourceReports().size());
    }

    @Test
    public void checkGetReportWorks() {
        assertNotNull(reportDAO.getPurityReport(testPReport.getId()));
        assertNotNull(reportDAO.getPurityReport(testSReport.getId()));
    }

    @Test
    public void checkEditReportWorks() {
        String pID = testPReport.getReporterId();
        String sID = testSReport.getReporterId();
        WaterPurityReport newTestPReport =
                new WaterPurityReport("newid", new Date(),
                        new Location("10", "10"), OverallCondition.Safe, 0, 0);
        WaterSourceReport newTestSReport =
                new WaterSourceReport("newid", new Location("10", "10"),
                        WaterType.Bottled, WaterCondition.Potable, new Date());
        reportDAO.editPurityReport(newTestPReport, testPReport.getId());
        reportDAO.editSourceReport(newTestSReport, testSReport.getId());
        assertNotSame(newTestPReport.getReporterId(), pID);
        assertNotSame(newTestSReport.getReporterId(), sID);
    }

    @Test
    public void checkRemoveReportWorks() {
        int pcount = reportDAO.getAllPurityReports().size();
        int scount = reportDAO.getAllSourceReports().size();
        assertTrue(reportDAO.reportExists(testPReport.getId()));
        assertTrue(reportDAO.reportExists(testSReport.getId()));
        reportDAO.deleteSourceReport(testPReport.getId());
        reportDAO.deletePurityReport(testPReport.getId());
        assertFalse(reportDAO.reportExists(testPReport.getId()));
        assertFalse(reportDAO.reportExists(testSReport.getId()));
        assertEquals(pcount - 1, reportDAO.getAllPurityReports().size());
        assertEquals(scount - 1, reportDAO.getAllSourceReports().size());
    }
}
