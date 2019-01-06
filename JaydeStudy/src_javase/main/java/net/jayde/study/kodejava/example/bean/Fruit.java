package net.jayde.study.kodejava.example.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;

public class Fruit implements Serializable {
    private Long id;
    private String name;
    private String latinName;
    private double price;

    public Fruit() {
    }

    public static void main(String[] args) {
        try {
            BeanInfo bi = Introspector.getBeanInfo(Fruit.class);
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                String propertyName = pd.getName();

                System.out.println("propertyName = " + propertyName);
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

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
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
