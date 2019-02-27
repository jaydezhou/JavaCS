package com.jayde.apps.appKnowledgeLibrary;

import com.taskadapter.redmineapi.*;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.regex.Pattern;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-18 14:25
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-18 14:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class RedmineJavaApiTest {

    private final String USERNAME = "root";
    private final String PASSWORD = "pAssw0rd";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssmJ";
    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    Connection connection;

    public static void main(String[] args) throws RedmineException {
//        String uri = "http://localhost:8080/redmine";
//        String apiAccessKey = "286ea61c92b5865c2027bc24b2efcea040eaa1e3";
//        String projectKey = "personal";
//        Integer queryId = null; // any
//
//        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
//        mgr.setObjectsPerPage(100);
//
//        List<Project> projectList = mgr.getProjectManager().getProjects();
//        for (Project project : projectList) {
//            log.info(project);
//            Params params = new Params();
//            ResultsWrapper<Issue> resultsWrapper =
//                    mgr.getIssueManager().getIssues(params);
//            System.out.println(resultsWrapper.getResults().size());
//            for (Issue issue : resultsWrapper.getResults()) {
//                System.out.println(issue);
//            }
//        }

        RedmineJavaApiTest redmineJavaApiTest = new RedmineJavaApiTest();
        redmineJavaApiTest.AllArticle();


// override default page size if needed
//        mgr.setObjectsPerPage(100);
//        List<Issue> issues = mgr.getIssueManager().getIssues(projectKey, queryId);
//        for (Issue issue : issues) {
//            System.out.println(issue.toString());
//        }


//        Project project = mgr.getProjectManager().getProjectByKey(projectKey);
//        System.out.println(project);
//
//        Tracker tracker_point = null;
//        List<Tracker> trackerList = mgr.getIssueManager().getTrackers();
//        for (Tracker tracker : trackerList) {
//            if (tracker.getName().trim().equals("知识点")) {
//                tracker_point = tracker;
//            }
//        }
//
//        Issue issue = IssueFactory.create(project.getId(), "test123");
//        issue.setParentId(1435);
//        issue.setTracker(tracker_point);
//        mgr.getIssueManager().createIssue(issue);


//        Version ver = VersionFactory.create(512);
//        issue.setTargetVersion(ver);
//        IssueCategory cat = IssueCategoryFactory.create(673);
//        issue.setCategory(cat);
//        ProjectManager projectManager = manager.getProjectManager();
//        Project projectByKey = projectManager.getProjectByKey("testid");
//        issue.setProject(projectByKey);
//        manager.getIssueManager().createIssue(issue);
    }


    public void AllArticle() {
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！！");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select distinct pid from bookarticle order by 'order'");
            while (resultSet.next()) {
                String aid = resultSet.getString("pid");
                ArticleC1C2(aid);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public String ArticleC1C2(String aid) {
        System.out.println(aid);
        try {
//            Class.forName(DRIVER);
//            System.out.println("注册驱动成功！！");
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from bookarticle where pid = '" + aid + "' order by 'order'");
//            System.out.println("[toc]");
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String c1 = resultSet.getString("c1");
                String c2 = resultSet.getString("c2");
                ChapterC1C2(id, c1, c2);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String ChapterC1C2(String id, String c1, String c2) {
//        log.info("------------------------------");
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("update bookarticle set c1=? where id=?");

            if (c1 == null || c2 == null) {
                System.out.println(c1);
                System.out.println(c2);
            }
            String nc1 = replaceCha(c1);
            if (nc1.equals(c1) == false) {
                c1 = nc1;
                preparedStatement.setString(1, c1);
                preparedStatement.setString(2, id);
                preparedStatement.executeUpdate();
            }

            String[] c1s = c1.split("\n");
            String[] c2s = c2.split("\n");
            if (c1s.length == c2s.length) {
//            String title = "###### " + c1s[0] + "(" + c2s[0] + ")";
//            System.out.println(title);
                for (int i = 1; i < c1s.length; i++) {
                    if (isEnglish(c1s[i]) == false) {
                        log.error("---------------------");
                        log.warn(c1s[i]);
                    }
//                System.out.print("**");
//                System.out.print(c1s[i]);
//                System.out.println("**");
//                System.out.print("`");
//                System.out.print(c2s[i]);
//                System.out.println("`");
//                System.out.println();
                }

            } else {
                log.error("有误");
                log.info(c1s.length);
                log.info(c2s.length);
            }
            preparedStatement.close();
//            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String replaceCha(String s) {
        String[] cs = {"，", "。", "！", "？", "．", "；", "“", "”", "‘", "’", "：", "－", "…", "—", "（", "）","￡","、"};
        String[] es = {",", ".", "!", "?", ",", ";", "\"", "\"", "'", "'", ":", "-", "...", "-", "(", ")","£",","};
        String ns = s;
        for (int i = 0; i < cs.length; i++) {
            if (ns.contains(cs[i])) {
                log.error("含字符：【" + cs[i] + "】");
//                log.warn(ns);
                ns = ns.replaceAll(cs[i], es[i]);
//                log.info(ns);
            }
        }
        return ns;
    }

    public boolean isEnglish(String s) {
        if (s == null) return true;
        for (char c : s.toCharArray()) {
            if (c > 255) {
                log.error(s);
                log.error("中文字符是：" + c);
                return false;
            }
        }
        return true;
    }
}
