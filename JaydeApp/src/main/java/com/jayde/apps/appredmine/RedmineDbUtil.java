package com.jayde.apps.appredmine;

import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssue;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineProject;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.list.TreeList;

import java.sql.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-05 15:18
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-05 15:18
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class RedmineDbUtil {
    static String USERNAME = "root";
    static String PASSWORD = "pAssw0rd";
    static String DRIVER = "com.mysql.jdbc.Driver";
    static String URL = "jdbc:mysql://localhost:3306/bitnami_redmine";

    Connection connection = null;

    String SQL_PROJECTS = "select * from projects order by name";
    String SQL_ROOT_ISSUES = "select * from issues where project_id=? and parent_id is null";
    String SQL_SON_ISSUES = "select * from issues where parent_id = ?";

    public RedmineDbUtil(String inputDriver, String inputUrl, String inputUser, String inputPwd) {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public RedmineDbUtil() {
        this(DRIVER, URL, USERNAME, PASSWORD);
    }

    public void endConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RedmineProject> listProjects() {
        List<RedmineProject> projectList = new TreeList<>();
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_PROJECTS);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                RedmineProject project = new RedmineProject();
                project.setProjectName(rs.getString("name"));
                project.setProjectId(String.valueOf(rs.getInt("id")));
                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public List<RedmineIssue> listRootIssues(RedmineProject project) {
        List<RedmineIssue> issueList = new TreeList<>();
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_ROOT_ISSUES);
            psmt.setString(1, String.valueOf(project.getProjectId()));
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                RedmineIssue issue = new RedmineIssue();
                issue.setIssueId(rs.getString("id"));
                issue.setSubject(rs.getString("subject"));
                issueList.add(issue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issueList;
    }

    public List<RedmineIssue> listSonIssues(RedmineIssue parentIssue) {
        List<RedmineIssue> issueList = new TreeList<>();
        try {
            PreparedStatement psmt = connection.prepareStatement(SQL_SON_ISSUES);
            psmt.setString(1, String.valueOf(parentIssue.getIssueId()));
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                RedmineIssue issue = new RedmineIssue();
                issue.setIssueId(rs.getString("id"));
                issue.setSubject(rs.getString("subject"));
                issueList.add(issue);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issueList;
    }

    public void sortIssueBySubject(List<RedmineIssue> issueList) {
        Collections.sort(issueList, Comparator.comparing(RedmineIssue::getSubject));
        for (RedmineIssue issue : issueList) {
            List<RedmineIssue> sonIssueList = listSonIssues(issue);
            if (sonIssueList.size() > 0) {
                sortIssueBySubject(sonIssueList);
            }
        }
    }

    public void sortIssueByCreateon(List<RedmineIssue> issueList) {
//        Collections.sort(issueList, Comparator.comparing(RedmineIssue::getCreatedOn));
    }

    public void sortIssueByDoneratio(List<RedmineIssue> issueList) {
//        Collections.sort(issueList, Comparator.comparing(RedmineIssue::getDoneRatio));
    }

    public static void main(String[] args) {
        RedmineDbUtil redmineDbUtil = new RedmineDbUtil();
        List<RedmineProject> projectList = redmineDbUtil.listProjects();
        for (RedmineProject project : projectList) {
//            log.info(project);
            List<RedmineIssue> issueList = redmineDbUtil.listRootIssues(project);
            redmineDbUtil.sortIssueBySubject(issueList);
            for (RedmineIssue issue : issueList) {
                log.warn(issue);
                List<RedmineIssue> issueList1 = redmineDbUtil.listSonIssues(issue);
                redmineDbUtil.sortIssueBySubject(issueList1);
                for (RedmineIssue issue1 : issueList1) {
                    log.error(issue1);
                    List<RedmineIssue> issueList2 = redmineDbUtil.listSonIssues(issue1);
                    redmineDbUtil.sortIssueBySubject(issueList2);
                    for (RedmineIssue issue2 : issueList2) {
                        log.info(issue2);
                    }
                }
            }
        }
        redmineDbUtil.endConnection();
    }
}
