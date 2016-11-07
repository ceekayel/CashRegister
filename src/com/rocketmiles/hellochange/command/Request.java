package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.TransactionValidationException;
import com.rocketmiles.hellochange.model.DenominationType;
import com.rocketmiles.hellochange.model.Drawer;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Request {

    private CommandType commandType = null;
    private final Map<DenominationType, Integer> valuesMap = new LinkedHashMap<>();
    private Integer changeAmount = null;

    private Request() {
    }

    private Request(String s) {
        String[] cmdLine = s.split(" ");
        if (!validateCommandLine(s)) {
            throw new TransactionValidationException("Cannot read command line: " + s);
        }
        initialize(cmdLine);

    }

    public static Request createRequestFromString(String s) {
        Request r = new Request(s);
        return r;
    }

    /**
     * helper method to set up the numeric portion of command line
     * @param cmdLine
     */
    private void initialize(String[] cmdLine) {
        this.commandType = CommandType.fromString(cmdLine[0]);
        if (commandType.equals(CommandType.CHANGE)) {
            this.changeAmount = new Integer(cmdLine[1]);
        } else if (cmdLine.length == 6) {
            valuesMap.put(DenominationType.TWENTY, new Integer(cmdLine[1]));
            valuesMap.put(DenominationType.TEN, new Integer(cmdLine[2]));
            valuesMap.put(DenominationType.FIVE, new Integer(cmdLine[3]));
            valuesMap.put(DenominationType.TWO, new Integer(cmdLine[4]));
            valuesMap.put(DenominationType.ONE, new Integer(cmdLine[5]));
        }
    }

    /**
     * Checks for null and number of args
     * @param s the command line string
     * @return false if not valid
     */
    boolean validateCommandLine(String s) {
        if (s == null) {
            return false;
        }
        String[] cmdLine = s.split(" ");
        if (cmdLine == null || cmdLine.length < 1 || cmdLine.length > 6) {
            return false;
        }
        return true;
    }

    /**
     * Cover method for internal implementation of valuesMap
     * @return
     */
    Set<DenominationType> orderedValuesKeySet()
    {
        Set<DenominationType> defensiveCopy = new LinkedHashSet<>();
        defensiveCopy.addAll(valuesMap.keySet());
        return defensiveCopy;
    }

    Integer getBillCountForDenomination(DenominationType d) {
        return valuesMap.get(d);
    }

    public Command getCommand() {
        return commandType.getCommand();
    }

    public String execute(Drawer d) {
        String ret = getCommand().execute(d, this);
        return ret;
    }

    Integer getChangeAmount() {
        return changeAmount;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
