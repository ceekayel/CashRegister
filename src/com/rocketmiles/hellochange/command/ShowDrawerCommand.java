package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

class ShowDrawerCommand extends AbstractDrawerCommand {

    public String execute(Drawer drawer, Request request) {
        return this.getResultStr(drawer);
    }

}
