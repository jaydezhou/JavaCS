package net.jayde.study.kodejava.example.fundamental;

enum RainbowColor {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

public class EnumSwitch {
    public static void main(String[] args) {
        RainbowColor color = RainbowColor.INDIGO;

        EnumSwitch es = new EnumSwitch();
        String colorCode = es.getColorCode(color);
        System.out.println("ColorCode = #" + colorCode);
    }

    public String getColorCode(RainbowColor color) {
        String colorCode = "";

        //
        // We use the switch-case statement to get the hex color code of our
        // enum type rainbow colors. We can pass the enum type as expression
        // in the swith. In the case statement we only use the enum named
        // constant excluding its type name.
        //
        switch (color) {
            //
            // We use RED instead of RainbowColor.RED
            //
            case RED:
                colorCode = "FF0000";
                break;
            case ORANGE:
                colorCode = "FFA500";
                break;
            case YELLOW:
                colorCode = "FFFF00";
                break;
            case GREEN:
                colorCode = "008000";
                break;
            case BLUE:
                colorCode = "0000FF";
                break;
            case INDIGO:
                colorCode = "4B0082";
                break;
            case VIOLET:
                colorCode = "EE82EE";
                break;
            default:
                break;
        }
        return colorCode;
    }
}
