import main.java.date.GregorianDate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianDateTest {
    GregorianDate gregorianDate1 = new GregorianDate(2015,8,13);
    GregorianDate gregorianDate2 = new GregorianDate(2013,7,11);
    GregorianDate gregorianDate3 = new GregorianDate(2012,6,10);
    GregorianDate gregorianDate4 = new GregorianDate(2011,5,4);

    @Test
    public void getDate() throws Exception {
        Calendar calendar1 = new GregorianCalendar(2015, 8 , 13);
        Calendar calendar2 = new GregorianCalendar(2013, 7 , 11);
        Calendar calendar3 = new GregorianCalendar(2012,  6,10);
        Calendar calendar4 = new GregorianCalendar(2011, 5 , 4);
        Assert.assertEquals(calendar1.getTime(), gregorianDate1.getDate());
        Assert.assertEquals(calendar2.getTime(), gregorianDate2.getDate());
        Assert.assertEquals(calendar3.getTime(), gregorianDate3.getDate());
        Assert.assertEquals(calendar4.getTime(), gregorianDate4.getDate());
    }
} 