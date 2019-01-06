package com.jayde.apps.appDisk.bo;

import lombok.Data;

import java.util.UUID;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/10/29 下午3:17
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/10/29 下午3:17
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Data
public class BoPath {
    private String parentDiskId;
    private String parentPathId;

    private String pathId;
    private int level;
    private String fullPath;

    public BoPath(){
        pathId = UUID.randomUUID().toString();
    }
}
