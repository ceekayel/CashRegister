package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

import java.util.Collection;
import java.util.Iterator;

class ChangeDueDrawerCommand extends AbstractDrawerCommand {

    /**
     * Calculate the change due but don't remove from the drawer
     *
     * @param drawer
     * @param request
     * @return
     */
    public String execute(Drawer drawer, Request request) {
        Collection<Integer> result = drawer.changeDue(request.getChangeAmount(), false);
        return returnStr(result);
    }

    String returnStr(Collection<Integer> result) {
        Iterator iter = result.iterator();
        StringBuilder buffer = new StringBuilder(iter.next().toString());
        while (iter.hasNext()) {
            buffer.append(" ").append(iter.next());
        }
        return buffer.toString();
    }

}
