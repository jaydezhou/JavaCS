package net.jayde.study.kodejava.example.annotation;

public class OverrideExample {
    private String field;
    private String attribute;

    @Override
    public int hashCode() {
        return field.hashCode() + attribute.hashCode();
    }

    @Override
    public String toString() {
        return field + " " + attribute;
    }
}
