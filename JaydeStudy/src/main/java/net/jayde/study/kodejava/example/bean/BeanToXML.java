package net.jayde.study.kodejava.example.bean;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BeanToXML {
    private Long id;
    private String itemName;
    private String itemColour;
    private Integer itemQuantities;

    public static void main(String[] args) {
        BeanToXML bean = new BeanToXML();
        bean.setId(new Long(1));
        bean.setItemName("T-Shirt");
        bean.setItemColour("Dark Red");
        bean.setItemQuantities(new Integer(100));

        try {
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
                    new FileOutputStream("Bean.xml")));

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
