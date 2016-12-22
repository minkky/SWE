package com.example.mgjs.mgjs;

import android.content.Intent;

import org.junit.*;
import static org.junit.Assert.*;

public class MgjsTest {
    private ValidateUserActivity validateUserActivity;
    private ChangeUserActivity changeUserActivity;
    private String password, myinput_password, change_password, change_checkpassword;
    private Detail_PhoneNumberActivity checkDeleteSQL;
    private PhoneBookActivity checkAddingRightStr;
    private Add_ScheduleActivity scheduleAddition;
    private Add_NoteActivity noteAdd;
    private String content = "meeting";
    private int year = 2017;
    private int month = 12;
    private int day = 25;

    @Before
    public void setupClass() throws Exception {
        validateUserActivity = new ValidateUserActivity();
        changeUserActivity = new ChangeUserActivity();
        checkDeleteSQL = new Detail_PhoneNumberActivity();
        checkAddingRightStr = new PhoneBookActivity();
        scheduleAddition = new Add_ScheduleActivity();
        noteAdd = new Add_NoteActivity();
    }

    @Test
    public void testMatchPasswordInValidate() {
        password = "mgjs";
        myinput_password = "mgjs";
        boolean isMatchedPasswordFromValidate = validateUserActivity.matchPassword(password, myinput_password);

        assertTrue(isMatchedPasswordFromValidate);
    }

    @Test
    public void testEqualPasswordInChangeUser() {
        change_password = "mgjss";
        change_checkpassword = "mgjsss";
        boolean isMatchedTwoPasswordFromChange = changeUserActivity.checkEqualPassword(change_password, change_checkpassword);

        assertFalse(isMatchedTwoPasswordFromChange);
    }

    @Test
    public void testSetInputRightSqlQuery() {
        int setId = 1;
        String expectedDeletedSqlStr = "";
        expectedDeletedSqlStr = "DELETE FROM phonebook WHERE _id=" +setId+";";

        assertEquals(expectedDeletedSqlStr, checkDeleteSQL.makeDeleteSQLquery(setId));
    }

    @Test
    public void testSetCheckRightStr() {
        String expectedDeletedSqlStr = "";
        expectedDeletedSqlStr = "NAME :\t\t\t\t\t\t\t\t\t\t\t"+ "sejin" + "\n" + "PHONE : \t\t\t\t\t\t\t\t" + "01000000000";

        assertEquals(expectedDeletedSqlStr,checkAddingRightStr.makeStrAddingInmData("sejin","01000000000"));

    }

    @Test
    public void testConfirmInsertion() {
        String expectedStr = "meeting"+2017+12+25;
        String resultStr = content + year + month + day;

        assertTrue(scheduleAddition.confirmInsertion(expectedStr, resultStr));
    }

    @Test
    public void testSetSqlQuery() {
        String expectedSqlStr = "";
        expectedSqlStr = "INSERT INTO Schedule VALUES(null,'" + content + "'," + year + "," + month + "," + day + ");";

        assertEquals(expectedSqlStr, scheduleAddition.setSqlQuery(content, year, month, day));
    }

    @Test
    public void testConfirmGetRightNoteContent(){
        String noteContent = "noteContent";
        assertTrue(noteAdd.confirmGetRightNoteContent("noteContent",noteContent));
    }

    @Test
    public void testIsIntentNotNull(){
        Intent intent = null;
        assertFalse(noteAdd.isintentNotNull(intent));
    }
}
