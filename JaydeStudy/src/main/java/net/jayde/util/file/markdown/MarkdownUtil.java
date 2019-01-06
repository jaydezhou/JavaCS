package net.jayde.util.file.markdown;

import javafx.scene.paint.CycleMethod;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownUtil {
    public static MarkdownMenu ImportMarkdownToObject(String mdFileName) {
//        MarkdownObject mdObject = new MarkdownObject();
        List<MarkdownMenu>[] list = new List[7];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        List<MarkdownMenu> listall = new ArrayList<>();

        MarkdownMenu menuLast;

        MarkdownMenu menu0 = new MarkdownMenu();
        menuLast = menu0;
        menu0.setId("0");
        menu0.setLevel(0);
        menu0.setName("Root");
        listall.add(menu0);

        String patternStr = "#{1,6} ";
        Pattern pattern = Pattern.compile(patternStr);

        try {
            FileReader fr = new FileReader(mdFileName);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String text = "";
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    text = text.replaceAll("&#10;&#10;", "&#10;");
                    if (text.equals("&#10;"))
                        text = "";
                    menuLast.setText(text);
//                    System.out.println(text);
                    text = "";
                    int level = 0;
                    if (line.startsWith("# ")) level = 1;
                    if (line.startsWith("## ")) level = 2;
                    if (line.startsWith("### ")) level = 3;
                    if (line.startsWith("#### ")) level = 4;
                    if (line.startsWith("##### ")) level = 5;
                    if (line.startsWith("###### ")) level = 6;
                    MarkdownMenu menu = new MarkdownMenu();
                    menu.setId(UUID.randomUUID().toString());
                    menu.setLevel(level);
                    menu.setName(line);
                    menuLast = menu;
                    for (int currentPos = listall.size() - 1; currentPos >= 0; currentPos--) {
                        int currentLevel = listall.get(currentPos).getLevel();
                        if (currentLevel > level) continue;
                        if (currentLevel == level) {
//                            MarkdownMenu currentMenu = listall.get(currentPos);
//                            menu.setPreId(currentMenu.getPreId());
//                            for (int prePos = 0; prePos < listall.size(); prePos++) {
//                                if (listall.get(prePos).getId().equals(currentMenu.getPreId())) {
//                                    listall.get(prePos).getSonMenus().add(menu);
//                                }
//                            }
//                            break;
                            continue;
                        }
                        if (currentLevel < level) {
                            if (currentLevel + 1 < level) {
                                System.out.println("error:" + menu);
                            }
                            MarkdownMenu currentMenu = listall.get(currentPos);
                            menu.setPreId(currentMenu.getId());
                            listall.get(currentPos).getSonMenus().add(menu);
                            break;
                        }
                    }
                    listall.add(menu);
                } else {
                    text += line + "&#10;";
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(listall);
        return menu0;
//        return listMenus.toString();
    }

    public static void ExportToXml(MarkdownMenu menu0, String xmlfilename) {

    }

    public static void ExportToOpml(MarkdownMenu menu0, String xmlfilename) {
        Document document = DocumentHelper.createDocument();
        Element eleOpml = document.addElement("opml");
        Element eleHead = eleOpml.addElement("head");
        eleHead.addElement("title").addText("");
        eleHead.addElement("dateModified").addText("");
        eleHead.addElement("ownerName").addText("");
        Element eleBody = eleOpml.addElement("body");
        for (int i = 0; i < menu0.getSonMenus().size(); i++) {
            MarkdownMenu menu1 = menu0.getSonMenus().get(i);
//            System.out.println(menu1);
            Element eleMenu1 = eleBody.addElement("outline").addAttribute("text", menu1.getName());
            if (menu1.getText().trim().length() > 0) {
                eleMenu1.addAttribute("栏2", menu1.getText());
            }
            cycleElementMenu(eleMenu1, menu1);
        }
        OutputFormat format = OutputFormat.createPrettyPrint();
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfilename + ".xml"), format);
            writer.write(document);
        } catch (UnsupportedEncodingException uee) {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileReader fr = new FileReader(xmlfilename + ".xml");
            BufferedReader br = new BufferedReader(fr);

            File opmlfile = new File(xmlfilename);
            if (opmlfile.exists()) {
                opmlfile.delete();
            }

            opmlfile = new File(xmlfilename);
            opmlfile.createNewFile();

            FileWriter fw = new FileWriter(opmlfile.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("");

            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("&amp;#10;", "&#10;");
                bw.append(line);
//                System.out.println(line);
//                System.out.println(line);
            }
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void cycleElementMenu(Element elemenu, MarkdownMenu menu) {
        for (int i = 0; i < menu.getSonMenus().size(); i++) {
            MarkdownMenu menu1 = menu.getSonMenus().get(i);
//            System.out.println(menu1);
            Element eleMenu1 = elemenu.addElement("outline").addAttribute("text", menu1.getName());
            if (menu1.getText().trim().length() > 0) {
//                System.out.println(menu1.getText());
//                System.out.println(menu1.getText().trim());
                eleMenu1.addAttribute("栏2", menu1.getText());
            }
            if (menu1.getSonMenus().size() > 0) {
                cycleElementMenu(eleMenu1, menu1);
            }
        }
    }

    public static void main(String[] args) {
        MarkdownMenu menu0 = MarkdownUtil.ImportMarkdownToObject("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/3.技术文档集/3.3）编程技能/Java/java.md");
        MarkdownUtil.ExportToOpml(menu0, "/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/3.技术文档集/3.3）编程技能/Java/java.opml");
    }
}
