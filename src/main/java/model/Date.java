package model;

import java.util.Calendar;

public class Date {
    private int year, month, date;
    private String day;
    private String[] strMonths = new String[] { "Jan"
                                                , "Feb"
                                                , "Mar"
                                                , "Apr"
                                                , "May"
                                                , "Jun"
                                                , "Jul"
                                                , "Aug"
                                                , "Sep"
                                                , "Oct"
                                                , "Nov"
                                                , "Dec" };
    private String[] strDays = new String[] { "Sunday"
                                            , "Monday"
                                            , "Tuesday"
                                            , "Wednesday"
                                            , "Thusday"
                                            , "Friday"
                                            , "Saturday" };
    public Date() {
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH) + 1;
        date = now.get(Calendar.DATE);
        day = strDays[now.get(Calendar.DAY_OF_WEEK) - 1];
    }
    public Date(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getYear() {
        return year;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public String getMonth() {
        return strMonths[month - 1];
    }
    public void setDate(int date) {
        this.date = date;
    }
    public int getDate() {
        return date;
    }
    public String getDay() {
        return day;
    }
    public String toString() {
        return month + "-" + date + "-" + year;
    }
}
