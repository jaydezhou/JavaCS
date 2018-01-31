package net.jayde.study.kodejava.example.fundamental;

public enum Rainbow {
    RED("FF0000"),
    ORANGE("FFA500"),
    YELLOW("FFFF00"),
    GREEN("008000"),
    BLUE("0000FF"),
    INDIGO("4B0082"),
    VIOLET("EE82EE");

    private String colorCode;

    //
    // The constructor of Rainbow enum.
    //
    Rainbow(String colorCode) {
        this.colorCode = colorCode;
    }

    /**
     * Get the hex color code.
     *
     * @return
     */
    public String getColorCode() {
        return colorCode;
    }
}
