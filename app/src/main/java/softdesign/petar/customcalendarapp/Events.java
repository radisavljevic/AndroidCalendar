package softdesign.petar.customcalendarapp;

import com.google.gson.annotations.SerializedName;

public class Events {
    @SerializedName("id")
    String ID;
    @SerializedName("event")
    String EVENT;
    @SerializedName("time")
    String TIME;
    @SerializedName("date")
    String DATE;
    @SerializedName("month")
    String MONTH;
    @SerializedName("year")
    String YEAR;

    public Events() {
    }

    public Events(String EVENT, String TIME, String DATE, String MONTH, String YEAR) {
        this.EVENT = EVENT;
        this.TIME = TIME;
        this.DATE = DATE;
        this.MONTH = MONTH;
        this.YEAR = YEAR;
    }

    public String getEVENT() {
        return EVENT;
    }

    public void setEVENT(String EVENT) {
        this.EVENT = EVENT;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getMONTH() {
        return MONTH;
    }

    public void setMONTH(String MONTH) {
        this.MONTH = MONTH;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    @Override
    public String toString() {
        return "Events{" +
                "ID='" + ID + '\'' +
                ", EVENT='" + EVENT + '\'' +
                ", TIME='" + TIME + '\'' +
                ", DATE='" + DATE + '\'' +
                ", MONTH='" + MONTH + '\'' +
                ", YEAR='" + YEAR + '\'' +
                '}';
    }
}
