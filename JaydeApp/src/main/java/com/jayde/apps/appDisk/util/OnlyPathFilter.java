package com.jayde.apps.appDisk.util;

import java.io.File;
import java.io.FileFilter;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/10/30 下午3:24
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/10/30 下午3:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class OnlyPathFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory())
            return true;
        else
            return false;
    }
}
