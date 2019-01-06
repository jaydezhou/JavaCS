
package net.jayde.study.kodejava.example.lang.reflect;

public class ComponentTypeDemo {
    public static void main(String[] args) {
        String[] words = {"and", "the"};
        int[][] matrix = {{1, 1}, {2, 1}};
        Double number = 10.0;

        Class clazz = words.getClass();
        Class cls = matrix.getClass();
        Class clz = number.getClass();

        //
        // Gets the type of an array component.
        //
        Class type = clazz.getComponentType();
        System.out.println("Words type: " +
                type.getCanonicalName());

        //
        // Gets the type of an array component.
        //
        Class matrixType = cls.getComponentType();
        System.out.println("Matrix type: " +
                matrixType.getCanonicalName());

        //
        // It will return null if the class doesn't represent
        // an array.
        //
        Class numberType = clz.getComponentType();
        if (numberType != null) {
            System.out.println("Number type: " +
                    numberType.getCanonicalName());
        } else {
            System.out.println(number.getClass().getName() +
                    " class is not an array");
        }
    }
}
