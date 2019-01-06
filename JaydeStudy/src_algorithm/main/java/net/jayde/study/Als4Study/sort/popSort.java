package net.jayde.study.Als4Study.sort;

import net.jayde.study.Als4Study.sort.selectSort.SelectSort;
import net.jayde.study.Als4Study.test.PrepareData;

import java.util.Date;

public class popSort {
    public void sort(int range) {
        PrepareData prepareData = new PrepareData();
        int[] datas = prepareData.getData(range);
        for (int i = 0; i < range; i++) {
//            System.out.println(datas[i]);
        }

        for (int i = 0; i < range - 1; i++) {
            int min = datas[i];
            int pos = i;
            for (int j = i + 1; j < range; j++) {
                if (datas[j] < min) {
                    min = datas[j];
                    pos = j;
                }
            }

            int temp = datas[i];
            datas[i] = datas[pos];
            datas[pos] = temp;
        }

        for (int i = 0; i < range; i++) {
//            System.out.println(datas[i]);
        }

    }

    public static void main(String[] args) {
        popSort popSort1 = new popSort();
        Date start = new Date();
        popSort1.sort(100000);
        Date end = new Date();
        long space = end.getTime() - start.getTime();
        System.out.println("times:" + space / 1000f + "s");
    }
}
