package net.jayde.app.abyssrium;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayde on 2016-12-21 11:29.
 * 版权所有
 */
public class CoralInfo {
    String id;
    String name;
    String comment;
    String priceText;
    BigInteger price;
    int counts;
    int level;
    //    BigInteger originalVitalitySecond;
//    BigInteger originalVitality;
    BigInteger originalVS;
//    BigInteger
    Map<Integer,CoralLevelInfo> mapLevel=new HashMap<Integer,CoralLevelInfo>();

    public static void main(String[] args) {
        BigInteger bigInteger2 = BigInteger.valueOf(2);
        BigInteger bigInteger3 = BigInteger.valueOf(3);
        BigInteger bi1 = BigInteger.valueOf(1600);
        BigInteger bi2 = bi1.multiply(bigInteger2).multiply(bigInteger3.pow(3));
        BigInteger bi3 = bi1.multiply(bigInteger2).multiply(bigInteger2).multiply(bigInteger3.pow(3)).multiply(bigInteger2.pow(7));
        BigInteger bi4 = bi3.multiply(BigInteger.valueOf(750));
        BigInteger bi5 = bi4.multiply(BigInteger.valueOf(1748));
        System.out.println(bi1);
        System.out.println(bi2);
        System.out.println(bi3);
        System.out.println(bi4);
        System.out.println(bi5);
    }
}

class CoralLevelInfo {
    int level;
    BigInteger vp;

}

