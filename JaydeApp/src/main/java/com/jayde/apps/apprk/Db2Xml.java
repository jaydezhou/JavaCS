package com.jayde.apps.apprk;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.sql.*;
import java.util.regex.Pattern;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.apprk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-07-02 22:08
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-07-02 22:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Db2Xml {
    private final String USERNAME = "root";
    private final String PASSWORD = "pAssw0rd";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost:3306/ssmJ";
    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    Connection connection;

    public void beginConn() {
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！！");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public void endConn() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BoC readC() {
        BoC rootC = new BoC();
        rootC.setParentChapter(null);
        rootC.setPid("");
        rootC.setCid("0");
        rootC.setCname("root");
        rootC = readSonC(rootC);
        return rootC;
    }

    public BoC readSonC(BoC pc) {
        try {
            Statement rootStat = connection.createStatement();
            ResultSet rootResult = rootStat.executeQuery("select * from rkc where pid='" + pc.getCid() + "' order by SUBSTR(cid,LENGTH(pid)+2)+1");
            while (rootResult.next()) {
                BoC boc1 = new BoC();
                boc1.setParentChapter(pc);
                boc1.setPid(rootResult.getString("pid"));
                boc1.setCid(rootResult.getString("cid"));
                boc1.setCname(rootResult.getString("cname"));
                boc1.setCtext(rootResult.getString("ctext"));
                boc1 = readSonC(boc1);
                pc.getListSonChapters().add(boc1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pc;
    }

    public void expert2Xml(BoC rootC) {
        Document document = DocumentHelper.createDocument();//创建xml文档
        Element root = document.addElement("root");//创建根元素

        //添加根元素下的子元素及其属性,内容
        Element ele0 = root.addElement("C").addAttribute("o", rootC.getCid()).addAttribute("n", rootC.getCname());
        for (BoC boc1 : rootC.listSonChapters) {
            Element ele1 = ele0.addElement("C").addAttribute("o", boc1.getCid()).addAttribute("n", boc1.getCname());
            if (boc1.getCtext() != null && boc1.getCtext().trim().length() > 0) {
//                ele1.addCDATA(boc1.getCtext());
            }
            for (BoC boc2 : boc1.listSonChapters) {
                Element ele2 = ele1.addElement("C").addAttribute("o", boc2.getCid()).addAttribute("n", boc2.getCname());
                if (boc2.getCtext() != null && boc2.getCtext().trim().length() > 0) {
//                    ele2.addCDATA(boc2.getCtext());
                }
                for (BoC boc3 : boc2.listSonChapters) {
                    Element ele3 = ele2.addElement("C").addAttribute("o", boc3.getCid()).addAttribute("n", boc3.getCname());
                    if (boc3.getCtext() != null && boc3.getCtext().trim().length() > 0) {
//                        ele3.addCDATA(boc3.getCtext());
                    }
                }
            }
        }

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(new OutputStreamWriter(
                    new FileOutputStream("RkC.xml"), "UTF-8"), format);
            writer.write(document);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void expert2Md(BoC rootC) {
        File file = new File("RkC.md");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("");

            fileWriter.write("# 软考信管教程（第三版）"  );
            fileWriter.write("\r\n");

            for (BoC boc1 : rootC.listSonChapters) {
                fileWriter.write("## " + boc1.getCid() + " " + boc1.getCname());
                fileWriter.write("\r\n");
                if (boc1.getCtext() != null && boc1.getCtext().trim().length() > 0) {
                    fileWriter.write(boc1.getCtext());
                    fileWriter.write("\r\n");
                }
                for (BoC boc2 : boc1.listSonChapters) {
                    fileWriter.write("### " + boc2.getCid() + " " + boc2.getCname());
                    fileWriter.write("\r\n");
                    if (boc2.getCtext() != null && boc2.getCtext().trim().length() > 0) {
                        fileWriter.write(boc2.getCtext());
                        fileWriter.write("\r\n");
                    }
                    for (BoC boc3 : boc2.listSonChapters) {
                        fileWriter.write("#### " + boc3.getCid() + " " + boc3.getCname());
                        fileWriter.write("\r\n");
                        if (boc3.getCtext() != null && boc3.getCtext().trim().length() > 0) {
                            fileWriter.write(boc3.getCtext());
                            fileWriter.write("\r\n");
                        }
                    }
                }
            }
            fileWriter.flush();
            fileWriter.close();
        }catch (IOException e){

        }

//        try {
//            OutputFormat format = OutputFormat.createPrettyPrint();
//            format.setEncoding("UTF-8");
//            XMLWriter writer = new XMLWriter(new OutputStreamWriter(
//                    new FileOutputStream("RkC.xml"), "UTF-8"),format);
//            writer.write(document);
//            writer.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public Db2Xml() {
        beginConn();
        BoC root = readC();
        endConn();
        expert2Xml(root);
        expert2Md(root);
    }

    public static void main(String[] args) {
        Db2Xml db2Xml = new Db2Xml();
    }
}
