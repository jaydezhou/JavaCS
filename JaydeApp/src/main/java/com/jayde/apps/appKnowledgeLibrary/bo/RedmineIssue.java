package com.jayde.apps.appKnowledgeLibrary.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-07 09:05
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-07 09:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Data
public class RedmineIssue {
    private String projectId;
    private String issueId;
    private String subject;
    private String parentId;
    private String trackerType;
    private String description;
    private List<RedmineIssue> listSonIssues = new ArrayList<>();

}
