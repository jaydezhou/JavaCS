package com.jayde.apps.appredmine;

import com.jayde.util.dateutil.StringDateTimeUtil;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.internal.ResultsWrapper;
import lombok.extern.log4j.Log4j;
import org.dom4j.CDATA;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultCDATA;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-27 11:24
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-27 11:24
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class testRedmineObject {

    String uri = "http://localhost:8080/redmine";
    String apiAccessKey = "286ea61c92b5865c2027bc24b2efcea040eaa1e3";
    String projectKey = "personal";
    Integer queryId = null; // any

    String outFilePath = "/Users/mac/Desktop/redmine.xml";
    Document document = null;
    Element eleRoot = null;
    RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
    ProjectManager projectManager = mgr.getProjectManager();
    IssueManager issueManager = mgr.getIssueManager();

    public void testProject() throws RedmineException {


        mgr.setObjectsPerPage(100);

        List<Project> projectList = projectManager.getProjects();
        for (Project project : projectList) {
            log.info(project);
            Element eleProject = eleRoot.addElement("Project");
            addElementProject(eleProject, project);
        }
    }

    private void beginWrite() {
        document = DocumentHelper.createDocument();
        eleRoot = document.addElement("ROOT");
    }

    private void endWrite() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(outFilePath), format);
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

    private void addElementProject(Element eleProject, Project project) {
        if (project.getId() != null) {
            eleProject.addAttribute("id", project.getId().toString());
        }
        if (project.getName() != null && project.getName().trim().length() > 0) {
            eleProject.addAttribute("name", project.getName().trim());
        }
        if (project.getCreatedOn() != null) {
            eleProject.addAttribute("createdOn", StringDateTimeUtil.Date2SimpleString(project.getCreatedOn()));
        }
        if (project.getUpdatedOn() != null) {
            eleProject.addAttribute("updatedOn", StringDateTimeUtil.Date2SimpleString(project.getUpdatedOn()));
        }
        if (project.getIdentifier() != null && project.getIdentifier().trim().length() > 0) {
            eleProject.addAttribute("identifier", project.getIdentifier());
        }
        if (project.getHomepage() != null && project.getHomepage().trim().length() > 0) {
            eleProject.addAttribute("homepage", project.getHomepage());
        }
        if (project.getDescription() != null && project.getDescription().trim().length() > 0) {
            eleProject.addAttribute("description", project.getDescription());
        }
        if (project.getParentId() != null) {
            eleProject.addAttribute("parentId", project.getParentId().toString());
        }

        List<Issue> issueList = null;
        try {
            issueList = issueManager.getIssues(project.getIdentifier(), null);
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        for (Issue issue : issueList) {
            if (issue.getParentId() == null) {
                log.error(issue);
                Element eleRootIssue = eleProject.addElement("Issue");
                addElementIssue(eleRootIssue, issue);
            }
        }
    }

    private void addElementIssue(Element eleIssue, Issue issue) {
        if (issue.getId() != null) {
            eleIssue.addAttribute("id", issue.getId().toString());
        }
        if (issue.getParentId() != null) {
            eleIssue.addAttribute("parentId", issue.getParentId().toString());
        }
        if (issue.getSubject() != null && issue.getSubject().trim().length() > 0) {
            eleIssue.addAttribute("subject", issue.getSubject());
        }
        if (issue.getTracker() != null) {
            eleIssue.addAttribute("trackerId", issue.getTracker().getId().toString());
        }
        if (issue.getStatusId() != null) {
            eleIssue.addAttribute("statusId", issue.getStatusId().toString());
        }
        if (issue.getCreatedOn() != null) {
            eleIssue.addAttribute("createdOn", StringDateTimeUtil.Date2SimpleString(issue.getCreatedOn()));
        }
        if (issue.getUpdatedOn() != null) {
            eleIssue.addAttribute("updatedOn", StringDateTimeUtil.Date2SimpleString(issue.getUpdatedOn()));
        }
        if (issue.getStartDate() != null) {
            eleIssue.addAttribute("startDate", StringDateTimeUtil.Date2SimpleString(issue.getCreatedOn()));
        }
        if (issue.getDoneRatio() != null) {
            eleIssue.addAttribute("doneRatio", issue.getDoneRatio().toString());
        }
        if (issue.getDescription() != null && issue.getDescription().trim().length() > 0) {
            String ctext = issue.getDescription();
            CDATA cdata = null;
            if(ctext.contains("]]>")){
                cdata = new DefaultCDATA("有异常字符，无法显示");
            }else{
                cdata = new DefaultCDATA(issue.getDescription());
            }

            eleIssue.add(cdata);
        }
        List<Issue> sonIssueList = ListSonIssues(issue.getProjectId(), issue.getId());
        for (Issue sonIssue : sonIssueList) {
            Element eleSonIssue = eleIssue.addElement("Issue");
            addElementIssue(eleSonIssue, sonIssue);
        }
    }

    private List<Issue> ListSonIssues(Integer projectId, Integer parentId) {
        List<Issue> list = new ArrayList<>();

        Map<String, String> params = new HashMap<>();
        params.put("project_id", projectId.toString());
        params.put("parent_id", parentId.toString());
        params.put("status_id", "*");
        ResultsWrapper<Issue> rw = null;
        try {
            rw = issueManager.getIssues(params);
        } catch (RedmineException e) {
            e.printStackTrace();
        }

        while (list.size() < rw.getTotalFoundOnServer()) {
//            log.info("还有");
            params.put("offset", String.valueOf(list.size()));
            try {
                rw = issueManager.getIssues(params);
            } catch (RedmineException e) {
                e.printStackTrace();
            }
//            log.error("getResultsNumber:" + rw.getResultsNumber() + "   getResults:" + rw.getResults().size() + "   getLimitOnServer:" + rw.getLimitOnServer() + "   getOffsetOnServer:" + rw.getOffsetOnServer() + "   getTotalFoundOnServer:" + rw.getTotalFoundOnServer());
            list.addAll(rw.getResults());
        }
        Collections.sort(list, Comparator.comparing(Issue::getSubject));

//        log.info(list.size());

        return list;
    }

    public static void main(String[] args) {
        testRedmineObject test = new testRedmineObject();
        try {
            test.beginWrite();
            test.testProject();
            test.endWrite();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
    }
}
