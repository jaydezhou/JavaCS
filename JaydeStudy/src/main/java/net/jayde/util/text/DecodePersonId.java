package net.jayde.util.text;

public class DecodePersonId {
    private static String oldId;
    private static String newId;

    private static String[] ID12 = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"};


    private static boolean verify18() {
        if (getLastChar(oldId.substring(0, 18)).equals(oldId.substring(17))) {
            return true;
        } else {
            System.out.println("error");
            return false;
        }
    }

    private static void upperId() {
        /**
         * 将15位身份证号码升级为18位身份证号码
         *
         * @param code
         *            15位身份证号码
         * @return 18位身份证号码
         */
        oldId = oldId.substring(0, 6) + "19" + oldId.substring(6);
        oldId += getLastChar(oldId);
        return;
    }

    public static String decodePersonId(String inputId) {
        oldId = inputId.trim();
        //13-03-01-1978-08-03-001-1
        if (oldId == null || oldId.trim().equals("")) {
            return "";
        }
        if (oldId.length() != 15 && oldId.length() != 18) {
            return oldId;
        }
        if (oldId.length() == 18) {
            boolean b = verify18();
            if (b == false) {
                return oldId;
            }
        }
        if (oldId.length() == 15) {
            upperId();
        }

        String n1_2 = oldId.substring(0, 2);
        String n3_4 = oldId.substring(2, 4);
        String n5_6 = oldId.substring(4, 6);
        String n7_10 = oldId.substring(6, 10);
        String n11_12 = oldId.substring(10, 12);
        String n13_14 = oldId.substring(12, 14);
        String n15_17 = oldId.substring(14, 17);
        String n18 = oldId.substring(17, 18);
        /*
        第1-2位省份：对调
         */
        for (int i = 0; i < ID12.length; i++) {
            if (ID12[i].equals(n1_2)) {
                n1_2 = ID12[ID12.length - i - 1];
                break;
            }
        }
        /*
        第3-4位市：10差值
         */
        int i34 = Math.abs(10 - Integer.valueOf(n3_4));
        if (i34 < 10) {
            n3_4 = "0" + i34;
        } else {
            n3_4 = String.valueOf(i34);
        }
        /*
        第11-12月份：加1对12取模
         */
        int i1112 = Integer.valueOf(n11_12);
        i1112 = (i1112 + 1) % 12;
        if (i1112 == 0) {
            i1112 = 12;
        }
        if (i1112 > 9) {
            n11_12 = String.valueOf(i1112);
        } else {
            n11_12 = "0" + i1112;
        }
        /*
        第15-17位次序：加2倍的日期对1000取模
         */
        int i1517 = Integer.valueOf(oldId.substring(11, 12)) * 4 + Integer.valueOf(n13_14) * 2 + Integer.valueOf(n15_17);
        i1517 = i1517 % 1000;
        if (i1517 == 0) {
            n15_17 = "002";
        }
        if (i1517 > 0 && i1517 < 10) {
            n15_17 = "00" + i1517;
        }
        if (i1517 >= 10 && i1517 < 100) {
            n15_17 = "0" + i1517;
        }
        if (i1517 > 99) {
            n15_17 = String.valueOf(i1517);
        }
        /*
        第18位：重新计算
         */

        newId = n1_2 + n3_4 + n5_6 + n7_10 + n11_12 + n13_14 + n15_17;
        newId = newId + getLastChar(newId);
        return newId;
    }

    /**
     * 名称： 计算18位身份证的最后一位
     * 功能 : 根据前17位身份证号，求最后一位
     * 身份证最后一位的算法：
     * 1.将身份证号码的前17位的数字，分别乘以权数 ： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * (比如：第一位乘以7，第二位乘以9，以此类推)
     * 2.再将上面的所有乘积求和
     * 3.将求得的和mod以11（%11），得到一个小于11的数（0到11）
     * 4.然后从1 0 X 9 8 7 6 5 4 3 2几位校验码中找出最后一位的数字
     * 如果得到的是0，则对应第一位：1,如果得到的是1，则对应第二位：0
     * 如果得到的是2，则对应第三位：X,如果得到的是3，则对应第四位：9,以此类推
     * 5.最后得到的就是身份证的最后一位
     */
    public static String getLastChar(String preIds) {
        Character lastId = null;
        //当传入的字符串没有17位的时候，则无法计算，直接返回
        if (preIds == null && preIds.length() < 17) {
            return null;
        }
        int[] weightArray = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};//权数数组
        String vCode = "10X98765432";//校验码字符串
        int sumNum = 0;//前17为乘以权然后求和得到的数

        //循环乘以权，再求和
        for (int i = 0; i < 17; i++) {
            int index = Integer.parseInt(preIds.charAt(i) + "");
            sumNum = sumNum + index * weightArray[i];//乘以权数，再求和
        }

        int modNum = sumNum % 11;//求模
        lastId = vCode.charAt(modNum);//从验证码中找出对应的数

        return String.valueOf(lastId);
    }

    public static void main(String[] args) {
        String pid = "  332502197808030019 ";
        System.out.println(pid);
        System.out.println(DecodePersonId.decodePersonId(pid));
    }
}
