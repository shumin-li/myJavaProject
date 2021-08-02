package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdayTest {

    private Jday jday;
    private Jday jdayNoSet;

    @BeforeEach
    void runBefore() {
        jday = new Jday(2021,7,30,11,20);
        jdayNoSet = new Jday();
    }

    @Test
    void testJdaySetter() {
        double jdayDouble = 738368.5125;
        jdayNoSet.setJday(jdayDouble);
        assertEquals(jdayDouble, jdayNoSet.getJday());
    }

    @Test
    void testJdayGetters() {
        assertEquals(2021, jday.getYear());
        assertEquals(7,jday.getMonth());
        assertEquals(30,jday.getDay());
        assertEquals(11, jday.getHour());
        assertEquals(20, jday.getMinute());
    }

    @Test
    void testSecondConstructor() {
        jday = new Jday(2021,12,13,14,15);
        double jdayDouble = jday.getJday();
        Jday jday2 = new Jday(jdayDouble);

        assertEquals(15, jday2.getMinute());

    }

    @Test
    void testGetDatevec() {
        int[] datevec = jday.getDatevec();
        assertEquals(5,datevec.length);
        assertEquals(20, datevec[4]);

    }

    @Test
    void testInLeapYear() {
        jday = new Jday(2020, 4, 6, 12, 13);
        assertEquals("April 6, 2020, 12:13:00",jday.getJdayString());
    }

    @Test
    void testDateInJanuary() {
        jday = new Jday(2020, 1, 6, 12, 13);
        assertEquals("January 6, 2020, 12:13:00",jday.getJdayString());
        assertEquals("January 6, 2020, 12:13:00",jday.getJdayString(jday.getJday()));


    }

    @Test
    void testGetJdayStringHourMinute() {
        assertEquals("11:20", jday.getJdayStringHourMinute());
        assertEquals("11:20", jday.getJdayStringHourMinute(jday.getJday()));

    }


}
