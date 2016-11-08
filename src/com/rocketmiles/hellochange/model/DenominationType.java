package com.rocketmiles.hellochange.model;

public enum DenominationType {

    TWENTY("20"),
    TEN("10"),
    FIVE("5"),
    TWO("2"),
    ONE("1");

    private Integer intValue;

    DenominationType(String s) {
        intValue = Integer.valueOf(s);
    }

    public Integer getIntValue() {
        return intValue;
    }
}
