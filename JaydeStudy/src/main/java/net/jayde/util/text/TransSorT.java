package net.jayde.util.text;

import com.hankcs.hanlp.HanLP;

public class TransSorT {
    public static void main(String[] args) {
        String sText = "太后";
        String tText = "太後";
        System.out.println(HanLP.segment("你好，欢迎使用HanLP！"));
        System.out.println(HanLP.hk2s(sText));
        System.out.println(HanLP.hk2s(tText));
        System.out.println(HanLP.s2tw(sText));
        System.out.println(HanLP.s2tw(tText));
        System.out.println(HanLP.convertToTraditionalChinese(sText));
        System.out.println(HanLP.convertToTraditionalChinese(tText));

        if(HanLP.convertToSimplifiedChinese(tText).equals(tText)==false){
            System.out.println(tText+" isn't equals "+sText);
        }
    }



}
