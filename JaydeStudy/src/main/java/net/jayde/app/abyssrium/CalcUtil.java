package net.jayde.app.abyssrium;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by jayde on 2016-12-29 16:21.
 * 版权所有
 */
public class CalcUtil {

    public static String BI2String(BigInteger bi) {
        String result;
        result = bi.toString();
        int ipos = 0;
        String spos = "";
        List<String> listPos = new ArrayList<>();
        while (result.length() > 6) {
            result = result.substring(0, result.length() - 3);
            ipos++;
        }
        float bf = Float.valueOf(result) / 1000.0f;
        bf = Float.parseFloat(result) / 1000.0f;
        ipos++;
        return Float.toString(bf) + getPos(ipos) + "   " + bi.toString();
    }

    public static String getPos(int ipos) {

        String[] c = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int length = c.length;
        String result = "";
        while (ipos > 0) {
            if (ipos % length == 0) {
                result = c[length - 1] + result;
            } else {
                result = c[ipos % length - 1] + result;
            }
            ipos = (ipos - 1) / length;
        }

        return result;
    }

    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE = new BigInteger("1");
    private final static BigInteger TWO = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();

    public static BigInteger rho(BigInteger N) {
        BigInteger divisor;
        BigInteger c = new BigInteger(N.bitLength(), random);
        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger xx = x;
        if (N.mod(TWO).compareTo(ZERO) == 0)
            return TWO;

        do {
            x = x.multiply(x).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            xx = xx.multiply(xx).mod(N).add(c).mod(N);
            divisor = x.subtract(xx).gcd(N);
        }
        while ((divisor.compareTo(ONE)) == 0);
        return divisor;
    }

    public static void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0)
            return;
        if (N.isProbablePrime(20)) {
            System.out.print(N + "*");
            return;
        }
        BigInteger divisor = rho(N);
        factor(divisor);
        factor(N.divide(divisor));

    }


    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("1600");
        System.out.println(CalcUtil.BI2String(b1));

        BigInteger bite = BigInteger.valueOf(54);

        String s2 = "84.6A";
        BigInteger b2= b1.multiply(bite = BigInteger.valueOf(54));
        System.out.println(CalcUtil.BI2String(b2));
        factor(bite);
        System.out.println();

        String s3 = "22.118B";
        BigInteger b3 = b2.multiply(bite = BigInteger.valueOf(256));
        System.out.println(CalcUtil.BI2String(b3));
        factor(bite);
        System.out.println();

        String s4 = "16.588B";
        BigInteger b4 = b3.multiply(bite = BigInteger.valueOf(750));
        System.out.println(CalcUtil.BI2String(b4));
        factor(bite);
        System.out.println();

        String s5 = "28.665C";
        BigInteger b5 = b4.multiply(bite = BigInteger.valueOf(1728));
        System.out.println(CalcUtil.BI2String(b5));
        factor(bite);
        System.out.println();

        String s6 = "98.322D";
        BigInteger b6 = b5.multiply(bite = BigInteger.valueOf(3430));
        System.out.println(CalcUtil.BI2String(b6));
        factor(bite);
        System.out.println();

        String s7 = "604.093E";
        BigInteger b7 = b6.multiply(bite = BigInteger.valueOf(6144));
        System.out.println(CalcUtil.BI2String(b7));
        factor(bite);
        System.out.println();

        String s8 = "6.165G";
        BigInteger b8 = b7.multiply(bite = BigInteger.valueOf(10206));//10207
        System.out.println(CalcUtil.BI2String(b8));
        factor(bite);
        System.out.println();

        String s9 = "98.646H";
        BigInteger b9 = b8.multiply(bite = BigInteger.valueOf(16000));//
        System.out.println(CalcUtil.BI2String(b9));
        factor(bite);
        System.out.println();

        String s10 = "2.363J";
        BigInteger b10 = b9.multiply(bite = BigInteger.valueOf(23958));//23955-23964
        System.out.println(CalcUtil.BI2String(b10));
        factor(bite);
        System.out.println();

        String s11 = "81.677K";
        BigInteger b11 = b10.multiply(bite = BigInteger.valueOf(34560));//
        System.out.println(CalcUtil.BI2String(b11));
        factor(bite);
        System.out.println();

        String s12 = "3.947M";
        BigInteger b12 = b11.multiply(bite = BigInteger.valueOf(48334));//48334
        System.out.println(CalcUtil.BI2String(b12));
        factor(bite);
        System.out.println();

        String s13 = "259.987N";
        BigInteger b13 = b12.multiply(bite = BigInteger.valueOf(65856));//48334
        System.out.println(CalcUtil.BI2String(b12));
        factor(bite);
        System.out.println();

        String s14 = "22.813P";
        BigInteger b14 = b13.multiply(bite = BigInteger.valueOf(87750));//87750
        System.out.println(CalcUtil.BI2String(b14));
        factor(bite);
        System.out.println();

        CalcUtil.factor(BigInteger.valueOf(1712l));
    }

}
