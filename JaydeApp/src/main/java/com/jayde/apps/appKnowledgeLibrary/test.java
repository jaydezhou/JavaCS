package com.jayde.apps.appKnowledgeLibrary;

import com.jayde.apps.appKnowledgeLibrary.bo.Issue;
import com.jayde.apps.appKnowledgeLibrary.bo.Project;
import com.jayde.apps.appKnowledgeLibrary.util.RedmineDbUtil;
import com.jayde.apps.appKnowledgeLibrary.util.RedmineProjectUtil;

import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-22 15:19
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-22 15:19
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class test {
    public static void main(String[] args) {
        RedmineDbUtil redmineDbUtil = RedmineDbUtil.getSingleton();
        RedmineProjectUtil redmineProjectUtil = new RedmineProjectUtil();
        List<Project> projectList = redmineDbUtil.readProjectList();
        List<Issue> issueList = redmineDbUtil.readIssueList();
        redmineProjectUtil.GenerateProjectTree(projectList,issueList);
        for(Project project:projectList){
            redmineProjectUtil.showTree(project);
            redmineProjectUtil.exportMD(project);
        }
    }
}
