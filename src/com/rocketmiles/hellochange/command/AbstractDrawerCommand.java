package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

abstract class AbstractDrawerCommand implements DrawerCommand {

    /**
     * This returns a result string that includes the total in the drawer
     * @param drawer
     * @return
     */
    String getResultStr(Drawer drawer) {
        String ret = "$" +
                drawer.totalCashValue() +
                " " +
                drawer.drawerContentsStr(" ");
        return ret;
    }

    public abstract String execute(Drawer drawer, Request request);

}
