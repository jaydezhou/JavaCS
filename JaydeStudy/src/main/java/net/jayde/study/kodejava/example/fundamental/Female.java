package net.jayde.study.kodejava.example.fundamental;

public class Female extends Human {
    private String hairStyle;

    public Female(String hairStyle, String sex) {
        super(sex);
        this.hairStyle = hairStyle;
    }
}
