package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.DenominationType;
import com.rocketmiles.hellochange.model.Drawer;

class PutDrawerCommand extends AbstractDrawerCommand {

    public String execute(Drawer drawer, Request request) {
        for (DenominationType dt : request.orderedValuesKeySet()) {
            drawer.add(dt, request.getBillCountForDenomination(dt));
        }
        drawer.commit();
        return this.getResultStr(drawer);
    }

}
