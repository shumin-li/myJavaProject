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


}
