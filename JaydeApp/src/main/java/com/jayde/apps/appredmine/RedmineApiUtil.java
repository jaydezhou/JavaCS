package com.jayde.apps.appredmine;

import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssue;
import com.jayde.util.dateutil.StringDateTimeUtil;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.*;
import com.taskadapter.redmineapi.internal.ResultsWrapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.list.TreeList;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import java.io.*;
import java.util.*;

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
public class RedmineApiUtil {

    static String uri = "http://localhost:8080/redmine";
    static String apiAccessKey = "286ea61c92b5865c2027bc24b2efcea040eaa1e3";
    static String projectKey = "personal";
    static Integer queryId = null; // any

    RedmineManager mgr = null;
    ProjectManager projectManager = null;
    IssueManager issueManager = null;
    UserManager userManager = null;
    WikiManager wikiManager = null;
    Map<Integer, Tracker> trackerMap = new HashMap<>();

    Map<Integer, Project> mapProject = new HashMap<>();
    Map<Project, Map<String, Issue>> mapProjectAndIssues = new HashMap<>();
    Map<String, User> userMap = new HashMap<>();

    public RedmineApiUtil(String inputUri, String inputApiAccessKey, String inputProjectKey) {
        projectKey = inputProjectKey;
        mgr = RedmineManagerFactory.createWithApiKey(inputUri, inputApiAccessKey);
        projectManager = mgr.getProjectManager();
        issueManager = mgr.getIssueManager();
        userManager = mgr.getUserManager();
        wikiManager = mgr.getWikiManager();
        //初始化Tracker列表
        List<Tracker> trackerList = null;
        try {
            trackerList = issueManager.getTrackers();
            for (Tracker tracker : trackerList) {
                trackerMap.put(tracker.getId(), tracker);
                System.out.println("tracker:" + tracker);
            }
        } catch (RedmineException e) {
            e.printStackTrace();
        }

        //初始化User列表
        try {
            List<User> userList = userManager.getUsers();
            for (User user : userList) {
                System.out.println("user:" + user + "   fullname:" + user.getFullName());
                userMap.put(user.getId().toString(), user);
            }

            List<Group> groupList = userManager.getGroups();
            for (Group group : groupList) {
                System.out.println("group:" + group);
            }

            List<Role> roleList = userManager.getRoles();
            for (Role role : roleList) {
                System.out.println("role:" + role);
            }
        } catch (RedmineException e) {
            e.printStackTrace();
        }


        //初始化Project列表
        try {
            List<Project> projectList = projectManager.getProjects();
            for (Project project : projectList) {
                mapProject.put(project.getId(), project);
            }
        } catch (RedmineException e) {
            e.printStackTrace();
        }

    }

    public RedmineApiUtil() {
        this(uri, apiAccessKey, projectKey);
    }

