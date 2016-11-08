package com.rocketmiles.hellochange.command;

public enum DrawerCommandType {

    CHANGE(new ChangeDrawerCommand()),
    CHANGEDUE(new ChangeDueDrawerCommand()),
    PUT(new PutDrawerCommand()),
    SHOW(new ShowDrawerCommand()),
    TAKE(new TakeDrawerCommand()),
    QUIT(new QuitCommand());

    private final DrawerCommand command;

    DrawerCommandType(DrawerCommand c) {
        this.command = c;
    }

    /**
     * Factory method throws @link IllegalArgumentException if it cannot find a match
     *
     * @param s
     * @return
     */
    public static DrawerCommandType fromString(String s) {
        return DrawerCommandType.valueOf(s.toUpperCase());
    }

    public DrawerCommand getCommand() {
        return command;
    }
}
