package com.rocketmiles.hellochange.command;

public enum CommandType {

    CHANGE(new ChangeCommand()),
    CHANGEDUE(new ChangeDueCommand()),
    PUT(new PutCommand()),
    SHOW(new ShowCommand()),
    TAKE(new TakeCommand()),
    QUIT(new QuitCommand());

    private final Command command;

    CommandType(Command c) {
        this.command = c;
    }

    /**
     * Factory method throws @link IllegalArgumentException if it cannot find a match
     * @param s
     * @return
     */
    public static CommandType fromString(String s) {
        CommandType c = CommandType.valueOf(s.toUpperCase());
        return c;
    }

    public Command getCommand() {
        return command;
    }
}
