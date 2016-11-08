package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

import java.util.Collection;

class ChangeDrawerCommand extends ChangeDueDrawerCommand {

    /**
     * calculate change due and remove from the drawer
     *
     * @param drawer
     * @param request
     * @return
     */
    public String execute(Drawer drawer, Request request) {
        Collection<Integer> result = drawer.changeDue(request.getChangeAmount(), true);
        return returnStr(result);
    }

}
