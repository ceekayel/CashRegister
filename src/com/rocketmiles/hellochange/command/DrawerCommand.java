package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

interface DrawerCommand {

    String execute(Drawer drawer, Request request);

}
