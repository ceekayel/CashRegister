package com.rocketmiles.hellochange;

import com.rocketmiles.hellochange.command.CommandType;
import com.rocketmiles.hellochange.command.Request;
import com.rocketmiles.hellochange.model.Drawer;
import com.rocketmiles.hellochange.model.DrawerBuilder;

import java.util.Scanner;

import static java.lang.System.exit;

public class CashRegister {

    private final Drawer drawer;

    private CashRegister() {
        this.drawer = DrawerBuilder.createDrawer();
    }

    public static void main(String[] args) {
        CashRegister register = new CashRegister();
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);

        System.out.println("ready");

        try {
            while (scanner.hasNextLine()) {
                String commandStr = scanner.nextLine();
                if (commandStr == null || commandStr.equals("")) {
                    continue;
                }
                try {
                    Request request = Request.createRequestFromString(commandStr);
                    String displayResult = register.handle(request);
                    System.out.println(displayResult);
                    //catch the quit command here to exit nicely
                    if (request.getCommandType().equals(CommandType.QUIT)) {
                        exit(0);
                    }
                } catch (TransactionValidationException tve) {
                    System.out.println(Errors.validationFailed);
                } catch (IllegalArgumentException iae) {
                    System.out.println(Errors.parseCommandFailed);
                }
            }
        } catch (Exception e)

        {
            e.printStackTrace();
            System.out.println(Errors.exceptionOccurred);
            exit(1);
        }

    }

    private String handle(Request r) {
        try {
            String retStr = r.execute(drawer);
            return retStr;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

}
