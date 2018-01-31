package net.jayde.app.abyssrium.calc;

import java.math.BigInteger;

/**
 * Created by jayde on 2016-12-21 14:03.
 * 版权所有
 */
public class FishLevel {
    String id;
    int level;
    BigInteger vsNumber;
    String vsText;
    BigInteger nextPriceNumber;
    String nextPriceText;
    BigInteger nextVsAddNumber;
    String nextVsAddText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BigInteger getVsNumber() {
        return vsNumber;
    }

    public void setVsNumber(BigInteger vsNumber) {
        this.vsNumber = vsNumber;
    }

    public String getVsText() {
        return vsText;
    }

    public void setVsText(String vsText) {
        this.vsText = vsText;
    }

    public BigInteger getNextPriceNumber() {
        return nextPriceNumber;
    }

    public void setNextPriceNumber(BigInteger nextPriceNumber) {
        this.nextPriceNumber = nextPriceNumber;
    }

    public String getNextPriceText() {
        return nextPriceText;
    }

    public void setNextPriceText(String nextPriceText) {
        this.nextPriceText = nextPriceText;
    }

    public BigInteger getNextVsAddNumber() {
        return nextVsAddNumber;
    }

    public void setNextVsAddNumber(BigInteger nextVsAddNumber) {
        this.nextVsAddNumber = nextVsAddNumber;
    }

    public String getNextVsAddText() {
        return nextVsAddText;
    }

    public void setNextVsAddText(String nextVsAddText) {
        this.nextVsAddText = nextVsAddText;
    }
}
