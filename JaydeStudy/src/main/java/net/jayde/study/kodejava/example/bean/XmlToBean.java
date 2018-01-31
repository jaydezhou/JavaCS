package net.jayde.study.kodejava.example.bean;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;

public class XmlToBean {
    public static void main(String[] args) {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
                XmlToBean.class.getResourceAsStream("Bean.xml")));

        //
        // Reads the next object from the underlying input stream.
        //
        BeanToXML bean = (BeanToXML) decoder.readObject();
        decoder.close();

        System.out.println("ID              = " + bean.getId());
        System.out.println("Item Name       = " + bean.getItemName());
        System.out.println("Item Colour     = " + bean.getItemColour());
        System.out.println("Item Quantities = " + bean.getItemQuantities());
    }
}
