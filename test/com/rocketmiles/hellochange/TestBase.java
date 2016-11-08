package com.rocketmiles.hellochange;

import com.rocketmiles.hellochange.model.DenominationType;
import com.rocketmiles.hellochange.model.Drawer;
import com.rocketmiles.hellochange.model.DrawerBuilder;

public abstract class TestBase {

    protected Drawer drawer;

    private void initializeDrawer() {
        drawer = DrawerBuilder.buildDrawer();
        drawer.add(DenominationType.TEN, 3);
        drawer.add(DenominationType.FIVE, 3);
        drawer.add(DenominationType.ONE, 10);
        drawer.commit();
    }

    public void setUp() {
        initializeDrawer();
    }

}
