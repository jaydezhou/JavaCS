package net.jayde.study.Als4Study.sort.selectSort;

import net.jayde.study.Als4Study.sort.AbstractSort;
import net.jayde.study.Als4Study.test.PrepareData;

import java.util.Comparator;
import java.util.Date;

public class SelectSort extends AbstractSort {
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
            exch(datas, i, pos);
        }

        for (int i = 0; i < range; i++) {
//            System.out.println(datas[i]);
        }

    }

    public static void main(String[] args) {
        SelectSort selectSort = new SelectSort();
        Date start = new Date();
        selectSort.sort(100000);
        Date end = new Date();
        long space = end.getTime() - start.getTime();
        System.out.println("times:" + space / 1000f + "s");
    }

    @Override
    public void sort(Comparable[] a) {

    }

    @Override
    public void sort(Comparable[] a, int lo, int hi) {

    }

    @Override
    public void sort(Object[] a, Comparator comparator) {

    }

    @Override
    public void sort(Object[] a, int lo, int hi, Comparator comparator) {

    }

    @Override
    public int[] indexSort(Comparable[] a) {
        return new int[0];
    }
}
