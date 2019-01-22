package com.jayde.apps.appKnowledgeLibrary.util;

import com.jayde.apps.appKnowledgeLibrary.bo.Issue;
import com.jayde.apps.appKnowledgeLibrary.bo.Project;

import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-21 14:59
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-21 14:59
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface DbUtil {

    /**
     * 本接口实现从库中读取Project信息
     * @return
     */
    List<Project> readProjectList();

    /**
     * 本接口实现从库中读取Issue信息
     * @return
     */
    List<Issue> readIssueList();
}
