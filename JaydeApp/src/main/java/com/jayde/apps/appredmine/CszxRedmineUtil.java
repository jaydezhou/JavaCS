package com.jayde.apps.appredmine;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.bean.*;
import org.apache.commons.collections.list.TreeList;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appredmine
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-30 09:32
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-30 09:32
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CszxRedmineUtil {
    RedmineApiUtil redmineApiUtil = null;
//    static String[] PERSONS;

    static String uri = "";
    static String apiAccessKey = "";
    static String projectKey = "";
    static String gzrzId = "";

    static Map<Integer, Tracker> trackerMap = new HashMap<>();
    static Map<Integer, User> userMap = new HashMap<>();
    static Map<String, String> statusMap = new HashMap<>();
    Project cszxProject;
    XMLConfiguration config = null;

    public CszxRedmineUtil() {

        try {
            Configurations configs = new Configurations();
            config = configs.xml("cszx-redmine.xml");
            {
                // 使用默认的符号定义创建一个表达式引擎
                DefaultExpressionEngine engine = new DefaultExpressionEngine(
                        DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
                // 指定表达式引擎
                config.setExpressionEngine(engine);

                uri = config.getString("project[@uri]");
                apiAccessKey = config.getString("project[@apiAccessKey]");
                projectKey = config.getString("project[@projectKey]");
                gzrzId = config.getString("gzrzRoot[@id]");

                redmineApiUtil = new RedmineApiUtil(uri, apiAccessKey, projectKey);
                cszxProject = redmineApiUtil.getProjectByIdentifier(projectKey);

                for (int i = 0; i < config.getMaxIndex("trackers.tracker") + 1; i++) {
                    Integer trackerId = Integer.valueOf(config.getString("trackers.tracker(" + i + ")[@id]"));
                    Tracker tracker = redmineApiUtil.getTrackById(trackerId);
                    trackerMap.put(trackerId, tracker);
                }
                for (int i = 0; i < config.getMaxIndex("users.user") + 1; i++) {
                    String userName = config.getString("users.user(" + i + ")[@name]");
                    User user = redmineApiUtil.getUserByName(userName);
                    userMap.put(user.getId(), user);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<WikiPage> wikiPageList = redmineApiUtil.listWikeByProject(cszxProject);
        for (WikiPage wikiPage : wikiPageList) {
            System.out.println("wike:" + wikiPage);
            WikiPageDetail wikiPageDetail = redmineApiUtil.getWikiPageDetail(cszxProject, wikiPage.getTitle());
            System.out.println("   " + wikiPageDetail.getText());
        }
    }

    public String checkAction(Date date) {
        LocalDate checkDate = LocalDate.now();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        checkDate = instant.atZone(zoneId).toLocalDate();
        int year = checkDate.getYear();
        int month = checkDate.getMonthValue();
        int day = checkDate.getDayOfMonth();
        List<Issue> gzrzIssueList = redmineApiUtil.listAllSonIssues(cszxProject, redmineApiUtil.getIssueById(cszxProject, gzrzId));

        //2019年3月27日工作日志（张聪聪）
        String checkResultText = "";
        for (User user : userMap.values()) {
            String rzName = year + "年" + month + "月" + day + "日工作日志（" + user.getLastName() + user.getFirstName() + "）";
//                    System.out.println(rzName);
            for (Issue issue : gzrzIssueList) {
                if (issue.getAssigneeId() == user.getId() && issue.getSubject().equals(rzName)) {
                    checkResultText += checkUserAction(user, issue, checkDate) + "\r\n---\r\n\r\n";
                }
            }
        }
        return checkResultText;
    }

    private String checkUserAction(User user, Issue rzIssue, LocalDate checkDate) {
//        System.out.println("User:" + user + "   Issue:" + rzIssue);
//        System.out.println(rzIssue.getDescription());
        StringBuilder outMdText = new StringBuilder("### " + rzIssue.getSubject() + "\r\n");

        outMdText.append("\r\n");
        outMdText.append("#### 上报的工作日志内容：\r\n");
        //已记录的工作内容
        if (rzIssue.getDescription() != null && rzIssue.getDescription().trim().length() > 0) {
            outMdText.append("> **请勿在日期条目内写工作内容，需要在下面进行新建条目**\r\n");
            outMdText.append("> ");
            outMdText.append("```日志详情\r\n");
            outMdText.append("> ");
            outMdText.append(rzIssue.getDescription());
            outMdText.append("\r\n> ```\r\n");
        }
        List<Issue> detailIssueList = redmineApiUtil.listAllSonIssues(cszxProject, rzIssue);
        if (detailIssueList == null || detailIssueList.size() == 0) {
            outMdText.append("> **您未在日期条目下面新建工作日志细节**\r\n");
        } else {
            for (Issue detailIssue : detailIssueList) {
                outMdText.append("> ");
                outMdText.append("* " + detailIssue.getSubject() + "\r\n");
                Collection<IssueRelation> issueRelationCollection = detailIssue.getRelations();
                for (IssueRelation issueRelation : issueRelationCollection) {
                    outMdText.append("> ");
                    outMdText.append("relation:" + issueRelation);
                }
                if (detailIssue.getDescription() == null || detailIssue.getDescription().trim().length() == 0) {
                    outMdText.append("> ");
                    outMdText.append("    \r\n");
                } else {
                    outMdText.append("> ");
                    outMdText.append("```日志详情\r\n");
                    outMdText.append("> ");
                    outMdText.append(detailIssue.getDescription());
                    outMdText.append("\r\n> ```\r\n");
                }
            }
        }
        //取出所有非工作日志的issue
        List<Issue> allRootIssue = redmineApiUtil.listRootIssues(cszxProject);
        List<Issue> allTempOpendIssueNotRz = new TreeList();
        for (Issue rootIssue : allRootIssue) {
            if (rootIssue.getId() != Integer.valueOf(gzrzId)) {
                allTempOpendIssueNotRz.addAll(redmineApiUtil.listAllSonIssues(cszxProject, rootIssue));
            }
        }
        Set<String> userNameTempZxrw = new HashSet<>();
        for (User tempuser : userMap.values()) {
            userNameTempZxrw.add(tempuser.getLastName() + tempuser.getFirstName() + "的临时专项工作");
            System.out.println(tempuser.getLastName() + tempuser.getFirstName() + "的临时专项工作");
            for (Issue issue : allTempOpendIssueNotRz) {
                if (issue.getSubject().equals(userNameTempZxrw)) {
                    allTempOpendIssueNotRz.remove(issue);
                    System.out.println("remove:" + issue);
                }
            }
        }
        List<Issue> allOpendIssueNotRz = new TreeList();
        for (Issue tempissue : allTempOpendIssueNotRz) {
            if (tempissue.getTracker().getId() != 9 || userNameTempZxrw.contains(tempissue.getSubject()) == false) {
                allOpendIssueNotRz.add(tempissue);
            }
        }

//        Integer selfFoldId = null;
//        for (int i = 0; i < config.getMaxIndex("users.user") + 1; i++) {
//            String userId = config.getString("users.user(" + i + ")[@id]");
//            if (userId.equals(user.getId().toString())) {
//                selfFoldId = Integer.valueOf(config.getString("users.user(" + i + ")[@folder]"));
//                break;
//            }
//        }

//        for (Issue issue : allOpendIssueNotRz) {
//            if (issue.getId() == selfFoldId) {
//                allOpendIssueNotRz.remove(issue);
//            }
//        }
        //已逾期的工作内容
        outMdText.append("\r\n");
        outMdText.append("#### 已逾期的工作任务：\r\n");
        for (Issue issue : allOpendIssueNotRz) {

            Date startDate = issue.getStartDate();
            LocalDate startLocalDate = null;
            if (startDate == null) {
                startLocalDate = LocalDate.of(1900, 1, 1);
            } else {
                startLocalDate = DateToLocal(startDate);
            }
            Date dueDate = issue.getDueDate();
            LocalDate dueLocalDate = null;
            if (dueDate == null) {
                dueLocalDate = LocalDate.of(2100, 1, 1);
            } else {
                dueLocalDate = DateToLocal(dueDate);
            }

            if (issue.getAssigneeId() == user.getId()) {
                if (issue.getDoneRatio() < 100 || issue.getStatusId() == 1) {
                    if (issue.getDueDate() != null) {
                        if (dueLocalDate.isBefore(checkDate) || dueLocalDate.isEqual(checkDate)) {
                            outMdText.append(showIssueInfo(issue));
                        }
                    }
                }
            }
        }

        //将来完成的工作内容
        outMdText.append("\r\n");
        outMdText.append("#### 进行中的工作任务：\r\n");
        for (Issue issue : allOpendIssueNotRz) {

            Date startDate = issue.getStartDate();
            LocalDate startLocalDate = null;
            if (startDate == null) {
                startLocalDate = LocalDate.of(1900, 1, 1);
            } else {
                startLocalDate = DateToLocal(startDate);
            }
            Date dueDate = issue.getDueDate();
            LocalDate dueLocalDate = null;
            if (dueDate == null) {
                dueLocalDate = LocalDate.of(2100, 1, 1);
            } else {
                dueLocalDate = DateToLocal(dueDate);
            }

            if (issue.getAssigneeId() == user.getId()) {
                if (issue.getDoneRatio() < 100 || issue.getStatusId() == 1) {
                    if (issue.getStartDate() != null) {
                        if (startLocalDate.isBefore(checkDate) || startLocalDate.isEqual(checkDate)) {
                            if (dueLocalDate.isAfter(checkDate)) {
                                outMdText.append(showIssueInfo(issue));
                            }
                        }
                    }
                }
            }
        }

        //将来开始的工作内容
        outMdText.append("\r\n");
        outMdText.append("#### 将来的工作任务：\r\n");
        for (Issue issue : allOpendIssueNotRz) {

            Date startDate = issue.getStartDate();
            LocalDate startLocalDate = null;
            if (startDate == null) {
                startLocalDate = LocalDate.of(1900, 1, 1);
            } else {
                startLocalDate = DateToLocal(startDate);
            }
            Date dueDate = issue.getDueDate();
            LocalDate dueLocalDate = null;
            if (dueDate == null) {
                dueLocalDate = LocalDate.of(2100, 1, 1);
            } else {
                dueLocalDate = DateToLocal(dueDate);
            }

            if (issue.getAssigneeId() == user.getId()) {
                if (issue.getDoneRatio() < 100 || issue.getStatusId() == 1) {
                    if (startLocalDate.isAfter(checkDate)) {
//                        if (issue.getDueDate() == null || DateToLocal(issue.getDueDate()).isAfter(checkDate)) {
                        outMdText.append(showIssueInfo(issue));
//                        }
                    }
                }
            }
        }
        return outMdText.toString();
    }

    private String showIssueInfo(Issue issue) {
        StringBuilder showIssueText = new StringBuilder();
        showIssueText.append("> ");
        showIssueText.append("* ");
        showIssueText.append("任务为：【" + issue.getId() + "】" + issue.getSubject());
        showIssueText.append("\r\n");
        showIssueText.append("> ");
        showIssueText.append(fromToDate(issue.getStartDate(), issue.getDueDate()));
        showIssueText.append("  状态：【");
        showIssueText.append(issue.getStatusName());
        showIssueText.append("】");
        showIssueText.append("\r\n");
        showIssueText.append("  完成百分比【");
        showIssueText.append(issue.getDoneRatio());
        showIssueText.append("%】");
        int per = issue.getDoneRatio() / 10;
        for (int i = 0; i < per; i++) {
            showIssueText.append("■");
        }
        for (int j = 10; j > per; j--) {
            showIssueText.append("□");
        }
        showIssueText.append("\r\n");
        return showIssueText.toString();
    }

    public LocalDate DateToLocal(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        return instant.atZone(zoneId).toLocalDate();
    }

    public Date LocalToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);

        return date;
    }

    private String fromToDate(Date fromDate, Date toDate) {
        String text = "从：";
        if (fromDate == null) {
            text += "未指定日期   到：";
        } else {
            text += DateToLocal(fromDate);
            text += "  到：";
        }
        if (toDate == null) {
            text += "未指定日期";
        } else {
            text += DateToLocal(toDate);
        }
        return text;
    }

    public String backupAction() {
        return "";
    }

    public String recordAction(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01");
        String nowText = simpleDateFormat.format(now);
        System.out.println(nowText);
        LocalDate thisMonth = LocalDate.parse(nowText);
        System.out.println(thisMonth);

        ZoneId zone = ZoneId.systemDefault();
        Instant fromInstant = thisMonth.atStartOfDay().atZone(zone).toInstant();
        Date fromDate = Date.from(fromInstant);
        Instant endInstant = thisMonth.atStartOfDay().atZone(zone).toInstant();
        Date endDate = Date.from(endInstant);

        Issue gzrzIssue = redmineApiUtil.getIssueById(redmineApiUtil.getProjectByIdentifier("cszx"), "3");
        System.out.println(gzrzIssue);
        Issue thisMonthIssue = null;
        String thisMonthText = thisMonth.getYear() + "年" + thisMonth.getMonthValue() + "月";
        List<Issue> listMonthIssue = redmineApiUtil.listSonIssues(cszxProject, gzrzIssue);
        for (Issue monthIssue : listMonthIssue) {
            if (monthIssue.getSubject().equals(thisMonthText)) {
                thisMonthIssue = monthIssue;
                System.out.println("找到了:" + thisMonthIssue);
                break;
            }
        }
        if (thisMonthIssue == null) {
            thisMonthIssue = new Issue();
            thisMonthIssue.setProjectId(cszxProject.getId());
            thisMonthIssue.setTracker(trackerMap.get(10));
            thisMonthIssue.setStatusId(1);
//            thisMonthIssue.setCreatedOn(now);
//            thisMonthIssue.setUpdatedOn(new Date());
            thisMonthIssue.setStartDate(fromDate);
            thisMonthIssue.setDueDate(endDate);
            thisMonthIssue.setParentId(gzrzIssue.getId());
            thisMonthIssue.setSubject(thisMonthText);
            thisMonthIssue.setDescription(null);
            thisMonthIssue.setDoneRatio(0);
//            thisMonthIssue.setAssigneeId(10);
//            thisMonthIssue.setAssigneeName("测试中心成员组");
            try {
                thisMonthIssue = redmineApiUtil.issueManager.createIssue(thisMonthIssue);
            } catch (RedmineException e) {
                e.printStackTrace();
            }
        }
        for (User user : userMap.values()) {
            System.out.println("给user：" + user);
            Issue thisMontUserIssue = null;
            String thisMonthUserText = thisMonth.getYear() + "年" + thisMonth.getMonthValue() + "月工作日志（" + user.getLastName() + user.getFirstName() + "）";
            List<Issue> listMonthUserIssue = redmineApiUtil.listSonIssues(cszxProject, thisMonthIssue);
            for (Issue monthUserIssue : listMonthUserIssue) {
                if (monthUserIssue.getSubject().equals(thisMonthUserText)) {
                    thisMontUserIssue = monthUserIssue;
                    System.out.println("找到了:" + thisMontUserIssue);
                    break;
                }
            }
            if (thisMontUserIssue == null) {
                thisMontUserIssue = new Issue();
                thisMontUserIssue.setProjectId(cszxProject.getId());
                thisMontUserIssue.setTracker(trackerMap.get(9));
                thisMontUserIssue.setStatusId(1);
//            thisMonthIssue.setCreatedOn(now);
//            thisMonthIssue.setUpdatedOn(new Date());
                thisMontUserIssue.setStartDate(fromDate);
                thisMontUserIssue.setDueDate(endDate);
                thisMontUserIssue.setParentId(thisMonthIssue.getId());
                thisMontUserIssue.setSubject(thisMonthUserText);
                thisMontUserIssue.setDescription(null);
                thisMontUserIssue.setDoneRatio(0);
                thisMontUserIssue.setAssigneeId(user.getId());
//            thisMonthIssue.setAssigneeName("测试中心成员组");
                try {
                    thisMontUserIssue = redmineApiUtil.issueManager.createIssue(thisMontUserIssue);
                } catch (RedmineException e) {
                    e.printStackTrace();
                }
            }
            LocalDate fromLocalDate = DateToLocal(fromDate);
            System.out.println("fromLocalDate:" + fromLocalDate);
            LocalDate endLocalDate = fromLocalDate.plusMonths(1).plusDays(-1);
            System.out.println("endLocalDate:" + endLocalDate);
            LocalDate recordLocalDate = null;
            List<Issue> thisMonthUserDayIssueList = redmineApiUtil.listSonIssues(cszxProject, thisMontUserIssue);
            for (int i = 0; i < endLocalDate.getDayOfMonth(); i++) {
                recordLocalDate = fromLocalDate.plusDays(i);
                Issue thisMonthUserDayIssue = null;
                String thisMonthUserDayText = thisMonth.getYear() + "年" + thisMonth.getMonthValue() + "月" + (i + 1) + "日工作日志（" + user.getLastName() + user.getFirstName() + "）";
                for (Issue issue : thisMonthUserDayIssueList) {
                    if (issue.getSubject().equals(thisMonthUserDayText)) {
                        thisMonthUserDayIssue = issue;
                    }
                }
                if (thisMonthUserDayIssue == null) {
                    thisMonthUserDayIssue = new Issue();
                    thisMonthUserDayIssue.setProjectId(cszxProject.getId());
                    thisMonthUserDayIssue.setTracker(trackerMap.get(6));
                    thisMonthUserDayIssue.setStatusId(1);
//            thisMonthIssue.setCreatedOn(now);
//            thisMonthIssue.setUpdatedOn(new Date());
                    thisMonthUserDayIssue.setStartDate(LocalToDate(recordLocalDate));
                    thisMonthUserDayIssue.setDueDate(LocalToDate(recordLocalDate));
                    thisMonthUserDayIssue.setParentId(thisMontUserIssue.getId());
                    thisMonthUserDayIssue.setSubject(thisMonthUserDayText);
                    thisMonthUserDayIssue.setDescription(null);
                    thisMonthUserDayIssue.setDoneRatio(0);
                    thisMonthUserDayIssue.setAssigneeId(user.getId());
//            thisMonthIssue.setAssigneeName("测试中心成员组");
                    try {
                        thisMonthUserDayIssue = redmineApiUtil.issueManager.createIssue(thisMonthUserDayIssue);
                    } catch (RedmineException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

//        for (String person : PERSONS) {
//            String monthTitle = monthText + "（" + person + "）";
//            System.out.println(monthTitle);
//            Issue monthIssue = new Issue();
//            monthIssue.setProjectId(gzrzIssue.getProjectId());
////                    monthIssue.setTracker(trackerMap.get(elethis.attributeValue("trackerId")));
//            monthIssue.setStatusId(1);
//            monthIssue.setCreatedOn(new Date());
//            monthIssue.setUpdatedOn(new Date());
//            monthIssue.setStartDate(new Date());
//            Calendar cal = Calendar.getInstance();
//            cal.set(Calendar.YEAR, 2019);
//            cal.set(Calendar.MONDAY, Calendar.JUNE);
//            cal.set(Calendar.DAY_OF_MONTH, 21);
//            monthIssue.setDueDate(cal.getTime());
//            monthIssue.setParentId(gzrzIssue.getId());
//            monthIssue.setSubject(monthTitle);
//            monthIssue.setDescription(null);
//            monthIssue.setDoneRatio(0);
//            try {
//                monthIssue = redmineApiUtil.issueManager.createIssue(monthIssue);
//            } catch (RedmineException e1) {
//                e1.printStackTrace();
//            }
//        }
        return "";
    }

    public String importAction(File file) {
        return "";
    }
}
