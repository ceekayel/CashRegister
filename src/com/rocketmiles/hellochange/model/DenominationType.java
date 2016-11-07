package com.rocketmiles.hellochange.model;

public enum DenominationType {

    ONE("1"),
    TWO("2"),
    FIVE("5"),
    TEN("10"),
    TWENTY("20");

    DenominationType(String s) {
    }

    /**
     * Factory method throws @link IllegalArgumentException if it cannot find a match
     * @param s
     * @return
     */
    public static DenominationType fromString(String s) {
        DenominationType d = DenominationType.valueOf(s);
        if (d == null) {
            throw new IllegalArgumentException("No Denomination with text " + s + " found");
        }
        return d;
    }
}