    public List<Project> listProjects() {
        List<Project> projectList = new TreeList<>();
        try {
            projectList = projectManager.getProjects();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    public Tracker getTrackById(Integer id) {
        return trackerMap.get(id);
    }

    public User getUserByName(String name) {
        for (User user : userMap.values()) {
            if (user.getLogin().equals(name)) {
                return user;
            }
        }
        return null;
    }


    public Tracker getTrackByName(String name) {
        for (Tracker tracker : trackerMap.values()) {
            if (tracker.getName().trim().equalsIgnoreCase(name.trim())) {
                return tracker;
            }
        }
        return null;
    }

    public Project getProjectById(String id) {
        return mapProject.get(Integer.valueOf(id));
    }

    public Project getProjectByIdentifier(String identifier) {
        Project project = null;
        for (Project sonProject : mapProject.values()) {
            if (sonProject.getIdentifier().trim().equalsIgnoreCase(identifier.trim())) {
                return sonProject;
            }
        }
        return project;
    }

    public Project getProjectByName(String name) {
        Project project = null;
        for (Project sonProject : mapProject.values()) {
            if (sonProject.getName().trim().equalsIgnoreCase(name.trim())) {
                return sonProject;
            }
        }
        return project;
    }

    public Map<String, Issue> listAllIssues(Project project) {
        if (mapProjectAndIssues.containsKey(project) == false) {
            //列举出所有Issue
            Map<String, String> params = new HashMap<>();
            params.put("project_id", project.getId().toString());
            params.put("status_id", "*");
            List<Issue> list = new ArrayList<>();
            ResultsWrapper<Issue> rw = null;
            try {
                rw = issueManager.getIssues(params);
            } catch (RedmineException e) {
                e.printStackTrace();
            }

            while (list.size() < rw.getTotalFoundOnServer()) {
                params.put("offset", String.valueOf(list.size()));
                try {
                    rw = issueManager.getIssues(params);
                } catch (RedmineException e) {
                    e.printStackTrace();
                }
                list.addAll(rw.getResults());
            }
//                Collections.sort(list, Comparator.comparing(Issue::getSubject));
            Map<String, Issue> mapIssues = new HashMap<>();
            for (Issue issue : list) {
                mapIssues.put(issue.getId().toString(), issue);
            }
            mapProjectAndIssues.put(project, mapIssues);
        }
        return mapProjectAndIssues.get(project);
    }

    public List<Issue> listRootIssues(Project project) {
        List<Issue> issueList = new TreeList<>();
        Map<String, Issue> mapIssue = listAllIssues(project);
        if (mapIssue == null || mapIssue.size() == 0) {
            return issueList;
        }
        Iterator<String> issueKeys = mapIssue.keySet().iterator();
        while (issueKeys.hasNext()) {
            String key = issueKeys.next();
            Issue issue = mapIssue.get(key);
            if (issue.getParentId() == null) {
                issueList.add(issue);
            }
        }
        return issueList;
    }

    public List<Issue> listSonIssues(Project project, Issue parentIssue) {
        List<Issue> issueList = new TreeList<>();
        for (Issue issue : listAllIssues(project).values()) {
            if (issue.getParentId() != null && issue.getParentId().equals(parentIssue.getId())) {
                issueList.add(issue);
            }
        }
//        try {
//            ResultsWrapper<Issue> resultsWrapper = issueManager.getIssues(new Params().add("parent_id", String.valueOf(parentIssue.getId())));
//            issueList = resultsWrapper.getResults();
//        } catch (RedmineException e) {
//            e.printStackTrace();
//        }
        return issueList;
    }

    public List<Issue> listAllSonIssues(Project project, Issue parentIssue) {
        List<Issue> issueList = new TreeList<>();
        for (Issue issue : listAllIssues(project).values()) {
            if (issue.getParentId() != null && issue.getParentId().equals(parentIssue.getId())) {
                issueList.add(issue);
                issueList.addAll(listAllSonIssues(project, issue));
            }
        }
        return issueList;
    }

    public Issue getIssueById(Project project, String issueId) {
//        Issue issue = null;
//        Map<String, Issue> mapIssue = listAllIssues(project);
//        issue = mapIssue.get(issueId);

        return listAllIssues(project).get(issueId);
    }

    public List<WikiPage> listWikeByProject(Project project) {
        List<WikiPage> wikiPageList = null;
        try {
            wikiPageList = wikiManager.getWikiPagesByProject(project.getIdentifier());
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return wikiPageList;
    }

    public WikiPageDetail getWikiPageDetail(Project project, String pageTitle) {
        try {
            return wikiManager.getWikiPageDetailByProjectAndTitle(project.getIdentifier(), pageTitle);
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void batchInsertIssueByXml(String filepath) {

        try {

            File file = new File(filepath);
            Document document = new SAXReader().read(file);
            Element root = document.getRootElement();
            Issue rootIssue = issueManager.getIssueById(Integer.valueOf(root.attributeValue("parentid")));
            List<Element> elements = root.element("ISSUES").elements("ISSUE");
            for (Element ele : elements) {
                log.info(ele);
                insertIssueByXml(rootIssue, ele);
            }
        } catch (DocumentException | RedmineException e) {
            e.printStackTrace();
        }
    }

    private void insertIssueByXml(Issue parentIssue, Element elethis) {
        try {
//            Issue thisIssue = IssueFactory.create(9, elethis.attributeValue("subject"));
//            thisIssue.setParentId();
            Issue thisIssue = new Issue();
            thisIssue.setProjectId(parentIssue.getProjectId());
            thisIssue.setTracker(trackerMap.get(elethis.attributeValue("trackerId")));
            thisIssue.setStatusId(Integer.parseInt(elethis.attributeValue("statusId")));
            thisIssue.setCreatedOn(new Date());
            thisIssue.setUpdatedOn(new Date());
            thisIssue.setStartDate(new Date());
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, 2019);
            cal.set(Calendar.MONDAY, Calendar.JUNE);
            cal.set(Calendar.DAY_OF_MONTH, 21);
            thisIssue.setDueDate(cal.getTime());
            thisIssue.setParentId(parentIssue.getId());
            thisIssue.setSubject(elethis.attributeValue("subject"));
            thisIssue.setDescription(null);
            thisIssue.setDoneRatio(Integer.valueOf(elethis.attributeValue("doneRatio")));
            thisIssue = issueManager.createIssue(thisIssue);
            log.info("update:" + thisIssue);
            List<Element> list = elethis.elements("ISSUE");
            for (Element eleson : list) {
                insertIssueByXml(thisIssue, eleson);
            }
        } catch (RedmineException e) {
            e.printStackTrace();
        }
    }

    private void split() {
        String text = "# 第三部分（附录、术语表、索引）\n" +
                "## 附录 X1 第 6 版更新\n" +
                "## 附录 X2 第六版《PMBOK ® 指南》编审人员.\n" +
                "## 附录 X3 敏捷型、迭代型、适应型和混合型项目环境 \n" +
                "## 附录 X4 知识领域关键概念总结\n" +
                "## 附录 X5 知识领域裁剪考虑因素总结\n" +
                "## 附录 X6 工具与技术\n" +
                "## 术语表（英文排序）";
        String[] texts = text.split("\n");
        RedmineIssue issues1 = new RedmineIssue();
        Map<String, RedmineIssue> issues2List = new HashMap<>();
        Map<String, RedmineIssue> issues3List = new HashMap<>();
        Map<String, RedmineIssue> issues4List = new HashMap<>();
        for (String line : texts) {
            System.out.println(line);
            if (line.startsWith("# ")) {
                issues1.setSubject(line);
            }
            if (line.startsWith("## ")) {
                RedmineIssue issue2 = new RedmineIssue();
                line = line.substring(3);
                System.out.println("line2:" + line);

                String key = line.substring(0, line.indexOf(" "));
                issue2.setSubject(line);
                System.out.println("key2:" + key);
                System.out.println("issue2:" + issue2);
                issues2List.put(key, issue2);
                issues1.getListSonIssues().add(issue2);
            }
            if (line.startsWith("### ")) {
                RedmineIssue issue3 = new RedmineIssue();

                line = line.substring(4);
                System.out.println("line3:" + line);

                String key = line.substring(0, line.indexOf(" "));
                issue3.setSubject(line);
                issues3List.put(key, issue3);
                System.out.println("key3:" + key);
                System.out.println("issue3:" + issue3);
                for (String issue2key : issues2List.keySet()) {
                    if (key.startsWith(issue2key)) {
                        issues2List.get(issue2key).getListSonIssues().add(issue3);
                    }
                }
            }
            if (line.startsWith("#### ")) {
                RedmineIssue issue4 = new RedmineIssue();
                line = line.substring(5);
                System.out.println("line4:" + line);

                String key = line.substring(0, line.indexOf(" "));
                issue4.setSubject(line);
                issues4List.put(key, issue4);
                System.out.println("key4:" + key);
                System.out.println("issue4:" + issue4);
                for (String issue3key : issues3List.keySet()) {
                    if (key.startsWith(issue3key)) {
                        issues3List.get(issue3key).getListSonIssues().add(issue4);
                    }
                }
            }
        }
        log.info("list1:" + issues1);
        log.info("list2:" + issues2List.size());
        log.info("list3:" + issues3List.size());
        log.info("list4:" + issues4List.size());
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("ISSUE");
        root.addAttribute("subject", issues1.getSubject());
        Element eleIssue = root.addElement("ISSUE");
        outputXml(issues1, eleIssue);
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream("/Users/mac/Downloads/pmp/temp.xml"), format);
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        //设置不自动进行转义
        writer.setEscapeText(false);
        // 生成XML文件

        //关闭XMLWriter对象
        try {
            writer.write(document);
            writer.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    private void outputXml(RedmineIssue issue, Element elethis) {
        elethis.addAttribute("subject", issue.getSubject());
        elethis.addAttribute("trackerId", "4");
        elethis.addAttribute("statusId", "2");
        elethis.addAttribute("createdOn", StringDateTimeUtil.Date2SimpleString(new Date()));
        elethis.addAttribute("updatedOn", StringDateTimeUtil.Date2SimpleString(new Date()));
        elethis.addAttribute("startDate", StringDateTimeUtil.Date2SimpleString(new Date()));
        elethis.addAttribute("doneRatio", "0");
        elethis.addElement("DESCRIPTION").addCDATA("");
        List<RedmineIssue> list = issue.getListSonIssues();
        for (RedmineIssue sonIssue : list) {
            Element eleSonIssue = elethis.addElement("ISSUE");
            outputXml(sonIssue, eleSonIssue);
        }
    }

    public static void main(String[] args) {
        RedmineApiUtil util = new RedmineApiUtil();
//        util.batchInsertIssueByXml("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/2.个人文档集/2.0）个人记事/人际关系/信息科技部(运维中心).xml");
        System.out.println(util.getTrackById(1).getName());
        System.out.println(util.getProjectById("1"));
        System.out.println(util.getProjectByIdentifier("pmp"));
        System.out.println(util.getProjectByName("3.Knowledge"));
        Project projectWork = util.getProjectByIdentifier("work");
        for (Issue issue : util.listRootIssues(projectWork)) {
//            System.out.println(issue);
        }
        int i = 0;
        for (Issue issue : util.listAllIssues(projectWork).values()) {
//            System.out.println(++i + ":" + issue);
        }
        Issue issue1656 = util.getIssueById(projectWork, "1656");
        projectWork.getStorage();
        System.out.println(issue1656);
        System.out.println(issue1656.getAssigneeName());
//        Issue issue1469 = util.getIssueById(projectWork, "1469");
//        System.out.println(issue1469);
//        List<Issue> issueList = util.listSonIssues(projectWork, issue1469);
//        for (Issue issue : issueList) {
//            System.out.println(issue);
//            for (Issue issue1 : util.listSonIssues(projectWork, issue)) {
//                System.out.println("    " + issue1);
//                if (issue1.getAuthorId() != null) {
//                    System.out.println("****"+issue1.getAuthorId()+"   "+issue1.getAuthorName());
//                }
//                if (issue1.getAssigneeId() != null) {
//                    System.out.println("&&&&&"+issue1.getAssigneeId()+"   "+issue1.getAssigneeName());
//                }
//            }
//        }
//        Project projectWork = util.listProjects();

    }


}
