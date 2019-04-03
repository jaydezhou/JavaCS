package com.jayde.apps.appredmine;

import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.Project;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Stack;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-14 16:58
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-14 16:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class ExportIssueToMd {
    public static void main(String[] args) {
        ExportIssueToMd exportIssueToMd = new ExportIssueToMd();
        exportIssueToMd.begin();
    }

    RedmineApiUtil redmineApiUtil = new RedmineApiUtil();

    private void begin() {
        Issue rootIssue = redmineApiUtil.getIssueById(redmineApiUtil.getProjectByName("PMP考试"),"1502");
        System.out.println(rootIssue);
        List<Issue> issueList = redmineApiUtil.listSonIssues(redmineApiUtil.getProjectByName("PMP考试"),rootIssue);
        Stack<Issue> stack = new Stack<>();
        if (issueList != null && issueList.size() > 0) {
            for (int i = 0; i < issueList.size(); i++) {
                stack.push(issueList.get(i));
            }
        }
        while (stack.size() > 0) {
            Issue issue1 = stack.pop();
            showIssue(redmineApiUtil.getProjectByName("PMP考试"),1, issue1);
        }
    }

    private void showIssue(Project project,int level, Issue issue) {
//        log.info(issue);
        System.out.println();
        switch (level) {
            case 1:
                System.out.print("# ");
                break;
            case 2:
                System.out.print("## ");
                break;
            case 3:
                System.out.print("### ");
                break;
            case 4:
                System.out.print("#### ");
                break;
            case 5:
                System.out.print("##### ");
                break;
            case 6:
                System.out.print("###### ");
                break;
        }
        System.out.println(issue.getSubject());
        if (issue.getDescription() != null && issue.getDescription().length() > 0 && issue.getDescription().equals("null") == false) {
            System.out.println();
            System.out.println(issue.getDescription());
        }

        List<Issue> issueList = redmineApiUtil.listSonIssues(project,issue);
        if (issueList != null && issueList.size() > 0) {
            for (int i = issueList.size() - 1; i >= 0; i--) {
                Issue issue2 = issueList.get(i);
                showIssue(project,level + 1, issue2);
            }

        }
    }
}
