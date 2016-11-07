package com.rocketmiles.hellochange.model;

import com.rocketmiles.hellochange.Errors;
import com.rocketmiles.hellochange.TransactionValidationException;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class DrawerImpl implements Drawer {

    private Map<DenominationType, Integer> contents = new LinkedHashMap<>();
    private Map<DenominationType, Integer> contentsUpdated = new LinkedHashMap<>();

    /**
     * Concrete Drawer instance with ordered values in Map
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

    private Map<DenominationType, Integer> clone(Map<DenominationType, Integer> other) {
        Map<DenominationType, Integer> clone = new LinkedHashMap<>();
        clone.put(DenominationType.TWENTY,
                other.get(DenominationType.TWENTY));
        clone.put(DenominationType.TEN,
                other.get(DenominationType.TEN));
        clone.put(DenominationType.FIVE,
                other.get(DenominationType.FIVE));
        clone.put(DenominationType.TWO,
                other.get(DenominationType.TWO));
        clone.put(DenominationType.ONE,
                other.get(DenominationType.ONE));
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
        int totalValue = contents.get(DenominationType.TWENTY) * 20
                + contents.get(DenominationType.TEN) * 10
                + contents.get(DenominationType.FIVE) * 5
                + contents.get(DenominationType.TWO) * 2
                + contents.get(DenominationType.ONE) * 1
                ;
        return totalValue;
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
