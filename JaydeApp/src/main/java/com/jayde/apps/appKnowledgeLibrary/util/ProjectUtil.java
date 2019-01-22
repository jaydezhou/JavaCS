package com.jayde.apps.appKnowledgeLibrary.util;

import com.jayde.apps.appKnowledgeLibrary.bo.Issue;
import com.jayde.apps.appKnowledgeLibrary.bo.Project;

import java.util.List;
import java.util.Map;

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
public interface ProjectUtil {

    /**
     * 本接口实现将Project集合与Issue集合整合为树状结构
     *
     * @param projectList
     * @param issueList
     */
    void GenerateProjectTree(List<Project> projectList, List<Issue> issueList);

    /**
     * 本接口查询Issue是否在Project中，返回null为不存在
     *
     * @param project
     * @param issue
     * @return
     */
//    Issue FindIssueInProject(Project project, Issue issue);

    void showTree(Project project);

    void exportMD(Project project);
}
