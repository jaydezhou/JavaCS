package net.jayde.app.abyssrium;

import net.jayde.app.abyssrium.calc.CoralCalc;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayde on 2016-12-21 11:29.
 * 版权所有
 */
public class StoneInfo {
    StoneInfo preStone;
    int level;
    BigInteger vp;
    BigInteger vpAdd;
    BigInteger price;
    static BigInteger bi107 = BigInteger.valueOf(107l);
    static BigInteger bi100 = BigInteger.valueOf(100l);
    static BigInteger bi2 = BigInteger.valueOf(2l);
    static CoralCalc calc = new CoralCalc();

    public StoneInfo(int inputLevel, StoneInfo inputPreStone) {
        if (inputLevel == 1) {
            level = 1;
            vp = BigInteger.valueOf(10l);
            vpAdd = BigInteger.ONE;
            price = BigInteger.valueOf(200l);
            preStone = null;
        } else {
            level = inputLevel;
            preStone = inputPreStone;
            vp = preStone.getVp().add(preStone.getVpAdd());
            vpAdd = preStone.getVpAdd();
            price = getPreStone().getPrice().multiply(bi107).divide(bi100);
            if (level % 25 == 0) {
                vp = vp.multiply(bi2);
                vpAdd = vpAdd.multiply(bi2);
            }
        }
    }

    public StoneInfo getPreStone() {
        return preStone;
    }

    public void setPreStone(StoneInfo preStone) {
        this.preStone = preStone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BigInteger getVp() {
        return vp;
    }

    public void setVp(BigInteger vp) {
        this.vp = vp;
    }

    public BigInteger getVpAdd() {
        return vpAdd;
    }

    public void setVpAdd(BigInteger vpAdd) {
        this.vpAdd = vpAdd;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "StoneInfo{" +
//                "preStone=" + preStone +
                ", level=" + level +
                ", vp=" + "(" + calc.biToString(vp) + ")" +
                ", vpAdd=" + "(" + calc.biToString(vpAdd) + ")" +
                ", price=" + "(" + calc.biToString(price) + ")" +
                '}';
    }

    public static void main(String[] args) {
        Map<Integer, StoneInfo> maps = new HashMap<Integer, StoneInfo>();
        StoneInfo stone1 = new StoneInfo(1, null);
        System.out.println(stone1);
        maps.put(Integer.valueOf(1), stone1);
        for (int i = 2; i < 3435; i++) {
            StoneInfo preStone = maps.get(Integer.valueOf(i - 1));
            StoneInfo nowStone = new StoneInfo(i, preStone);
            maps.put(Integer.valueOf(i), nowStone);
            System.out.println(nowStone);
        }

    }
}

class StoneBigLevel{
    //200-3
}