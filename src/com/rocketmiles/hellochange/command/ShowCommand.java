package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

class ShowCommand extends AbstractCommand {

    public String execute(Drawer drawer, Request request) {
        return this.getResultStr(drawer);
    }

}
