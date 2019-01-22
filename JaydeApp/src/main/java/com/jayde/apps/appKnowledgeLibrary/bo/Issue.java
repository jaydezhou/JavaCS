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
public class Issue {

    public static final String TRACKER_ERROR = "1";
    public static final String TRACKER_FUNCTION = "2";
    public static final String TRACKER_DEPEND = "3";
    public static final String TRACKER_KNOWLEDGE_GROUP = "4";
    public static final String TRACKER_KNOWLEDGE_POINT = "5";
    public static final String TRACKER_INNER_FILE = "6";
    public static final String TRACKER_LINK_FILE = "7";

    //固有属性
    private String projectId;
    private String issueId;
    private String subject;
    private String parentId;
    private String trackerType;
    private String description;

    //扩展属性
    private List<Issue> listSonIssues = new ArrayList<>();
    int issueLevel;
    private Project parentProject;

    @Override
    public String toString() {
        return subject;
    }

    /**
     * 获取所在级别level（从1开始）
     *
     * @param level
     */
    public void setIssueLevel(int level) {
        issueLevel = level;
        if (level > parentProject.getMaxLevel()) {
            parentProject.setMaxLevel(issueLevel);
        }
    }

    /**
     * 获取所有子孙Issue集合
     *
     * @param
     */
    public List<Issue> getAllSonIssues() {
        List<Issue> list = new ArrayList<>();
        //TODO:过多的List实例，考虑重构
        if (listSonIssues.size() > 0) {
            list.addAll(listSonIssues);
            for (Issue issue : listSonIssues) {
                list.addAll(issue.getAllSonIssues());
            }
        }
        return list;
    }

    /**
     * 根据编号查询是否存在子孙集合中有Issue，无则返回null
     *
     * @param issueId
     * @return
     */
    public Issue getSonIssueById(String issueId) {

        for (Issue sonIssue : listSonIssues) {
            if (sonIssue.getIssueId().equals(issueId)) {
                return sonIssue;
            }
        }

        Issue issue = null;
        for (Issue sonIssue : listSonIssues) {
            issue = sonIssue.getSonIssueById(issueId);
            if (issue != null) {
                return issue;
            }
        }

        return null;
    }
}
