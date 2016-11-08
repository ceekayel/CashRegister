package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.TestBase;
import com.rocketmiles.hellochange.TransactionValidationException;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Date: 11/7/16
 * Time: 5:02 PM
 */
public class ChangeDueDrawerCommandTest extends TestBase {

    ChangeDueDrawerCommand unitUnderTest;
    static final String BASE_REQUEST_STR = "changedue ";

    @Before
    public void setUp() {
        super.setUp();
        unitUnderTest = (ChangeDueDrawerCommand) DrawerCommandType.CHANGEDUE.getCommand();
    }

    @Test
    public void execute() {
        Request _request = Request.createRequestFromString(BASE_REQUEST_STR + "13");
        String retStr = unitUnderTest.execute(drawer, _request);
        assertEquals("0 1 0 0 3", retStr);
    }

    @Test(expected = TransactionValidationException.class)
    public void executeWithException() {
        //not enough money in drawer for this
        Request _request = Request.createRequestFromString(BASE_REQUEST_STR + "93");
        unitUnderTest.execute(drawer, _request);
    }

    @Test
    public void returnStr() {
        LinkedList<Integer> ls = new LinkedList<>();
        ls.add(3);
        ls.add(2);
        ls.add(1);
        ls.add(0);
        ls.add(0);
        String resStr = unitUnderTest.returnStr(ls);
        assertEquals("3 2 1 0 0", resStr);
    }

}