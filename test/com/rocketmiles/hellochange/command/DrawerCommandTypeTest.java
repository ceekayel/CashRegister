package com.rocketmiles.hellochange.command;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Date: 11/8/16
 * Time: 7:31 AM
 */
public class DrawerCommandTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void fromStringWithException() {
        DrawerCommandType.fromString("gibberish");
    }

    @Test
    public void values() {
        assertArrayEquals(Arrays.asList(DrawerCommandType.CHANGE,
                DrawerCommandType.CHANGEDUE,
                DrawerCommandType.PUT,
                DrawerCommandType.SHOW,
                DrawerCommandType.TAKE,
                DrawerCommandType.QUIT).toArray(), DrawerCommandType.values());

    }

    @Test
    public void fromString() {
        assertEquals(DrawerCommandType.CHANGE, DrawerCommandType.fromString("change"));
        assertEquals(DrawerCommandType.CHANGEDUE, DrawerCommandType.fromString("changedue"));
        assertEquals(DrawerCommandType.PUT, DrawerCommandType.fromString("put"));
        assertEquals(DrawerCommandType.SHOW, DrawerCommandType.fromString("show"));
        assertEquals(DrawerCommandType.TAKE, DrawerCommandType.fromString("take"));
        assertEquals(DrawerCommandType.QUIT, DrawerCommandType.fromString("quit"));
    }

    @Test
    public void getCommand() {
        assertEquals(TakeDrawerCommand.class, DrawerCommandType.TAKE.getCommand().getClass());
    }

}