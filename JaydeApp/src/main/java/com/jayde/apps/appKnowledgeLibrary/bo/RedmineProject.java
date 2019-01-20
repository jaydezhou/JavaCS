package com.jayde.apps.appKnowledgeLibrary.bo;

import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

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
@Log4j
public class RedmineProject {
    private String projectId;
    private String projectName;
    List<RedmineIssue> listRootIssues = new ArrayList<>();

    //最深层级
    int maxLevel = 0;
    //总共节点数
    int issueCount = 0;

    @Override
    public String toString() {
        return
                projectName
                ;
    }

    public List<RedmineIssue> getAllIssues() {
        List<RedmineIssue> listAllIssues = new ArrayList<>();
        listAllIssues.addAll(listRootIssues);
        for (RedmineIssue issue : listRootIssues) {
            issue.getAllSonIssues(listAllIssues);
        }
        log.info(listAllIssues.size());
        return listAllIssues;
    }

    public RedmineIssue getIssueById(String issueId) {

        for (RedmineIssue sonIssue : listRootIssues) {
            if (sonIssue.getIssueId().equals(issueId)) {
                return sonIssue;
            }
        }

        RedmineIssue issue = null;
        for (RedmineIssue sonIssue : listRootIssues) {
            issue = sonIssue.getIssueById(issueId);
            if (issue != null) {
                return issue;
            }
        }

        return null;
    }
}
