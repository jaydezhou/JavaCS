package com.jayde.apps.appKnowledgeLibrary.bo;

import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-21 14:54
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-21 14:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Data
@Log4j
public class Project {

    //固有属性
    private String projectId;
    private String projectName;

    //扩展属性
    //最深层级
    int maxLevel = 0;
    //总共节点数
    int issueCount = 0;
    //子节点Issue集合
    List<Issue> listRootIssues = new ArrayList<>();

    @Override
    public String toString() {
        return projectName;
    }

    public List<Issue> getAllIssues() {
        List<Issue> listAllIssues = new ArrayList<>();
        listAllIssues.addAll(listRootIssues);
        for (Issue issue : listRootIssues) {
            listAllIssues.addAll(issue.getAllSonIssues());
        }
        return listAllIssues;
    }


    public Issue getSonIssueById(String issueId) {

        for (Issue sonIssue : listRootIssues) {
            if (sonIssue.getIssueId().equals(issueId)) {
                return sonIssue;
            }
        }

        Issue issue = null;
        for (Issue sonIssue : listRootIssues) {
            issue = sonIssue.getSonIssueById(issueId);
            if (issue != null) {
                return issue;
            }
        }

        return null;
    }
}
