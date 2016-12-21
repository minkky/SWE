package com.example.mgjs.mgjs;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import org.junit.*;
import static org.junit.Assert.*;

public class LoginTest {
    private ValidateUserActivity validateUserActivity;
    private ChangeUserActivity changeUserActivity;
    private String password, myinput_password, change_password, change_checkpassword;

    @Before
    public void setup() {
        validateUserActivity = new ValidateUserActivity();
        changeUserActivity = new ChangeUserActivity();
    }

    @Test
    public void matchPasswordTest(){
        password = "mgjs";
        myinput_password = "mgjs";
        boolean isMatchedPasswordFromValidate = validateUserActivity.matchPassword(password, myinput_password);

        assertTrue(isMatchedPasswordFromValidate);
    }

    @Test
    public void equalPasswordTest(){
        change_password = "mgjss";
        change_checkpassword = "mgjsss";
        boolean isMatchedTwoPasswordFromChange = changeUserActivity.checkEqualPassword(change_password, change_checkpassword);

        assertFalse(isMatchedTwoPasswordFromChange);
    }

}
