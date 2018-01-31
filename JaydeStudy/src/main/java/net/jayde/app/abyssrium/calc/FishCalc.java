package net.jayde.app.abyssrium.calc;

import java.math.BigInteger;

/**
 * Created by jayde on 2016-12-21 14:06.
 * 版权所有
 */
public class FishCalc {
    String id;
    String name;
    FishLevel[] fishLeves = new FishLevel[101];
    BigInteger beginPrice;
    BigInteger beginVsNumber;
    BigInteger nextAddVsNumber;
    BigInteger nextPriceNumber;
    String beginPriceText;
    String beginVsNumberText;
    String nextPriceText;
    String nextAddVsNumberText;

    public FishCalc(String inputId, String inputName, long inputBeginPrice, long inputBeginVsNumber, long inputNextPriceNumber, long inputNextAddVsNumber) {
        id = inputId;
        name = inputName;
        beginPrice = BigInteger.valueOf(inputBeginPrice);
        beginVsNumber = BigInteger.valueOf(inputBeginVsNumber);
        nextAddVsNumber = BigInteger.valueOf(inputNextAddVsNumber);
        nextPriceNumber = BigInteger.valueOf(inputNextPriceNumber);
        fishLeves[1] = new FishLevel();
        fishLeves[1].setId(id);
        fishLeves[1].setLevel(1);
        fishLeves[1].setVsNumber(BigInteger.valueOf(10));
        fishLeves[1].setNextPriceNumber(BigInteger.valueOf(200));
        fishLeves[1].setNextVsAddNumber(BigInteger.ONE);
        for (int i = 2; i < fishLeves.length; i++) {
            fishLeves[i] = new FishLevel();
            fishLeves[i].setId(id);
            fishLeves[i].setLevel(i);
            fishLeves[i].setVsNumber(fishLeves[i-1].getVsNumber().add(fishLeves[i-1].getNextVsAddNumber()));
            fishLeves[i].setNextPriceNumber(fishLeves[i-1].getNextPriceNumber());
            fishLeves[i].setNextVsAddNumber(fishLeves[i-1].getNextVsAddNumber());
            if(i%25==0){
                fishLeves[i].setVsNumber(fishLeves[i].getVsNumber().multiply(BigInteger.valueOf(2)));
//                fishLeves[i].setNextPriceNumber(fishLeves[i].getNextPriceNumber().multiply(1.07));
                if(i>=25) {
                    fishLeves[i].setNextVsAddNumber(fishLeves[i].getNextVsAddNumber().add(fishLeves[i].getNextPriceNumber().multiply(BigInteger.valueOf(7)).divide(BigInteger.valueOf(1000))));
                }
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FishLevel[] getFishLeves() {
        return fishLeves;
    }

    public void setFishLeves(FishLevel[] fishLeves) {
        this.fishLeves = fishLeves;
    }

    public BigInteger getBeginPrice() {
        return beginPrice;
    }

    public void setBeginPrice(BigInteger beginPrice) {
        this.beginPrice = beginPrice;
    }

    public BigInteger getBeginVsNumber() {
        return beginVsNumber;
    }

    public void setBeginVsNumber(BigInteger beginVsNumber) {
        this.beginVsNumber = beginVsNumber;
    }

    public BigInteger getNextAddVsNumber() {
        return nextAddVsNumber;
    }

    public void setNextAddVsNumber(BigInteger nextAddVsNumber) {
        this.nextAddVsNumber = nextAddVsNumber;
    }

    public BigInteger getNextPriceNumber() {
        return nextPriceNumber;
    }

    public void setNextPriceNumber(BigInteger nextPriceNumber) {
        this.nextPriceNumber = nextPriceNumber;
    }

    public String getBeginPriceText() {
        return beginPriceText;
    }

    public void setBeginPriceText(String beginPriceText) {
        this.beginPriceText = beginPriceText;
    }

    public String getBeginVsNumberText() {
        return beginVsNumberText;
    }

    public void setBeginVsNumberText(String beginVsNumberText) {
        this.beginVsNumberText = beginVsNumberText;
    }

    public String getNextPriceText() {
        return nextPriceText;
    }

    public void setNextPriceText(String nextPriceText) {
        this.nextPriceText = nextPriceText;
    }

    public String getNextAddVsNumberText() {
        return nextAddVsNumberText;
    }

    public void setNextAddVsNumberText(String nextAddVsNumberText) {
        this.nextAddVsNumberText = nextAddVsNumberText;
    }
}
