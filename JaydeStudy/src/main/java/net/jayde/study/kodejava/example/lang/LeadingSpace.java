package net.jayde.study.kodejava.example.lang;

public class LeadingSpace {
    public static void main(String[] args) {
        String text = "     tattarrattat     ";

        //
        // Using a regular expression to remove only the leading white space in
        // a string
        //
        text = text.replaceAll("^\\s+", "");
        System.out.println("Text: " + text);
    }
}
