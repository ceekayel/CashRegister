package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.DenominationType;
import com.rocketmiles.hellochange.model.Drawer;

class ChangeCommand extends ChangeDueCommand {

    /**
     * calculate change due and remove from the drawer
     * @param drawer
     * @param request
     * @return
     */
    public String execute(Drawer drawer, Request request) {

        for (DenominationType dt : request.orderedValuesKeySet()) {
            drawer.subtract(dt, request.getBillCountForDenomination(dt));
        }
        drawer.commit();
        return drawer.drawerContentsStr(" ");
    }

}
