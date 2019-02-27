package com.jayde.apps.appBook;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appBook
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-19 17:05
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-19 17:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BatchInsert {
    public static void main(String[] args) {
        String PID = "01-01-06";
        int[] COUNT = {25,14,12,20};
        for (int i = 1; i <= COUNT.length; i++) {
            String pid ;
            if(i<10){
                pid =PID + "-0" + i;
            } else {
                pid =PID + "-" + i;
            }
            for (int j = 1; j <= COUNT[i-1]; j++) {
                if (j < 10) {
                    System.out.println("insert into bookarticle value('" +
                            pid + "-0" + j + "','" + pid + "'," + j + ",'','');");
                } else {
                    System.out.println("insert into bookarticle value('" +
                            pid + "-" + j + "','" + pid + "'," + j + ",'','');");
                }
            }
        }
    }
}
