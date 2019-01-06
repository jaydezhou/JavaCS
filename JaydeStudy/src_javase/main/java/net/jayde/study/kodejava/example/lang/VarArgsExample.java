package net.jayde.study.kodejava.example.lang;

public class VarArgsExample {
    public static void main(String[] args) {
        VarArgsExample e = new VarArgsExample();

        e.printParams(1, 2, 3);
        e.printParams(10, 20, 30, 40, 50);
        e.printParams(100, 200, 300, 400, 500);
    }

    public void printParams(int... numbers) {
        for (int number : numbers) {
            System.out.print(number + ", ");
        }
        System.out.println();
    }
}
