package com.jayde.apps.appKnowledgeLibrary;

import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssue;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineIssueGW;
import com.jayde.apps.appKnowledgeLibrary.bo.RedmineProject;
import com.jayde.apps.appKnowledgeLibrary.util.ReadFromRedmineDb;
import lombok.extern.log4j.Log4j;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;


/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-18 16:30
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-18 16:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class RestApiTest {
    public static void main(String[] args) {

        final String USERNAME = "root";
        final String PASSWORD = "pAssw0rd";
        final String DRIVER = "com.mysql.jdbc.Driver";
        final String URL = "jdbc:mysql://localhost:3306/bitnami_redmine";


        Date start = new Date();
        ReadFromRedmineDb readFromRedmineDb = new ReadFromRedmineDb();
        List<RedmineProject> listProjects = readFromRedmineDb.getProjectsAndIssues();
        Map<String, RedmineIssue> issueMap = new HashMap<>();
        Stack<RedmineIssue> stack = new Stack<>();
        RedmineIssue issue = null;
        for (RedmineProject project : listProjects) {
            issue = null;
            issue = project.getIssueById("271");
            if (issue != null) {
                break;
            }
        }
        log.info(issue);
        File toFile = new File("271.xml");
        log.info(toFile.getAbsoluteFile());
        try {
            log.info(toFile.createNewFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<RedmineIssueGW> listGWs = readFromRedmineDb.getIssuesGW();
        Map<String, RedmineIssueGW> mapGWs = new HashMap<>();
        for (RedmineIssueGW gw : listGWs) {
            mapGWs.put(gw.getIssueId(), gw);
        }

        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        org.dom4j.Element eleRoot = document.addElement("ROOT");
        for (RedmineIssue bookIssue : issue.getListSonIssues()) {
            Element bookEle = eleRoot.addElement("BOOK").addAttribute("name", bookIssue.getSubject());
            for (RedmineIssue setIssue : bookIssue.getListSonIssues()) {
                Element setEle = bookEle.addElement("SET").addAttribute("name", setIssue.getSubject());
                if (setIssue.getListSonIssues().size() > 0) {
                    for (RedmineIssue articleIssue : setIssue.getListSonIssues()) {
                        RedmineIssueGW gw = mapGWs.get(articleIssue.getIssueId());
                        String articleText = articleIssue.getDescription();
                        Element articleEle = setEle.addElement("ARTICLE").addAttribute("name", articleIssue.getSubject());
//                        Element ywEle = articleEle.addElement("原文").addCDATA(articleIssue.getDescription());
                        Element ywEle = articleEle.addElement("原文").addCDATA("");
//                        if (gw != null) {
//                            Element fyEle = articleEle.addElement("翻译").addCDATA(gw.getFy());
//                        } else {
                        Element fyEle = articleEle.addElement("翻译").addCDATA("");
//                        }
//                        if (gw != null) {
//                            Element zsEle = articleEle.addElement("注释").addCDATA(gw.getZs());
//                        } else {
                        Element zsEle = articleEle.addElement("注释").addCDATA("");
//                        }

                        Element otherEle = articleEle.addElement("OTHER").addCDATA("");
                        Element recordsEle = articleEle.addElement("RECORDS");
                        if (gw == null) {
                            StringReader stringReader = new StringReader(articleText);
                            BufferedReader bufferedReader = new BufferedReader(stringReader);
                            String article = null;
                            try {
                                while ((article = bufferedReader.readLine()) != null) {

                                    if (article.trim().length() > 0) {
//                                    log.info(article);
//                                        Element recordEle = recordsEle.addElement("RECORD");
//                                        Element sonYwEle = recordEle.addElement("原文").addCDATA(article);
//                                        Element sonFyEle = recordEle.addElement("翻译").addCDATA("");
//                                        Element sonZsEle = recordEle.addElement("注释").addCDATA("");
                                    }
                                }
                            } catch (IOException e) {
                                log.error(e.getLocalizedMessage());
                            }
                        } else {
                            List<String> listYW = new ArrayList<>();
                            List<String> listFY = new ArrayList<>();

                            StringReader stringReader = new StringReader(articleText);
                            BufferedReader bufferedReader = new BufferedReader(stringReader);
                            String article = null;
                            try {
                                while ((article = bufferedReader.readLine()) != null) {

                                    if (article.trim().length() > 0) {
//                                    log.info(article);
                                        listYW.add(article);

                                    }
                                }
                            } catch (IOException e) {
                                log.error(e.getLocalizedMessage());
                            }
                            String fytext = gw.getFy();
                            if (fytext == null) {
                                fytext = "";
                            }
                            StringReader stringReader2 = new StringReader(fytext);
                            BufferedReader bufferedReader2 = new BufferedReader(stringReader2);
                            String fy = null;
                            try {
                                while ((fy = bufferedReader2.readLine()) != null) {

                                    if (fy.trim().length() > 0) {
                                        listFY.add(fy);
                                    }
                                }
                            } catch (IOException e) {
                                log.error(e.getLocalizedMessage());
                            }
                            if (listYW.size() == listFY.size()) {
                                for (int i = 0; i < listYW.size(); i++) {
                                    Element recordEle = recordsEle.addElement("RECORD");
                                    Element sonYwEle = recordEle.addElement("原文").addCDATA(listYW.get(i));
                                    Element sonFyEle = recordEle.addElement("翻译").addCDATA(listFY.get(i));
                                    Element sonZsEle = recordEle.addElement("注释").addCDATA("");
                                }
                            } else {
                                if (listYW.size() > 0 && listFY.size() > 0) {
                                    log.error("id:" + articleIssue.getIssueId() + "行数不对"+listYW.size()+"!="+listFY.size());
                                }
                            }
                        }
                    }
                } else {
                    RedmineIssue articleIssue = setIssue;
                    String articleText = articleIssue.getDescription();
                    Element articleEle = setEle.addElement("ARTICLE").addAttribute("name", articleIssue.getSubject());
                    Element ywEle = articleEle.addElement("原文").addCDATA(articleIssue.getDescription());
                    Element fyEle = articleEle.addElement("翻译").addCDATA("");
                    Element zsEle = articleEle.addElement("注释").addCDATA("");
                    Element otherEle = articleEle.addElement("OTHER").addAttribute("type", "").addCDATA("");
                    Element recordsEle = articleEle.addElement("RECORDS");
                    StringReader stringReader = new StringReader(articleText);
                    BufferedReader bufferedReader = new BufferedReader(stringReader);
                    String article = null;
                    try {
                        while ((article = bufferedReader.readLine()) != null) {

                            if (article.trim().length() > 0) {
//                                log.info(article);
                                Element recordEle = recordsEle.addElement("RECORD");
                                Element sonYwEle = recordEle.addElement("原文").addCDATA(article);
                                Element sonFyEle = recordEle.addElement("翻译").addCDATA("");
                                Element sonZsEle = recordEle.addElement("注释").addCDATA("");
                            }
                        }
                    } catch (IOException e) {
                        log.error(e.getLocalizedMessage());
                    }


                }
            }
        }

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(toFile), format);
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

        Date end = new Date();

        log.info("-----------------------------");
        log.error("Start:" + start);
        log.error("End  ::" + end);

//        Stack<RedmineIssue> stack = new Stack<>();

    }
}
