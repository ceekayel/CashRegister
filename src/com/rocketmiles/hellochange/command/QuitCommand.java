package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

class QuitCommand extends AbstractCommand implements Command {

    public String execute(Drawer drawer, Request request) {
        return "Bye";
    }

}
