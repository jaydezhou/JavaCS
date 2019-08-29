package com.jayde.apps.appDisk.bolibrary;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-29 15:54
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-29 15:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoQualityItem {
    int quatilyType1;
    int quatilyType2;
    float standardScore;
    float realScore;
    int errorMessageId;

    public int getQuatilyType1() {
        return quatilyType1;
    }

    public void setQuatilyType1(int quatilyType1) {
        this.quatilyType1 = quatilyType1;
    }

    public int getQuatilyType2() {
        return quatilyType2;
    }

    public void setQuatilyType2(int quatilyType2) {
        this.quatilyType2 = quatilyType2;
    }

    public float getStandardScore() {
        return standardScore;
    }

    public void setStandardScore(float standardScore) {
        this.standardScore = standardScore;
    }

    public float getRealScore() {
        return realScore;
    }

    public void setRealScore(float realScore) {
        this.realScore = realScore;
    }

    public int getErrorMessageId() {
        return errorMessageId;
    }

    public void setErrorMessageId(int errorMessageId) {
        this.errorMessageId = errorMessageId;
    }
}

