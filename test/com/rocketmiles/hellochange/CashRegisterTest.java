package com.rocketmiles.hellochange;

import com.rocketmiles.hellochange.command.Request;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Date: 11/8/16
 * Time: 7:58 AM
 */
public class CashRegisterTest {

    @Test
    public void handle() throws Exception {
        CashRegister cr = new CashRegister();
        Request request = Request.createRequestFromString("put 1 2 3 4 4");
        String resStr = cr.handle(request);
        assertEquals("$67 1 2 3 4 4", resStr);

    }

}