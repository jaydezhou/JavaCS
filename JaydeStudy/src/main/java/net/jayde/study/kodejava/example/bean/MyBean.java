package net.jayde.study.kodejava.example.bean;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class MyBean implements PropertyChangeListener, Serializable {
    private Long id;
    private String name;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MyBean() {
        pcs.addPropertyChangeListener(this);
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Name      = " + evt.getPropertyName());
        System.out.println("Old Value = " + evt.getOldValue());
        System.out.println("New Value = " + evt.getNewValue());
    }

    public static void main(String[] args) {
        MyBean bean = new MyBean();
        bean.setName("My Initial Value");
        bean.setName("My New Value");
        bean.setName("My Yet Another Value");
    }


    //~ --------------------------------------------- Bean's Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;

        //
        // Fires a property change event
        //
        pcs.firePropertyChange("name", oldValue, name);
    }
}
