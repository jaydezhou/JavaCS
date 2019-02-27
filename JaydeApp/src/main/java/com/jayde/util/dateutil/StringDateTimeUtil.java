package com.jayde.util.dateutil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.util.dateutil
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-27 14:51
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-27 14:51
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class StringDateTimeUtil {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String Date2SimpleString(Date date) {
        return simpleDateFormat.format(date);
    }
}
