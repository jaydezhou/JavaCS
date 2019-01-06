package com.jayde.apps;

import lombok.Data;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExportNCE {
    String filename = "/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/2.个人文档集/2.B）英语学习/新概念英语/kb1(jayde).txt";

    public void export() {
        File saveFile = new File("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/2.个人文档集/2.B）英语学习/新概念英语/kb1(jayde).xml");
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(saveFile.exists());
        System.out.println(saveFile);
        // 创建Document对象
        Document document = DocumentHelper.createDocument();
        // 创建根节点
        Element eleBooks = document.addElement("BOOKS");
        Element eleBook = eleBooks.addElement("BOOK");


        File file = new File(filename);
        List<String> listLesson = new ArrayList<>();
        String alltext = "";

        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
//                System.out.println("line " + line + ": " + tempString);
                alltext += tempString + "\n";
                line++;
            }
            analyze(alltext, eleBook);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileOutputStream(saveFile), format);
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void analyze(String text, Element eleBook) {
        String[] lessonsTexts = text.split("\\(jayde\\)Lesson");
//        System.out.println(text);
        for (String lessonText : lessonsTexts) {
            System.out.println("----------------");
//            System.out.println(lessonText);
            LessonInfo lessonInfo = new LessonInfo();
            String[] lines = lessonText.split("\n");
            if (lines.length > 5) {

                lessonInfo.setLesson_no(Integer.parseInt(lines[0]));
                lessonInfo.setLesson_name(lines[1]);
                lessonInfo.setLesson_name_cn(lines[2]);
                lessonInfo.setLesson_question(lines[3]);
                lessonInfo.setLesson_question_cn(lines[4]);

                int content_begin = 5;
                int content_end = 0;
                int words_begin = 0;
                int words_end = 0;
                int cn_begin = 0;
                int cn_end = 0;
                for (int j = 5; j < lines.length; j++) {
//                    System.out.println(j+":"+lines[j]);
                    if (lines[j].contains("生词和短语")) {
                        words_begin = j + 1;
                        content_end = j - 1;
                    }
                    if (lines[j].contains("参考译文")) {
                        words_end = j - 1;
                        cn_begin = j + 1;
                        cn_end = lines.length - 1;
                    }
                }
                if (content_begin * content_end * words_begin * words_end * cn_begin * cn_end == 0) {
//                    System.out.println(lessonText);
                    System.out.println("lesson error:" + lines[0]);
                    System.out.println("content_begin:" + content_begin);
                    System.out.println("content_end:" + content_end);
                    System.out.println("words_begin:" + words_begin);
                    System.out.println("words_end:" + words_end);
                    System.out.println("cn_begin:" + cn_begin);
                    System.out.println("cn_end:" + cn_end);

                }


                String content = "";
                String words = "";
                String cn = "";
                lessonInfo.setLesson_content(content);
                words = words.replaceAll("&", "or");
                lessonInfo.setLesson_words(words);
                lessonInfo.setLesson_content_cn(cn);

                if (content_end - content_begin != cn_end - cn_begin) {
                    System.out.println(lessonInfo.getLesson_no());
                    System.out.println(content);
                    System.out.println(cn);
                    System.out.println("content_begin:" + content_begin);
                    System.out.println("content_end:" + content_end);
                    System.out.println("cn_begin:" + cn_begin);
                    System.out.println("cn_end:" + cn_end);
                }

                Element eleLesson = eleBook.addElement("LESSON");
                eleLesson.addAttribute("lesson_no", lessonInfo.getLesson_no() + "");
                eleLesson.addAttribute("lesson_name", lessonInfo.getLesson_name() + "");
                eleLesson.addAttribute("lesson_name_cn", lessonInfo.getLesson_name_cn() + "");
                eleLesson.addAttribute("question", lessonInfo.getLesson_question());
                eleLesson.addAttribute("question_cn", lessonInfo.getLesson_question_cn());
                eleLesson.addElement("content_en").addText(lessonInfo.getLesson_content() + "");
                eleLesson.addElement("content_cn").addText(lessonInfo.getLesson_content_cn() + "");


                Element eleWords = eleLesson.addElement("words");
                for (int j = words_begin; j <= words_end; j++) {
                    lines[j] = lines[j].replaceAll("&","&amp;");
                    eleWords.addElement("word").addText(lines[j]);
                }
                if (content_end - content_begin != cn_end - cn_begin) {
                    Element eleContentEn = eleLesson.addElement("contents_en");
                    for (int j = content_begin; j <= content_end; j++) {
                        eleContentEn.addElement("content_en").addText(lines[j]);
                    }
                    Element eleContentCn = eleLesson.addElement("contents_cn");
                    for (int j = cn_begin; j < cn_end; j++) {
                        eleContentCn.addElement("content_cn").addText(lines[j]);
                        System.out.println(lines[j]);
                    }
                } else {
                    Element eleContentAll = eleLesson.addElement("contents");
                    for (int j = content_begin; j <= content_end; j++) {
                        eleContentAll.addElement("content_en").addText(lines[j]);
                        eleContentAll.addElement("content_cn").addText(lines[j+cn_begin-content_begin]);
                    }

                }
            }
//            System.out.println(lessonInfo);
        }
        return;
    }

    public static void main(String[] args) {
        ExportNCE exportNCE = new ExportNCE();
        exportNCE.export();
    }


}

@Data
class LessonInfo {
    int lesson_no = 0;
    String lesson_name = "";
    String lesson_name_cn = "";
    String lesson_question = "";
    String lesson_question_cn = "";
    String lesson_content = "";
    String lesson_content_cn = "";
    String lesson_words = "";
}
