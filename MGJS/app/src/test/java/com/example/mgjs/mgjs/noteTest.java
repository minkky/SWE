package com.example.mgjs.mgjs;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by 연가시 on 2016-12-22.
 */

public class noteTest {
    Add_NoteActivity noteAdd;
    NoteActivity note;


    @Before
    public void setUp() throws Exception {
        noteAdd = new Add_NoteActivity();
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
