package com.example.mgjs.mgjs;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by 최정민 on 2016-12-21.
 */

public class ScheduleInsertionTest {
    Add_ScheduleActivity scheduleAddition;
    String content = "meeting";
    int year = 2017;
    int month = 12;
    int day = 25;

    @Before
    public void setUpClass() throws Exception {
        scheduleAddition = new Add_ScheduleActivity();
    }

    @Test
    public void testConfirmInsertion(){
        String expectedStr = "meeting"+2017+12+25;
        String resultStr = content + year + month + day;

        assertTrue(scheduleAddition.confirmInsertion(expectedStr, resultStr));
    }

    @Test
    public void testSetSqlQuery(){
        String expectedSqlStr = "";
        expectedSqlStr = "INSERT INTO Schedule VALUES(null,'" + content + "'," + year + "," + month + "," + day + ");";

        assertEquals(expectedSqlStr, scheduleAddition.setSqlQuery(content, year, month, day));
    }
}
