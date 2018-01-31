package net.jayde.study.kodejava.example.bean;

import java.beans.*;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BeanToXmlTransient {
    private Long id;
    private String itemName;
    private String itemColour;
    private Integer itemQuantities;

    static {
        try {
            //
            // In this block will change the attribute type of the itemQuantities
            // to transient so it will be not serialized to xml when we use
            // XMLEncode to convert the bean to xml persistence.
            //
            BeanInfo bi = Introspector.getBeanInfo(BeanToXmlTransient.class);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for (int i = 0; i < pds.length; i++) {
                PropertyDescriptor propertyDescriptor = pds[i];
                if (propertyDescriptor.getName().equals("itemQuantities")) {
                    propertyDescriptor.setValue("transient", Boolean.TRUE);
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BeanToXmlTransient bean = new BeanToXmlTransient();
        bean.setId(new Long(1));
        bean.setItemName("T-Shirt");
        bean.setItemColour("Dark Red");
        bean.setItemQuantities(new Integer(100));

        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream("BeanTransient.xml")));

            //
            // Write an XML representation of the specified object to the output.
            //
            encoder.writeObject(bean);
            encoder.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemColour() {
        return itemColour;
    }

    public void setItemColour(String itemColour) {
        this.itemColour = itemColour;
    }

    public Integer getItemQuantities() {
        return itemQuantities;
    }

    public void setItemQuantities(Integer itemQuantities) {
        this.itemQuantities = itemQuantities;
    }
}
