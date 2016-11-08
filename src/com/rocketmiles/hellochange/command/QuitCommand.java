package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

class QuitCommand implements DrawerCommand {

    public String execute(Drawer d, Request request) {
        return "Bye";
    }

}
