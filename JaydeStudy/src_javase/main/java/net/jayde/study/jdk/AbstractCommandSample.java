package com.jayde.codesamples.jdk;

/**
 * Created by jayde on 2016-11-27 16:51.
 * 版权所有
 */
public abstract class AbstractCommandSample {
    String[] tips ;
    public void setTips(String[] inputTips){
        tips = inputTips;
        if(tips.length>0) {
            System.out.println("--------------" + tips[0] + "--------------");
            for (int i = 1; i < tips.length; i++) {
                System.out.println("    知识点："+tips[i]);
            }
        }
    }
}
