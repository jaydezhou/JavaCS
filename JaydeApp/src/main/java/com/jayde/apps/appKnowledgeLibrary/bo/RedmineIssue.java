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
    int issueLevel;
    private RedmineProject parentProject;

    @Override
    public String toString() {
        return subject;
    }

    public void setIssueLevel(int level) {
        issueLevel = level;
        if (level > parentProject.getMaxLevel()) {
            parentProject.setMaxLevel(issueLevel);
        }
    }

    public void getAllSonIssues(List<RedmineIssue> list) {
        if (listSonIssues.size() == 0) {
            return;
        } else {
            list.addAll(listSonIssues);
            for (RedmineIssue issue : listSonIssues) {
                issue.getAllSonIssues(list);
            }
        }
    }

    public RedmineIssue getIssueById(String issueId) {

        for (RedmineIssue sonIssue : listSonIssues) {
            if (sonIssue.getIssueId().equals(issueId)) {
                return sonIssue;
            }
        }

        RedmineIssue issue = null;
        for (RedmineIssue sonIssue : listSonIssues) {
            issue = sonIssue.getIssueById(issueId);
            if (issue != null) {
                return issue;
            }
        }

        return null;
    }
}
