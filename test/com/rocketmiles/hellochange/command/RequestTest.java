package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.TestBase;
import com.rocketmiles.hellochange.model.DenominationType;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Date: 11/8/16
 * Time: 6:29 AM
 */
public class RequestTest extends TestBase {

    Request unitUnderTest;

    @Before
    public void setUp() {
        super.setUp();
        unitUnderTest = Request.createRequestFromString("put 1 2 3 4 4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createRequestFromStringWithException() {
        unitUnderTest = Request.createRequestFromString("gibberish");
    }

    @Test
    public void validateCommandLine() {
        boolean valid = unitUnderTest.validateCommandLine("take 0 0 0 0 1");
        assertTrue(valid);
    }

    @Test
    public void orderedValuesKeySet() {
        assertEquals(new LinkedHashSet<>(Arrays.asList(DenominationType.TWENTY,
                DenominationType.TEN,
                DenominationType.FIVE,
                DenominationType.TWO,
                DenominationType.ONE)), unitUnderTest.orderedValuesKeySet());
    }

    @Test
    public void getBillCountForDenomination() {
        int amt = unitUnderTest.getBillCountForDenomination(DenominationType.FIVE);
        assertEquals(3, amt);
    }

    @Test
    public void getCommand() {
        assertEquals(PutDrawerCommand.class, unitUnderTest.getCommand().getClass());
    }

    @Test
    public void execute() {
        //this tests the various command operations as well
        //TODO should have individual test classes per command
        Request request = Request.createRequestFromString("put 1 2 3 4 4");
        assertEquals("$122 1 5 6 4 14", request.execute(drawer));
        request = Request.createRequestFromString("changedue 1");
        assertEquals("0 0 0 0 1", request.execute(drawer));
        request = Request.createRequestFromString("show");
        assertEquals("$122 1 5 6 4 14", request.execute(drawer));
        request = Request.createRequestFromString("change 1");
        assertEquals("0 0 0 0 1", request.execute(drawer));
        request = Request.createRequestFromString("take 0 0 0 0 1");
        assertEquals("$120 1 5 6 4 12", request.execute(drawer));
    }

    @Test
    public void getChangeAmount() {
        assertEquals(null, unitUnderTest.getChangeAmount());
        unitUnderTest = Request.createRequestFromString("change 4");
        assertEquals(Integer.valueOf(4), unitUnderTest.getChangeAmount());
    }

    @Test
    public void getCommandType() {
        assertEquals(DrawerCommandType.PUT, unitUnderTest.getCommandType());
    }

}