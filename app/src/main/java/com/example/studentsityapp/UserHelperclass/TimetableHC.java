package com.example.studentsityapp.UserHelperclass;

public class TimetableHC {
    private String period1;
    private String period2;
    private String period3;

    public TimetableHC() {
    }

    public TimetableHC(String period1, String period2, String period3) {
        this.period1 = period1;
        this.period2 = period2;
        this.period3 = period3;
    }

    public String getPeriod1() {
        return period1;
    }

    public void setPeriod1(String period1) {
        this.period1 = period1;
    }

    public String getPeriod2() {
        return period2;
    }

    public void setPeriod2(String period2) {
        this.period2 = period2;
    }

    public String getPeriod3() {
        return period3;
    }

    public void setPeriod3(String period3) {
        this.period3 = period3;
    }
}
