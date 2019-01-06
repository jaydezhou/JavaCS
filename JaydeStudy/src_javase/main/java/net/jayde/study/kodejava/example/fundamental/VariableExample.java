package net.jayde.study.kodejava.example.fundamental;

public class VariableExample {
    //
    // declares a double variable number1 and total
    //
    private double number1, total;

    //
    // declares a double variable and initializes its value to 10000
    //
    private double number2 = 1000;


    public static void main(String[] args) {
        VariableExample ve = new VariableExample();

        //
        // assigns a value to variable number1
        //
        ve.number1 = 500;

        //
        // assigns the calculation result of number1 + number2 to total
        //
        ve.total = ve.number1 + ve.number2;
        System.out.println(ve.total);
    }
}
