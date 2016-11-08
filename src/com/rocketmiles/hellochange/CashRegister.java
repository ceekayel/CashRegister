package com.rocketmiles.hellochange;

import com.rocketmiles.hellochange.command.DrawerCommandType;
import com.rocketmiles.hellochange.command.Request;
import com.rocketmiles.hellochange.model.Drawer;
import com.rocketmiles.hellochange.model.DrawerBuilder;

import java.util.Scanner;

import static java.lang.System.exit;

public class CashRegister {

    private final Drawer drawer;

    CashRegister() {
        this.drawer = DrawerBuilder.buildDrawer();
    }

    public static void main(String[] args) {
        CashRegister register = new CashRegister();
        // create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in, "UTF8");

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
                    if (request.getCommandType().equals(DrawerCommandType.QUIT)) {
                        exit(0);
                    }
                } catch (TransactionValidationException tve) {
                    System.out.println(tve.getMessage());
                } catch (IllegalArgumentException iae) {
                    System.out.println(Errors.parseCommandFailed);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(Errors.exceptionOccurred);
            exit(1);
        }

    }

    String handle(Request r) {
        return r.execute(drawer);
    }

}
