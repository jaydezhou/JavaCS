package com.jayde.apps.appDisk.bolibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-29 15:43
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-29 15:43
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoMusicQuality {
    float scoreTotal = 0f;
    List<BoQualityItem> qualityItemList = new ArrayList<>();

    public float getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(float scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public List<BoQualityItem> getQualityItemList() {
        return qualityItemList;
    }

    public void setQualityItemList(List<BoQualityItem> qualityItemList) {
        this.qualityItemList = qualityItemList;
    }
}