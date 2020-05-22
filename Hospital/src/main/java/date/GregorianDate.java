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

    public GregorianDate(Date date) {
        String[] dateArr = date.toString().split("-");
        this.year = Integer.parseInt(dateArr[0]);
        this.month = Integer.parseInt(dateArr[1]);
        this.dayOfMonth = Integer.parseInt(dateArr[2]);
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Date getDate()throws Exception {
        if(this.year < 0 || (this.month < 0 || this.month > 12) || (this.dayOfMonth > 31 || this.dayOfMonth < 0))
            throw new Exception("Некорректно введена дата");
        Calendar calendar = new GregorianCalendar(this.year, this.month , this.dayOfMonth);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return year + "_" + month + "_" + dayOfMonth;
    }
}
