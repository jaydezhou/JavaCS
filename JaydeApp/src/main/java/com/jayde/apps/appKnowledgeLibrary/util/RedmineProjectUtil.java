package com.jayde.apps.appKnowledgeLibrary.util;

import com.jayde.apps.appKnowledgeLibrary.bo.*;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

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
@Log4j
public class RedmineProjectUtil implements ProjectUtil {

    @Override
    public void GenerateProjectTree(List<Project> projectList, List<Issue> issueList) {
        for (Project project : projectList) {
            String projectId = project.getProjectId();
            List<Issue> listAllSonIssues = new ArrayList<>();
            for (Issue issue : issueList) {
                if (issue.getProjectId().equals(projectId)) {
                    issue.setParentProject(project);
                    listAllSonIssues.add(issue);
                }
            }
            project.setIssueCount(listAllSonIssues.size());
            GenerateProjectTree(project, listAllSonIssues);
        }
    }


    private void GenerateProjectTree(Project project, List<Issue> listIssues) {
        for (int i = listIssues.size() - 1; i >= 0; i--) {
            Issue sonIssue = listIssues.get(i);
            if (sonIssue.getParentId() == null) {
                sonIssue.setIssueLevel(1);
                project.getListRootIssues().add(sonIssue);
                listIssues.remove(i);
            }
        }
        Collections.sort(project.getListRootIssues(), new Comparator<Issue>() {
            public int compare(Issue p1, Issue p2) {
                return p1.getSubject().compareTo(p2.getSubject());
            }
        });
        for (Issue rootIssue : project.getListRootIssues()) {
            GenerateProjectSonTree(rootIssue, listIssues);
        }

        return;
    }


    private void GenerateProjectSonTree(Issue parentIssue, List<Issue> listIssues) {
        for (int i = listIssues.size() - 1; i >= 0; i--) {
            Issue sonIssue = listIssues.get(i);
            if (sonIssue.getParentId().equals(parentIssue.getIssueId())) {
                sonIssue.setIssueLevel(parentIssue.getIssueLevel() + 1);
                parentIssue.getListSonIssues().add(sonIssue);
                listIssues.remove(i);
            }
        }
        Collections.sort(parentIssue.getListSonIssues(), new Comparator<Issue>() {
            public int compare(Issue p1, Issue p2) {
                return p1.getSubject().compareTo(p2.getSubject());
            }
        });
        for (Issue sonIssue : parentIssue.getListSonIssues()) {
            GenerateProjectSonTree(sonIssue, listIssues);
        }
        return;
    }


    @Override
    public void showTree(Project project) {
        log.info("P:" + project.getProjectId() + "(" + project.getProjectName() + ")");
        for (Issue issue : project.getListRootIssues()) {
            showIssue(issue, "");
        }
    }

    private void showIssue(Issue parentIssue, String blank) {
        System.out.println(blank + "  " + "I:" + parentIssue.getIssueId() + "(" + parentIssue.getSubject() + ")");
        for (Issue sonIssue : parentIssue.getListSonIssues()) {
            showIssue(sonIssue, blank + "  ");
        }
    }


    @Override
    public void exportMD(Project project) {
        String path = "/Users/mac/Desktop/";

        if (project.getMaxLevel() > 6) {
            log.error("project(" + project.getProjectName() + " has " + project.getMaxLevel() + " level");
            return;
        }


        File file = new File(path + project.getProjectName() + ".md");
//        log.info(file.getAbsolutePath());
        try {
            file.createNewFile();
            RandomAccessFile mm = null;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            StringBuffer content = new StringBuffer("");
            content.append("[TOC]\r\n");
            for (Issue parentIssue : project.getListRootIssues()) {
                ExportIssueMD(parentIssue, content);
            }
//                    log.info(content);
            fileOutputStream.write(content.toString().getBytes("utf-8"));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ExportIssueMD(Issue parentIssue, StringBuffer content) {
        content.append("\r\n");
        switch (parentIssue.getIssueLevel()) {
            case 1:
                content.append("# 【1级组】" + parentIssue.getSubject());
                break;
            case 2:
                content.append("## 【2级组】" + parentIssue.getSubject());
                break;
            case 3:
                content.append("### 【3级组】" + parentIssue.getSubject());
                break;
            case 4:
                content.append("#### 【4级组】" + parentIssue.getSubject());
                break;
            case 5:
                content.append("##### 【5级组】" + parentIssue.getSubject());
                break;
            case 6:
                content.append("###### 【6级组】" + parentIssue.getSubject());
                break;
        }
        content.append("\r\n");

        if (parentIssue.getDescription().length() > 0) {
            switch (parentIssue.getTrackerType()) {
                case Issue.TRACKER_ERROR:
                case Issue.TRACKER_FUNCTION:
                case Issue.TRACKER_DEPEND:
                case Issue.TRACKER_KNOWLEDGE_GROUP:
                case Issue.TRACKER_KNOWLEDGE_POINT:
                    content.append(parentIssue.getDescription());
                    content.append("\r\n");
                    break;
                case Issue.TRACKER_INNER_FILE:
                    content.append("\r\n");
                    content.append("~~~markdown");
                    content.append("\r\n");
                    content.append(parentIssue.getDescription());
                    content.append("\r\n");
                    content.append("~~~");
                    content.append("\r\n");
                    content.append("\r\n");
                    break;
                case Issue.TRACKER_LINK_FILE:
                    break;
            }
        }
        for (Issue sonIssue : parentIssue.getListSonIssues()) {
            ExportIssueMD(sonIssue, content);
//            Element elesonIssue = parentEle.addElement("outline").addAttribute("text", sonIssue.getSubject());
//            if (sonIssue.getDescription().length() > 0) {
//                elesonIssue.addAttribute("_note", sonIssue.getDescription());
//            }
//            ExportIssueOPML(elesonIssue, sonIssue);
        }
    }
}

