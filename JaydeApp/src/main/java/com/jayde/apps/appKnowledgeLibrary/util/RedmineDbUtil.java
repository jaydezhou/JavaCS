package com.jayde.apps.appKnowledgeLibrary.util;

import com.jayde.apps.appKnowledgeLibrary.bo.Issue;
import com.jayde.apps.appKnowledgeLibrary.bo.Project;
import com.jayde.apps.appKnowledgeLibrary.bo.ProjectRedmine;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-21 14:58
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-21 14:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class RedmineDbUtil implements DbUtil {

    //单例的实例
    private static volatile RedmineDbUtil redmineDbUtil = null;


    private final String USERNAME = "root";
    private final String PASSWORD = "pAssw0rd";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/bitnami_redmine";

    private final String TRACKER_ERROR = "1";
    private final String TRACKER_FUNCTION = "2";
    private final String TRACKER_DEPEND = "3";
    private final String TRACKER_KNOWLEDGE_GROUP = "4";
    private final String TRACKER_KNOWLEDGE_POINT = "5";
    private final String TRACKER_INNER_FILE = "6";
    private final String TRACKER_LINK_FILE = "7";

    private Connection conn;

    public static RedmineDbUtil getSingleton() {
        if (redmineDbUtil == null) {
            synchronized (RedmineDbUtil.class) {
                if (redmineDbUtil == null) {
                    redmineDbUtil = new RedmineDbUtil();
                    redmineDbUtil.beginConnection();
                }
            }
        }
        return redmineDbUtil;
    }

    private void beginConnection() {
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！！");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return;
    }

    private void endConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    @Override
    public List<Project> readProjectList() {
        List<Project> projectList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from projects order by id");
            while (resultSet.next()) {
                ProjectRedmine projectRedmine = new ProjectRedmine();
                projectRedmine.setProjectId(resultSet.getString("id"));
                projectRedmine.setProjectName(resultSet.getString("name"));
                projectList.add(projectRedmine);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {

        }
        return projectList;
    }

    @Override
    public List<Issue> readIssueList() {

        List<Issue> issueList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from issues order by id");
            while (resultSet.next()) {
                Issue issue = new Issue();
                issue.setIssueId(resultSet.getString("id"));
                issue.setProjectId(resultSet.getString("project_id"));
                issue.setParentId(resultSet.getString("parent_id"));
                issue.setTrackerType(resultSet.getString("tracker_id"));
                issue.setSubject(resultSet.getString("subject"));
                issue.setDescription(resultSet.getString("description"));
                issueList.add(issue);
            }
            resultSet.close();
            statement.close();
        } catch (
                Exception e) {

        }
        return issueList;
    }
}
