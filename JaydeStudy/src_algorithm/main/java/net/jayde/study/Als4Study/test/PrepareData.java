package net.jayde.study.Als4Study.test;


import java.util.ArrayList;
import java.util.List;

public class PrepareData {

    public void createData(int range) {
        List<Integer> listi = new ArrayList<>();


        for (int i = 0; i < range; i++) {
            listi.add(i);
        }
        java.util.Collections.shuffle(listi);
        for (int i = 0; i < range; i++) {
            System.out.println(listi.get(i));
        }

    }

    public int[] getData(int range) {
        List<Integer> listi = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            listi.add(i);
        }
        int[] datas = new int[range];
        java.util.Collections.shuffle(listi);
        for (int i = 0; i < range; i++)
            datas[i] = listi.get(i);
        return datas;
    }

    public static void main(String[] args) {
        PrepareData prepareData = new PrepareData();
        System.out.println("--------");
        prepareData.createData(100000);
    }
}
