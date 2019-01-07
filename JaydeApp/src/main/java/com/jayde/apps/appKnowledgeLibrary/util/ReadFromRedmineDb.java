package com.jayde.apps.appKnowledgeLibrary.util;

import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssue;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineProject;
import lombok.extern.log4j.Log4j;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-06 23:04
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-06 23:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class ReadFromRedmineDb {

    private final String USERNAME = "root";
    private final String PASSWORD = "pAssw0rd";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/bitnami_redmine";

    public List<RedmineProject> getProjects() {
        List<RedmineProject> projectList = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！！");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from projects order by id");
            while (resultSet.next()) {
                RedmineProject redmineProject = new RedmineProject();
                redmineProject.setProjectId(resultSet.getString("id"));
                redmineProject.setProjectName(resultSet.getString("name"));
                projectList.add(redmineProject);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("注册驱动失败！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectList;
    }

    public List<RedmineIssue> getIssues() {
        List<RedmineIssue> issueList = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！！");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from issues order by id");
            while (resultSet.next()) {
                RedmineIssue redmineIssue = new RedmineIssue();
                redmineIssue.setIssueId(resultSet.getString("id"));
                redmineIssue.setProjectId(resultSet.getString("project_id"));
                redmineIssue.setParentId(resultSet.getString("parent_id"));
                redmineIssue.setTrackerType(resultSet.getString("tracker_id"));
                redmineIssue.setSubject(resultSet.getString("subject"));
                redmineIssue.setDescription(resultSet.getString("description"));
                issueList.add(redmineIssue);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("注册驱动失败！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return issueList;
    }

    public void SpitProjectIssues(List<RedmineProject> projectList, List<RedmineIssue> issueList) {
        for (RedmineProject redmineProject : projectList) {
//            log.info(redmineProject);
            String projectId = redmineProject.getProjectId();
            List<RedmineIssue> listIssues = new ArrayList<>();
            for (RedmineIssue redmineIssue : issueList) {
                if (redmineIssue.getProjectId().equals(projectId)) {
                    listIssues.add(redmineIssue);
                }
            }
            GenerateProjectTree(redmineProject, listIssues);
            ShowProject(redmineProject);
        }
        return;
    }

    private void GenerateProjectTree(RedmineProject redmineProject, List<RedmineIssue> listIssues) {
        for (int i = listIssues.size() - 1; i >= 0; i--) {
            RedmineIssue sonIssue = listIssues.get(i);
            if (sonIssue.getParentId() == null) {
                redmineProject.getListRootIssues().add(sonIssue);
                listIssues.remove(i);
            }
        }
        Collections.sort(redmineProject.getListRootIssues(), new Comparator<RedmineIssue>(){
            public int compare(RedmineIssue p1, RedmineIssue p2) {
                return p1.getSubject().compareTo(p2.getSubject());
            }
        });
        for (RedmineIssue rootIssue : redmineProject.getListRootIssues()) {
            GenerateProjectSonTree(rootIssue, listIssues);
        }
        return;
    }

    private void GenerateProjectSonTree(RedmineIssue parentIssue, List<RedmineIssue> listIssues) {
        for (int i = listIssues.size() - 1; i >= 0; i--) {
            RedmineIssue sonIssue = listIssues.get(i);
            if (sonIssue.getParentId().equals(parentIssue.getIssueId())) {
                parentIssue.getListSonIssues().add(sonIssue);
                listIssues.remove(i);
            }
        }
        Collections.sort(parentIssue.getListSonIssues(), new Comparator<RedmineIssue>(){
            public int compare(RedmineIssue p1, RedmineIssue p2) {
                return p1.getSubject().compareTo(p2.getSubject());
            }
        });
        for (RedmineIssue sonIssue : parentIssue.getListSonIssues()) {
            GenerateProjectSonTree(sonIssue, listIssues);
        }
        return;
    }

    public void ShowProject(RedmineProject project) {
        System.out.println("P:" + project.getProjectId() + "(" + project.getProjectName() + ")");
        for (RedmineIssue issue : project.getListRootIssues()) {
            showIssue(issue,"");
        }

    }

    private void showIssue(RedmineIssue parentIssue,String blank) {
        System.out.println(blank+"  "+"I:"+parentIssue.getIssueId()+"("+parentIssue.getSubject()+")");
        for (RedmineIssue sonIssue : parentIssue.getListSonIssues()) {
            showIssue(sonIssue,blank+"  ");
        }
    }

    public static void main(String[] args) {
        ReadFromRedmineDb readFromRedmineDb = new ReadFromRedmineDb();
        List<RedmineProject> projectList = readFromRedmineDb.getProjects();
        List<RedmineIssue> issueList = readFromRedmineDb.getIssues();
        readFromRedmineDb.SpitProjectIssues(projectList, issueList);
    }
}
