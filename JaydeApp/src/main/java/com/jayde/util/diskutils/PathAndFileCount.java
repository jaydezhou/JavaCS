package com.jayde.util.diskutils;

import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class PathAndFileCount {
    long allPathCount = 0;
    long allFileCount = 0;
    long doPathCount = 0;
    long doFileCount = 0;
    long pathSize = 0;
    Date startTime = null;
    Date endTime = null;
    Date nowTime = null;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:S");

    public void allPathAdd() {
        allPathCount++;
    }

    public void allFileAdd() {
        allFileCount++;
    }

    public void doPathAdd() {
        doPathCount++;
        if(doPathCount==1){
            startTime = new Date();
        }
    }

    public void doFileAdd() {
        doFileCount++;
    }

    public void showPathWork() {
        int ibit =(int) (doPathCount * 10000d / allPathCount);
        float fbit = ibit/100f;
        endTime = new Date();
        long space = endTime.getTime() - startTime.getTime();
        long left = (space/doPathCount)*(allPathCount-doPathCount);
        endTime.setTime(endTime.getTime()+left);
        System.out.println("done/all:" + doPathCount+ "/" + allPathCount+"    "+fbit+"%    begin:" +dateFormat.format(startTime)+"   end(maybe):"+dateFormat.format(endTime));
    }
}
