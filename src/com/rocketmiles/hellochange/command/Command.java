package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.model.Drawer;

public interface Command {

    String execute(Drawer drawer, Request request);

}
