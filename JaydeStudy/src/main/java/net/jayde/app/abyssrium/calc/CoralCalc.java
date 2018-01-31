package net.jayde.app.abyssrium.calc;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by jayde on 2016-12-22 17:42.
 * 版权所有
 */
public class CoralCalc {
    static String[][] ss =
            {
                    {"1", "软软的枝杈珊瑚", "0", "100", "1600"},
                    {
                            "2", "清新的海草", "0", "1300", "86400"
                    },
                    {
                            "3", "鲜红的扇形珊瑚", "0", "26000", "22118400"
                    },
                    {
                            "4", "神秘的海星", "0", "860000", "16588800000"
                    },
                    {
                            "5", "白色海葵", "0", "51000000", "28665446400000"
                    },
                    {
                            "6", "珍珠色的海葵", "500", "1400000000", "98322481152000000"
                    },
                    {
                            "7", "蓝色的蘑菇石珊瑚", "500", "142000000000", "604093324197888000000"
                    },
                    {
                            "8", "深红色的喇叭珊瑚", "500", "6900000000000", "6165376466763644928000000"
                    },
                    {
                            "9", "蓝色的卷心菜皮革珊瑚", "500", "630000000000000", "98646023468218318848000000000"
                    },
                    {
                            "10", "绿色的宽珊瑚", "1000", "57000000000000000", "2363361430251574482960384000000000"
                    },
                    {
                            "11", "闪烁的海花", "1000", "4900000000000000000", "81677771029494414131110871040000000000"
                    },
                    {
                            "12", "蓝宝石光扇形珊瑚", "1000", "412000000000000000000", "3947813384939583012613112840847360000000000"
                    },
                    {
                            "13", "红色卷心菜皮革书", "1000", "69000000000000000000000", "259987198278581178878649159246843740160000000000"
                    },
                    {
                            "14", "柳珊瑚", "1000", "11000000000000000000000000", "22813876648945498446601463723910538199040000000000000"
                    }
            };
    final BigDecimal BD_THOUSAND = BigDecimal.valueOf(1000l);
    final BigInteger BI_THOUSAND = BigInteger.valueOf(1000l);
    static BigInteger bi107 = BigInteger.valueOf(107l);
    static BigInteger bi100 = BigInteger.valueOf(100l);
    static BigInteger bi2 = BigInteger.valueOf(2l);
    final BigDecimal BD_107 = BigDecimal.valueOf(1.07);
    final BigDecimal BD_10 = BigDecimal.valueOf(10);
    char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'};

    public BigDecimal level25price(int level, String vp, String price, String vpadd) {
        BigDecimal bdAllPrice = BigDecimal.ZERO;
        BigDecimal bdVp = sToBd(vp);
        BigDecimal bdPrice = sToBd(price);
        BigDecimal bdVpAdd = sToBd(vpadd);
        BigDecimal[] bdPrices = new BigDecimal[25];
        for (int i = level; i < level + 25; i++) {
            bdPrices[i - level] = new BigDecimal(0);
            if (i == level) {
                bdPrices[i - level] = bdPrice;
            } else {
                bdPrices[i - level] = bdPrices[i - level - 1].multiply(BD_107);
            }
            bdAllPrice = bdAllPrice.add(bdPrices[i - level]);
            bdVp = bdVp.add(bdVpAdd);
            System.out.println("level=" + i + "   vp=" + bdToString(bdVp) + "   vpadd=" + bdToString(bdVpAdd) + "   price=" + bdToString(bdPrices[i - level]));
        }
        System.out.println("allPrice=" + bdToString(bdAllPrice));
        return bdAllPrice;
    }

    public String bdToString(BigDecimal bd) {
        BigDecimal result = BigDecimal.ZERO;
        char dig = ' ';
        int digPos = 0;
        while (bd.compareTo(BD_THOUSAND) > 0) {
            bd = bd.divide(BD_THOUSAND);
            dig = chars[digPos];
            digPos++;
        }
        result = bd;
        result = result.setScale(3, BigDecimal.ROUND_FLOOR);
        return result.toString() + Character.valueOf(dig);
    }

    public String biToString(BigInteger bi) {
        BigDecimal bd = new BigDecimal(bi);
        return bdToString(bd);
    }

    public BigDecimal sToBd(String s) {
        BigDecimal bd = BigDecimal.ZERO;
        char dig = s.charAt(s.length() - 1);
        int digpos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (dig == chars[i]) {
                digpos = i * 3;
            }
        }
        if (digpos == 0) {
            bd = BigDecimal.valueOf(Float.valueOf(s));
        } else {
            bd = BigDecimal.valueOf(Float.valueOf(s.substring(0, s.length() - 1)));
            for (int i = 0; i < digpos; i++) {
                bd = bd.multiply(BD_10);
            }
        }
        bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
        return bd;
    }

    public static void main(String[] args) {
        CoralCalc c = new CoralCalc();
        System.out.println(c.bdToString(BigDecimal.valueOf(93202).multiply(BigDecimal.valueOf(10).pow(21))));
        System.out.println(c.sToBd("93.202i"));
        System.out.println(c.level25price(1100, "105.580j", "37.9k", "93.202i"));
    }
}
