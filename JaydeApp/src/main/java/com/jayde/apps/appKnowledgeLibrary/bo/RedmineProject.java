package com.jayde.apps.appKnowledgeLibrary.bo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-07 09:03
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-07 09:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Data
public class RedmineProject {
    private String projectId;
    private String projectName;
    List<RedmineIssue> listRootIssues = new ArrayList<>();

    @Override
    public String toString() {
        return
                projectName
                ;
    }
}
