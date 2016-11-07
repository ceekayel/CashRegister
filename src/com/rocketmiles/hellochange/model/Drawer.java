package com.rocketmiles.hellochange.model;

public interface Drawer {

    /**
     * Add some amount of a certain denomination,
     * @param d
     * @param amt
     * @return
     */
    Drawer add(DenominationType d, Integer amt);

    /**
     * Subtract some amount of a certain denomination,
     * throwing @link IllegalArgumentException if result becomes negative
     * @param d
     * @param amt
     * @return
     */
    Drawer subtract(DenominationType d, Integer amt);

    void cancel();

    /**
     * Commit the changes made
     * @return this modified instance
     */
    Drawer commit();

    int totalCashValue();

    String drawerContentsStr(String delimiter);

}
