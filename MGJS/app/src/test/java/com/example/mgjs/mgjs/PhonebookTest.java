package com.example.mgjs.mgjs;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by sejin on 2016-12-22.
 */

public class PhonebookTest {
    Detail_PhoneNumberActivity checkDeleteSQL;
    PhoneBookActivity checkAddingRightStr;


    @Before
    public void setUpClass() throws Exception {
        checkDeleteSQL = new Detail_PhoneNumberActivity();
        checkAddingRightStr = new PhoneBookActivity();
    }

    @Test
    public void testSetInputRightSqlQuery(){
        int setId = 1;
        String expectedDeletedSqlStr = "";
        expectedDeletedSqlStr = "DELETE FROM phonebook WHERE _id=" +setId+";";

        assertEquals(expectedDeletedSqlStr, checkDeleteSQL.makeDeleteSQLquery(setId));
    }

    @Test
    public void testSetCheckRightStr(){
        String expectedDeletedSqlStr = "";
        expectedDeletedSqlStr = "NAME :\t\t\t\t\t\t\t\t\t\t\t"+ "sejin" + "\n" + "PHONE : \t\t\t\t\t\t\t\t" + "01000000000";

        assertEquals(expectedDeletedSqlStr,checkAddingRightStr.makeStrAddingInmData("sejin","01000000000"));

    }

}
