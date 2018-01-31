package net.jayde.study.kodejava.example.fundamental;

enum ProcessStatus {
    IDLE, RUNNING, FAILED, DONE;

    @Override
    public String toString() {
        return "Process Status: " + this.name();
    }
}

public class EnumNameDemo {
    public static void main(String[] args) {
        for (ProcessStatus ps : ProcessStatus.values()) {
            //
            // Gets the name of this enum constant, exactly as
            // declared in its enum declaration.
            //
            System.out.println(ps.name());

            //
            // Here we call to our implementation of the toString
            // method to get a more friendly message of the
            // enum constant name.
            //
            System.out.println(ps.toString());
        }
    }
}
