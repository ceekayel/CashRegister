package com.rocketmiles.hellochange.command;

import org.junit.Assert;
import org.junit.Test;

/**
 * Date: 11/8/16
 * Time: 6:03 AM
 */
public class QuitCommandTest {

    @Test
    public void execute() throws Exception {
        QuitCommand unitUnderTest = (QuitCommand) DrawerCommandType.QUIT.getCommand();
        Request _request = Request.createRequestFromString("quit");
        String retStr = unitUnderTest.execute(null, _request);
        Assert.assertEquals("Bye", retStr);
    }

}