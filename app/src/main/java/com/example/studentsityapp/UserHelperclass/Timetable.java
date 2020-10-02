package com.example.studentsityapp.UserHelperclass;

public class Timetable {
    private String period;
    private String periodName;

    public Timetable() {
    }

    public Timetable(String period, String periodName) {
        this.period = period;
        this.periodName = periodName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }
}
