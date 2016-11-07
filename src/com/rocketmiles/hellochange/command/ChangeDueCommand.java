package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.Errors;
import com.rocketmiles.hellochange.TransactionValidationException;
import com.rocketmiles.hellochange.model.Drawer;

import java.util.Collection;

class ChangeDueCommand extends AbstractCommand {

    /**
     * Calculate the change due but don't remove from the drawer
     * @param drawer
     * @param request
     * @return
     */
    public String execute(Drawer drawer, Request request) {
        Collection<Integer> result = calculateChangeDue(drawer, request.getChangeAmount()) ;
        return "TODO";
    }

    Collection<Integer> calculateChangeDue(Drawer drawer, Integer changeAmount) {
        if (drawer.totalCashValue() < changeAmount) {
            throw new TransactionValidationException(Errors.cantMakeChange);
        }
        //TODO
        return null;
    }


}
