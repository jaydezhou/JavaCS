package net.jayde.study.kodejava.example.fundamental;

public class UnaryOperatorsDemo {
    public static void main(String[] args) {
        int result = +10;  // result = 10
        System.out.println("result = " + result);
        result--;          // result = 9
        System.out.println("result = " + result);
        result++;          // result = 10
        System.out.println("result = " + result);
        result = -result;  // result = -10;
        System.out.println("result = " + result);

        //
        // The increment and decrement operators can be applied
        // before (prefix) or after (postfix) the operand. Both
        // of them will increment or decrement value by one. The
        // different is that the prefix version evaluates to the
        // incremented or decremented value while the postfix
        // version evaluates to the original value;
        //
        --result;
        System.out.println("result = " + result);
        ++result;
        System.out.println("result = " + result);

        boolean status = result == -10;  // status = true
        System.out.println("status = " + status);
        status = !status;                // status = false;
        System.out.println("status = " + status);
    }
}
