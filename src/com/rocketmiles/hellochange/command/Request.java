package com.rocketmiles.hellochange.command;

import com.rocketmiles.hellochange.Errors;
import com.rocketmiles.hellochange.TransactionValidationException;
import com.rocketmiles.hellochange.model.DenominationType;
import com.rocketmiles.hellochange.model.Drawer;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Request {

    private DrawerCommandType commandType = null;
    private final Map<DenominationType, Integer> valuesMap = new LinkedHashMap<>();
    private Integer changeAmount = null;

    private Request() {
    }

    private Request(String s) {
        String[] cmdLine = s.split(" ");
        if (!validateCommandLine(s)) {
            throw new TransactionValidationException(Errors.validationFailed);
        }
        initialize(cmdLine);

    }

    public static Request createRequestFromString(String s) {
        return new Request(s);
    }

    /**
     * helper method to set up the numeric portion of command line
     * @param cmdLine
     */
    private void initialize(String[] cmdLine) {
        this.commandType = DrawerCommandType.fromString(cmdLine[0]);
        if (commandType.equals(DrawerCommandType.CHANGE) || commandType.equals(DrawerCommandType.CHANGEDUE)) {
            this.changeAmount = Integer.valueOf(cmdLine[1]);
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
        if (cmdLine.length < 1 || cmdLine.length > 6) {
            return false;
        }
        //make sure we have a sensible command
        DrawerCommandType.fromString(cmdLine[0]);
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

    public DrawerCommand getCommand() {
        return commandType.getCommand();
    }

    public String execute(Drawer d) {
        return getCommand().execute(d, this);
    }

    Integer getChangeAmount() {
        return changeAmount;
    }

    public DrawerCommandType getCommandType() {
        return commandType;
    }
}
