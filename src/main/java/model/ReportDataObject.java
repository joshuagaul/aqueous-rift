package model;

import java.util.Date;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import classes.WaterSourceReport;
import classes.WaterPurityReport;
import classes.WaterType;
import classes.WaterCondition;
import classes.Location;

public class ReportDataObject {

    private Set<String> reports = new HashSet<>();
    private Map<String, WaterSourceReport> candidateReportMap = new HashMap<>();
    private Map<String, WaterPurityReport> confirmedReportMap = new HashMap<>();
    private AtomicInteger numCandidateReports = new AtomicInteger();
    private AtomicInteger numConfirmedReports = new AtomicInteger();
    private static final ReportDataObject INSTANCE = new ReportDataObject();

    /**
     * Private constructor for the ReportDataObject to implement
     * the Singleton pattern
     */
    private ReportDataObject() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated the"
                + "UserDataObject.  Please getInstance().");
        }
        DatabaseReference reports = getReports();
        DatabaseReference candidateReports = getCandidateReports();
        DatabaseReference confirmedReports = getConfirmedReports();
        candidateReports.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                int newSize = numCandidateReports.incrementAndGet();
                System.out.println("Added " + dataSnapshot.getKey()
                    + ", count is " + newSize);
                updateReports(dataSnapshot.getKey(), false);
                updateCandidateReports(dataSnapshot.getKey(),
                    (HashMap<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                int size = numCandidateReports.get()
                    + numConfirmedReports.get();
                System.out.println("Edited " + dataSnapshot.getKey()
                    + ", count is " + size);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //TODO
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,
                String prevChildKey) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("FireBase error:"
                    + databaseError.getMessage());
            }
        });
        confirmedReports.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                int newSize = numConfirmedReports.incrementAndGet();
                System.out.println("Added " + dataSnapshot.getKey()
                    + ", count is " + newSize);
                updateReports(dataSnapshot.getKey(), true);
                updateConfirmedReports(dataSnapshot.getKey(),
                    (HashMap<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,
                    String prevChildKey) {
                int size = numCandidateReports.get()
                    + numConfirmedReports.get();
                System.out.println("Edited " + dataSnapshot.getKey()
                    + ", count is " + size);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //TODO
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot,
                String prevChildKey) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("FireBase error:"
                    + databaseError.getMessage());
            }
        });
    }

    /**
     * Returns the singleton instance
     * @return UserDataObject singleton
     */
    public static ReportDataObject getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a reference to the "candidateReports" key in the database
     * @return candidateReports database reference
     */
    private DatabaseReference getCandidateReports() {
        return DataManager.getReference("/candidateReports");
    }

    /**
     * Returns a reference to the "confirmedReports" key in the database
     * @return confirmedReports database reference
     */
    private DatabaseReference getConfirmedReports() {
        return DataManager.getReference("/confirmedReports");
    }

    /**
     * Returns a reference to the "reports" key in the database
     * @return reports database reference
     */
    private DatabaseReference getReports() {
        return DataManager.getReference("/reports");
    }

    /**
     * Adds a candidate report to the firebase database
     * @param  reportToAdd - report instance to add
     */
    public void addCandidateReport(WaterSourceReport reportToAdd) {
        int reportId = reportToAdd.getNumber();
        DatabaseReference report = getCandidateReports().child("/" + reportId);
        report.setValue(reportToAdd);
    }

    /**
     * Adds a confirmed report to the firebase database
     * @param  reportToAdd - report instance to add
     */
    public void addConfirmedReport(WaterPurityReport reportToAdd) {
        // int reportId = reportToAdd.getNumber();
        // DatabaseReference report = getConfirmedReports().child("/"
        // + reportId);
        // report.setValue(reportToAdd);
    }

    /**
     * Determines if the queried report is in the database
     * @param  reportId The report to query
     * @return          True if the report exists in the database.
     */
    public Boolean reportExists(String reportId) {
        return reports.contains(reportId);
    }

    /**
     * Determines if the queried report is in confirmed or not.
     * Be sure to check if the report exists before using this method.
     *
     * @param  reportId The report to query
     * @return True if the report is confirmed, false if it is a candidate.
     */
    public Boolean reportConfirmed(String reportId) {
        return confirmedReportMap.containsKey(reportId);
    }

    /**
     * Returns the Report object mapped to the specified reportId.
     * A WaterSourceReport is returned because it represents a candidate report.
     *
     * @param  reportId The report key in the mapping
     * @return          WaterSourceReport object
     */
    public WaterSourceReport getCandidateReport(String reportId) {
        return candidateReportMap.get(reportId);
    }

    /**
     * Returns all saved candidateReport objects
     * A WaterSourceReport is returned because it represents a candidate report.
     *
     * @return     A map mapping String reportId and WaterSourceReport
     */
    public Map<String, WaterSourceReport> getAllCandidateReports() {
        return candidateReportMap;
    }

    /**
     * Returns the Report object mapped to the specified reportId.
     * A WaterPurityReport is returned because it represents a confirmed report.
     *
     * @param  reportId The report key in the mapping
     * @return          WaterPurityReport object
     */
    public WaterPurityReport getConfirmedReport(String reportId) {
        return confirmedReportMap.get(reportId);
    }

    /**
     * Overwrites the existing mapping associated with the reportId key
     * @param  reportToAdd New Report object to add to the database
     * @param  reportId Key to map the Report with
     */
    public void editCandidateReport(WaterSourceReport reportToAdd,
            String reportId) {
        addCandidateReport(reportToAdd);
    }

    /**
     * Helper method from an add callback in adding either type of Report
     * @param  reportId - key to update.
     * @param  confirmed - Determines whether the report is confirmed or not.
     */
    private void updateReports(String reportId, Boolean confirmed) {
        DatabaseReference reports = getReports();
        reports.child("/size").setValue(numConfirmedReports.get()
            + numCandidateReports.get());
        reports.child("/" + reportId).setValue(confirmed);
        this.reports.add(reportId);
    }

    /**
     * Updates local storage when a WaterSourceReport is edited
     * @param  reportId Key to map the Report object with
     * @param  objReport Data structure that FireBase returns
     */
    private void updateCandidateReports(String reportId,
            Map<String, Object> objReport) {
        try {
            Map<String, String> locationObj = (HashMap<String, String>)
                objReport.get("location");
            String lat = (String) locationObj.get("latitude");
            String lon = (String) locationObj.get("longitude");
            String dateString = (String) objReport.get("date");

            Date date = new java.text.SimpleDateFormat("mm/dd/"
                + "yyyy").parse(dateString);

            String reporterId = (String) objReport.get("reporterId");
            String type = (String) objReport.get("type");
            WaterType t = WaterType.valueOf(type);
            String condition = (String) objReport.get("condition");
            WaterCondition c = WaterCondition.valueOf(condition);
            Location loc = new Location(lat, lon);
            WaterSourceReport report = new WaterSourceReport(reporterId, loc,
                t, c, date);
            candidateReportMap.put(reportId, report);
        } catch (java.text.ParseException pe) {
            System.out.println(pe.getMessage());
        }
    }

    /**
     * TODO - updateConfirmedReports (very similar to above)
     * @param  reportId Key to map the Report object with
     * @param  objReport Data structure that FireBase returns
     */
    private void updateConfirmedReports(String reportId, Map<String,
            Object> objReport) {

    }
}
