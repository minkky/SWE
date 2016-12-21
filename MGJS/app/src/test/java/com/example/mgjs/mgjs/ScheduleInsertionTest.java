package com.example.mgjs.mgjs;

import org.junit.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by 최정민 on 2016-12-21.
 */

public class ScheduleInsertionTest {
    public Add_ScheduleActivity scheduleAddition;

    @Before
    public void setUp() throws Exception {
        scheduleAddition = new Add_ScheduleActivity();
    }

    @Test
    public void testScheduleInsertion() {
        int schedule_year = 2017;
        int schedule_month = 12;
        int schedule_day = 25;
        String schedule_content = "meet my friends to do PA";
        String resultStr = schedule_content + schedule_year + schedule_month + schedule_day;

        scheduleAddition.InsertSchedule(schedule_content, schedule_year, schedule_month, schedule_day);
        assertEquals(resultStr,scheduleAddition.str);
    }
}
