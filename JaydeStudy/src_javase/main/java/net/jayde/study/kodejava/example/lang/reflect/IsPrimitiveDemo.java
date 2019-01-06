
package net.jayde.study.kodejava.example.lang.reflect;

public class IsPrimitiveDemo {
    public static void main(String[] args) {
        IsPrimitiveDemo.get(int.class);
        IsPrimitiveDemo.get(String.class);
        IsPrimitiveDemo.get(double.class);
        IsPrimitiveDemo.get(void.class);
    }

    private static void get(Class clazz) {
        if (clazz.isPrimitive()) {
            System.out.println(clazz.getName() +
                    " is a primitive type.");
        } else {
            System.out.println(clazz.getName() +
                    " is not a primitive type.");            
        }
    }
}
