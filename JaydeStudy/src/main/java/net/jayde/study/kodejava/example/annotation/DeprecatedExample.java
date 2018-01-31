package net.jayde.study.kodejava.example.annotation;

import java.util.Calendar;
import java.util.Date;

public class DeprecatedExample {
    public static void main(String[] args) {
        DeprecatedExample de = new DeprecatedExample();
        de.getDate();
        de.getMonthFromDate();
    }

    /**
     * Get current system date.
     *
     * @return current system date.
     * @deprecated This method will removed in the near future.
     */
    @Deprecated
    public Date getDate() {
        return new Date();
    }

    public int getMonthFromDate() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
}
