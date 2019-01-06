package net.jayde.study.kodejava.example.fundamental;

public class ConditionalANDDemo {
    public static void main(String[] args) {
        //
        // second operand (2<3) is not evaluated, because the first
        // operand return false the result of complete expression
        // can't be true
        //
        boolean a = (5 < 3) && (2 < 3);

        //
        // first operand return true, second operand is evaluated
        // to check the result of the second expression if second
        // operand resolves to true, the complete expression return
        // false, otherwise return false
        //
        boolean b = (1 == 1) && (2 < 3);
        boolean c = (1 == 1) && (5 < 3);

        System.out.println("result a: " + a);
        System.out.println("result b: " + b);
        System.out.println("result c: " + c);
    }
}
