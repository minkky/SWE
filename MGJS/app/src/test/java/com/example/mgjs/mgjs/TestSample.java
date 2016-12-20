package com.example.mgjs.mgjs;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Created by minji on 2016. 12. 14..
 */

public class TestSample {
    private ValidateUserActivity validateUserActivity;
    String pw, checkpw;

    @Before
    public void setup(){
        validateUserActivity = new ValidateUserActivity();
    }

    @Test
    public void test(){
        pw = "mgjs";
        checkpw = "mgjs";
        boolean isMatched = validateUserActivity.matchPassword(pw, checkpw);

        if(isMatched) {
            assertTrue(true);
        }
    }

}
