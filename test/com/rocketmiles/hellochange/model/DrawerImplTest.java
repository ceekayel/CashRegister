package com.rocketmiles.hellochange.model;

import com.rocketmiles.hellochange.TestBase;
import com.rocketmiles.hellochange.TransactionValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Date: 11/7/16
 * Time: 5:03 PM
 */
public class DrawerImplTest extends TestBase {

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void add() {
        drawer.add(DenominationType.FIVE, 2);
        drawer.commit();
        Assert.assertEquals("0 3 5 0 10", drawer.drawerContentsStr(" "));
    }

    @Test
    public void subtract() {
        drawer.subtract(DenominationType.FIVE, 2);
        drawer.commit();
        Assert.assertEquals("0 3 1 0 10", drawer.drawerContentsStr(" "));
    }

    @Test(expected = TransactionValidationException.class)
    public void subtractWithException() {
        drawer.subtract(DenominationType.FIVE, 7);
    }

    @Test
    public void cancel() {
        drawer.add(DenominationType.FIVE, 2);
        drawer.cancel();
        Assert.assertEquals("0 3 3 0 10", drawer.drawerContentsStr(" "));
    }

    @Test
    public void commit() {
        drawer.add(DenominationType.FIVE, 2);
        Assert.assertEquals("0 3 3 0 10", drawer.drawerContentsStr(" "));
        drawer.commit();
        Assert.assertEquals("0 3 5 0 10", drawer.drawerContentsStr(" "));
    }

    @Test
    public void totalCashValue() {
        Assert.assertEquals(55, drawer.totalCashValue());
    }

    @Test
    public void drawerContentsStr() {
        Assert.assertEquals("0 3 3 0 10", drawer.drawerContentsStr(" "));
    }

}