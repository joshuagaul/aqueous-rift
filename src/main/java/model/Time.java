package model;

public class Time {
    private long milliSeconds, totalSeconds, currentSeconds;
    private long totalMinutes, currentMinutes;
    private long totalHours, currentHours;
    private String time;

    public Time() {
        milliSeconds = System.currentTimeMillis();
        totalSeconds = milliSeconds / 1000;
        currentSeconds = totalSeconds % 60;
        totalMinutes = totalSeconds / 60;
        currentMinutes = totalMinutes % 60;
        totalHours = totalMinutes / 60;
        currentHours = totalHours % 24;
        if (currentHours == 0 || currentHours - 12 < 0) {
            time = "AM"
                + (currentHours % 12) + ":"
                + currentMinutes + ":"
                + currentSeconds;
        } else {
            time = "PM"
                + (currentHours % 12) + ":"
                + currentMinutes + ":"
                + currentSeconds;
        }
    }
    public Time(long hours, long minutes, long seconds) {
        currentSeconds = seconds;
        currentMinutes = minutes;
        currentHours = hours;
    }
    public void setSeconds (long seconds) {
        currentSeconds = seconds;
    }
    public long getSeconds () {
        return currentSeconds;
    }
    public void setMinutes (long minutes) {
        currentMinutes = minutes;
    }
    public long getMinutes () {
        return currentMinutes;
    }
    public void setHours (long hours) {
        currentHours = hours;
    }
    public long getHours () {
        return currentHours;
    }
    public void setTime(long hours, long minutes, long seconds) {
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
        if (currentHours == 0 || currentHours - 12 < 0) {
            time = "AM"
                + (currentHours % 12) + ":"
                + currentMinutes + ":"
                + currentSeconds;
        } else {
            time = "PM"
                + (currentHours % 12) + ":"
                + currentMinutes + ":"
                + currentSeconds;
        }

    }
    public String toString() {
        return time;
    }
}
