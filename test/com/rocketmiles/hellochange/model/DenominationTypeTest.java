package com.rocketmiles.hellochange.model;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Date: 11/8/16
 * Time: 7:05 AM
 */
public class DenominationTypeTest {

    @Test
    public void values() {
        assertArrayEquals(Arrays.asList(DenominationType.TWENTY,
                DenominationType.TEN,
                DenominationType.FIVE,
                DenominationType.TWO,
                DenominationType.ONE).toArray(), DenominationType.values());

    }

    @Test
    public void getIntValue() {
        assertEquals(Integer.valueOf(20), DenominationType.TWENTY.getIntValue());
        assertEquals(Integer.valueOf(10), DenominationType.TEN.getIntValue());
        assertEquals(Integer.valueOf(5), DenominationType.FIVE.getIntValue());
        assertEquals(Integer.valueOf(2), DenominationType.TWO.getIntValue());
        assertEquals(Integer.valueOf(1), DenominationType.ONE.getIntValue());
    }

}