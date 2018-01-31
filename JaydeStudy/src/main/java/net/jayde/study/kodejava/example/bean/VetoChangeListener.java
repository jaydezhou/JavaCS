package net.jayde.study.kodejava.example.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class VetoChangeListener implements VetoableChangeListener {
    /**
     * This method gets called when a constrained property is changed.
     *
     * @param evt a <code>PropertyChangeEvent</code> object describing the
     *            event source and the property that has changed.
     * @throws java.beans.PropertyVetoException
     *          if the recipient wishes the property
     *          change to be rolled back.
     */
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        String eventName = evt.getPropertyName();
        if (eventName.equalsIgnoreCase("interest")) {
            double interest = ((Double) evt.getNewValue()).doubleValue();
            if (interest > 20.00) {
                throw new PropertyVetoException("Interest must be below 20.00", evt);
            }
            System.out.println("Interest applied = " + interest);
        }
    }
}
