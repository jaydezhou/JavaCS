package net.jayde.study.kodejava.example.lang;

import java.lang.reflect.Field;
import java.util.Date;

public class GetSetFieldDemo {
    public static Date now;
    public Long id;
    public String name;

    public static void main(String[] args) {
        GetSetFieldDemo demo = new GetSetFieldDemo();
        Class clazz = demo.getClass();

        try {
            //
            // Get field id, set it value and read it back
            //
            Field field = clazz.getField("id");
            field.set(demo, new Long(10));
            Object value = field.get(demo);
            System.out.println("Value = " + value);

            //
            // Get static field date, set it value and read it back
            //
            field = clazz.getField("now");
            field.set(null, new Date());
            value = field.get(null);
            System.out.println("Value = " + value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
