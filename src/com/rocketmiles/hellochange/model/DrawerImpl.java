package com.rocketmiles.hellochange.model;

import com.rocketmiles.hellochange.Errors;
import com.rocketmiles.hellochange.TransactionValidationException;

import java.util.*;

class DrawerImpl implements Drawer {

    Map<DenominationType, Integer> contents = new LinkedHashMap<>();
    Map<DenominationType, Integer> contentsUpdated = new LinkedHashMap<>();

    /**
     * Concrete Drawer instance with insertion-ordered values in Map
     */
    DrawerImpl() {
        contents.put(DenominationType.TWENTY, 0);
        contents.put(DenominationType.TEN, 0);
        contents.put(DenominationType.FIVE, 0);
        contents.put(DenominationType.TWO, 0);
        contents.put(DenominationType.ONE, 0);
        contentsUpdated.put(DenominationType.TWENTY, 0);
        contentsUpdated.put(DenominationType.TEN, 0);
        contentsUpdated.put(DenominationType.FIVE, 0);
        contentsUpdated.put(DenominationType.TWO, 0);
        contentsUpdated.put(DenominationType.ONE, 0);
    }

    @Override
    public Drawer add(DenominationType d, Integer amt) {
        Integer currentAmount = contentsUpdated.get(d);
        Integer newAmount = currentAmount + amt;
        contentsUpdated.put(d, newAmount);
        return this;
    }

    @Override
    public Drawer subtract(DenominationType d, Integer amt) {
        Integer currentAmount = contentsUpdated.get(d);
        Integer newAmount = currentAmount - amt;

        if (newAmount < 0) {
            this.cancel();
            throw new TransactionValidationException(Errors.cantMakeChange);
        }
        contentsUpdated.put(d, newAmount);
        return this;
    }

    @Override
    public void cancel() {
        //rollback
        this.contentsUpdated = this.clone(contents);
    }

    /**
     * Clone operation - maintains insertion-order
     *
     * @param other object to clone
     * @return
     */
    private Map<DenominationType, Integer> clone(Map<DenominationType, Integer> other) {
        Map<DenominationType, Integer> clone = new LinkedHashMap<>();
        for (DenominationType d : other.keySet()) {
            clone.put(d, other.get(d));
        }
        return clone;
    }

    @Override
    public Drawer commit() {
        // Make update contents the current contents, then clone it
        this.contents = this.contentsUpdated;
        this.contentsUpdated = this.clone(contents);
        return this;
    }

    @Override
    public int totalCashValue() {
        int accum = 0;
        for (DenominationType d : contents.keySet()) {
            accum += contents.get(d) * d.getIntValue();
        }
        return accum;
    }

    /**
     * Calculates what change can be made for this amount
     * commits changes if commit is TRUE
     *
     * @param changeAmount the desired amount of change total
     * @return insertion-ordered set of bill counts per denomination
     */
    @Override
    public Collection<Integer> changeDue(Integer changeAmount, boolean commit) {
        if (this.totalCashValue() < changeAmount) {
            //not enough money in drawer
            throw new TransactionValidationException(Errors.cantMakeChange);
        }
        //start with largest denominations and work down.
        //track the amount obtained and count down toward zero (done)
        int countDown = changeAmount;
        Collection<Integer> fistFullOfDollars = new ArrayList<>();
        for (DenominationType d : contentsUpdated.keySet()) {
            //see if there's any money in drawer of this denomination
            //OR we've reached our goal
            if (d.getIntValue() == 0 || countDown == 0) {
                fistFullOfDollars.add(0);
                continue;
            }

            int billsNeeded = countDown / d.getIntValue();
            int billsInDrawer = contentsUpdated.get(d);
            if (billsInDrawer < billsNeeded) {
                billsNeeded = billsInDrawer;
            }
            countDown -= billsNeeded * d.getIntValue();
            fistFullOfDollars.add(billsNeeded);
            this.subtract(d, billsNeeded);
        }
        if (countDown > 0) {
            //not the right combination of denominations in drawer
            this.cancel();
            throw new TransactionValidationException(Errors.cantMakeChange);
        }
        //commit the change if requested, otherwise cancel to reset the state
        if (commit) {
            this.commit();
        } else {
            this.cancel();
        }
        return fistFullOfDollars;
    }

    @Override
    public String drawerContentsStr(String delimiter) {
        Iterator iter = contents.values().iterator();
        StringBuilder buffer = new StringBuilder(iter.next().toString());
        while (iter.hasNext()) {
            buffer.append(delimiter).append(iter.next());
        }
        return buffer.toString();
    }
}
