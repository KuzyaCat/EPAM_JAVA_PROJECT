package main.java.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GregorianDate {
    private int year;
    private int month;
    private int dayOfMonth;

    public GregorianDate(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public GregorianDate() {
        this(1970, 1, 1);
    }

    public Date getDate() {
        Calendar calendar = new GregorianCalendar(this.year, this.month , this.dayOfMonth);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return year + "_" + month + "_" + dayOfMonth;
    }

    public GregorianDate parseString(String str) {
        String[] strArr = str.split("_");

        int year = Integer.parseInt(strArr[0]);
        int month = Integer.parseInt(strArr[1]);
        int dayOfMonth = Integer.parseInt(strArr[2]);

        return new GregorianDate(year, month, dayOfMonth);
    }
}
