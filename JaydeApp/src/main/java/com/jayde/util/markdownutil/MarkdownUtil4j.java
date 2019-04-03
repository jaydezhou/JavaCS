package com.jayde.util.markdownutil;

import com.jayde.util.markdownutil.mdtool.Analyzer;
import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.bean.Block;
import lombok.extern.log4j.Log4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.util
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-07 13:52
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-07 13:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public class MarkdownUtil4j {
//    public void parseMd(String filepath) {
//        try {
//            MarkdownMenuObject nowMenu = new MarkdownMenuObject(0);
//            MarkdownMenuObject[] menus = new MarkdownMenuObject[7];
//            menus[0] = nowMenu;
//            MarkdownMenuObject preMenu;
//
//            File mdFile = new File(filepath);
//            List<String> lines = Files.readAllLines(mdFile.toPath());
//            for (String line : lines) {
//                log.info(line);
//                if (line.startsWith("# ") || line.startsWith("## ") || line.startsWith("### ") || line.startsWith("#### ") || line.startsWith("##### ") || line.startsWith("###### ")) {
//                    nowMenu = new MarkdownMenuObject(line);
//
//                    preMenu = menus[nowMenu.getLevel() - 1];
//                    if (preMenu == null) {
//                        log.error("menu结构有割裂：" + line);
//                        return;
//                    }
//                    nowMenu.setPMenu(preMenu);
//                    preMenu.getSMenus().add(nowMenu);
//                    menus[nowMenu.getLevel()] = nowMenu;
//                } else {
//                    nowMenu.getSText().add(line);
//                }
//            }
//            System.out.println("OK");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    // 按行存储 markdown 文件
//    private ArrayList<String> mdList = new ArrayList();
//    // 存储 markdown 文件的每一行对应类型
//    private ArrayList<String> mdListType = new ArrayList();
//
//    public void initMd(String filepath) {
//        File mdFile = new File(filepath);
//        List<String> lines = null;
//        try {
//            lines = Files.readAllLines(mdFile.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (String line : lines) {
//            mdList.add(line);
//        }
//    }
//
//    /**
//     * 判断每一段 markdown 语法对应的 html 类型
//     *
//     * @param 空
//     * @return 空
//     */
//    private void defineAreaType() {
//        // 定位代码区
//        ArrayList<String> tempList = new ArrayList();
//        ArrayList<String> tempType = new ArrayList();
//        tempType.add("OTHER");
//        tempList.add(" ");
//        boolean codeBegin = false, codeEnd = false;
//        for (int i = 1; i < mdList.size() - 1; i++) {
//            String line = mdList.get(i);
//            if (line.length() > 2 && line.charAt(0) == '`' && line.charAt(1) == '`' && line.charAt(2) == '`') {
//                // 进入代码区
//                if (!codeBegin && !codeEnd) {
//                    tempType.add("CODE_BEGIN");
//                    tempList.add(" ");
//                    codeBegin = true;
//                }
//                // 离开代码区
//                else if (codeBegin && !codeEnd) {
//                    tempType.add("CODE_END");
//                    tempList.add(" ");
//                    codeBegin = false;
//                    codeEnd = false;
//                } else {
//                    tempType.add("OTHER");
//                    tempList.add(line);
//                }
//            } else {
//                tempType.add("OTHER");
//                tempList.add(line);
//            }
//        }
//        tempType.add("OTHER");
//        tempList.add(" ");
//
//        mdList = (ArrayList<String>) tempList.clone();
//        mdListType = (ArrayList<String>) tempType.clone();
//        tempList.clear();
//        tempType.clear();
//
//        // 定位其他区，注意代码区内无其他格式
//        boolean isCodeArea = false;
//        tempList.add(" ");
//        tempType.add("OTHER");
//        for (int i = 1; i < mdList.size() - 1; i++) {
//            String line = mdList.get(i);
//            String lastLine = mdList.get(i - 1);
//            String nextLine = mdList.get(i + 1);
//
//            if (mdListType.get(i) == "CODE_BEGIN") {
//                isCodeArea = true;
//                tempList.add(line);
//                tempType.add("CODE_BEGIN");
//                continue;
//            }
//            if (mdListType.get(i) == "CODE_END") {
//                isCodeArea = false;
//                tempList.add(line);
//                tempType.add("CODE_END");
//                continue;
//            }
//
//            // 代码区不含其他格式
//            if (!isCodeArea) {
//                // 进入引用区
//                if (line.length() > 2 && line.charAt(0) == '>' && lastLine.charAt(0) != '>' && nextLine.charAt(0) == '>') {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempType.add("QUOTE_BEGIN");
//                    tempType.add("OTHER");
//                }
//                // 离开引用区
//                else if (line.length() > 2 && line.charAt(0) == '>' && lastLine.charAt(0) == '>' && nextLine.charAt(0) != '>') {
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("OTHER");
//                    tempType.add("QUOTE_END");
//
//                }
//                // 单行引用区
//                else if (line.length() > 2 && line.charAt(0) == '>' && lastLine.charAt(0) != '>' && nextLine.charAt(0) != '>') {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("QUOTE_BEGIN");
//                    tempType.add("OTHER");
//                    tempType.add("QUOTE_END");
//
//                }
//                // 进入无序列表区
//                else if ((line.charAt(0) == '-' && lastLine.charAt(0) != '-' && nextLine.charAt(0) == '-') ||
//                        (line.charAt(0) == '+' && lastLine.charAt(0) != '+' && nextLine.charAt(0) == '+') ||
//                        (line.charAt(0) == '*' && lastLine.charAt(0) != '*' && nextLine.charAt(0) == '*')) {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempType.add("UNORDER_BEGIN");
//                    tempType.add("OTHER");
//                }
//                // 离开无序列表区
//                else if ((line.charAt(0) == '-' && lastLine.charAt(0) == '-' && nextLine.charAt(0) != '-') ||
//                        (line.charAt(0) == '+' && lastLine.charAt(0) == '+' && nextLine.charAt(0) != '+') ||
//                        (line.charAt(0) == '*' && lastLine.charAt(0) == '*' && nextLine.charAt(0) != '*')) {
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("OTHER");
//                    tempType.add("UNORDER_END");
//                }
//                // 单行无序列表区
//                else if ((line.charAt(0) == '-' && lastLine.charAt(0) != '-' && nextLine.charAt(0) != '-') ||
//                        (line.charAt(0) == '+' && lastLine.charAt(0) != '+' && nextLine.charAt(0) != '+') ||
//                        (line.charAt(0) == '*' && lastLine.charAt(0) != '*' && nextLine.charAt(0) != '*')) {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("UNORDER_BEGIN");
//                    tempType.add("OTHER");
//                    tempType.add("UNORDER_END");
//                }
//                // 进入有序列表区
//                else if ((line.length() > 1 && (line.charAt(0) >= '1' || line.charAt(0) <= '9') && (line.charAt(1) == '.')) &&
//                        !(lastLine.length() > 1 && (lastLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (lastLine.charAt(1) == '.')) &&
//                        (nextLine.length() > 1 && (nextLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (nextLine.charAt(1) == '.'))) {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempType.add("ORDER_BEGIN");
//                    tempType.add("OTHER");
//                }
//                // 离开有序列表区
//                else if ((line.length() > 1 && (line.charAt(0) >= '1' || line.charAt(0) <= '9') && (line.charAt(1) == '.')) &&
//                        (lastLine.length() > 1 && (lastLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (lastLine.charAt(1) == '.')) &&
//                        !(nextLine.length() > 1 && (nextLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (nextLine.charAt(1) == '.'))) {
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("OTHER");
//                    tempType.add("ORDER_END");
//                }
//                // 单行有序列表区
//                else if ((line.length() > 1 && (line.charAt(0) >= '1' || line.charAt(0) <= '9') && (line.charAt(1) == '.')) &&
//                        !(lastLine.length() > 1 && (lastLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (lastLine.charAt(1) == '.')) &&
//                        !(nextLine.length() > 1 && (nextLine.charAt(0) >= '1' || line.charAt(0) <= '9') && (nextLine.charAt(1) == '.'))) {
//                    tempList.add(" ");
//                    tempList.add(line);
//                    tempList.add(" ");
//                    tempType.add("ORDER_BEGIN");
//                    tempType.add("OTHER");
//                    tempType.add("ORDER_END");
//                }
//                // 其他
//                else {
//                    tempList.add(line);
//                    tempType.add("OTHER");
//                }
//            } else {
//                tempList.add(line);
//                tempType.add("OTHER");
//            }
//        }
//        tempList.add(" ");
//        tempType.add("OTHER");
//
//        mdList = (ArrayList<String>) tempList.clone();
//        mdListType = (ArrayList<String>) tempType.clone();
//        tempList.clear();
//        tempType.clear();
//    }
//
//    /**
//     * 判断每一行 markdown 语法对应的 html 类型
//     *
//     * @param 空
//     * @return 空
//     */
//    private void defineLineType() {
//        Stack<String> st = new Stack();
//        for (int i = 0; i < mdList.size(); i++) {
//            String line = mdList.get(i);
//            String typeLine = mdListType.get(i);
//            if (typeLine == "QUOTE_BEGIN" || typeLine == "UNORDER_BEGIN" || typeLine == "ORDER_BEGIN" || typeLine == "CODE_BEGIN") {
//                st.push(typeLine);
//            } else if (typeLine == "QUOTE_END" || typeLine == "UNORDER_END" || typeLine == "ORDER_END" || typeLine == "CODE_END") {
//                st.pop();
//            } else if (typeLine == "OTHER") {
//                if (!st.isEmpty()) {
//                    // 引用行
//                    if (st.peek() == "QUOTE_BEGIN") {
//                        mdList.set(i, line.trim().substring(1).trim());
//                    }
//                    // 无序列表行
//                    else if (st.peek() == "UNORDER_BEGIN") {
//                        mdList.set(i, line.trim().substring(1).trim());
//                        mdListType.set(i, "UNORDER_LINE");
//                    }
//                    // 有序列表行
//                    else if (st.peek() == "ORDER_BEGIN") {
//                        mdList.set(i, line.trim().substring(2).trim());
//                        mdListType.set(i, "ORDER_LINE");
//                    }
//                    // 代码行
//                    else {
//                        mdListType.set(i, "CODE_LINE");
//                    }
//                }
//                line = mdList.get(i);
//                typeLine = mdListType.get(i);
//                // 空行
//                if (line.trim().isEmpty()) {
//                    mdListType.set(i, "BLANK_LINE");
//                    mdList.set(i, "");
//                }
//                // 标题行
//                else if (line.trim().charAt(0) == '#') {
//                    mdListType.set(i, "TITLE");
//                    mdList.set(i, line.trim());
//                }
//            }
//        }
//    }

    public MarkdownMenuObject ParserMdFile(String filePath) {
        File mdFile = new File(filePath);
        return ParserMdFile(mdFile);
    }

    public MarkdownMenuObject ParserMdFile(File mdFile) {
        try {
            StringBuilder mdSource = new StringBuilder();
            List<String> lines = Files.readAllLines(mdFile.toPath());
            for (String line : lines) {
                mdSource.append(line);
                mdSource.append("\r\n");
            }
            return ParserMdSource(mdSource.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//            for (String line : lines) {
        return null;
    }

    public MarkdownMenuObject ParserMdSource(String mdSource) {
        MarkdownMenuObject[] menus = new MarkdownMenuObject[7];
        menus[0] = new MarkdownMenuObject();
        List<Block> blockList = Analyzer.analyze(mdSource);
        int ipos = 0;
        for (int i = 0; i < blockList.size(); i++) {
            Block block = blockList.get(i);
            System.out.println(block);
            if (block.getType() == BlockType.HEADLINE) {
                ipos = i;
                break;
            } else {
                try {
                    MarkdownOtherObject markdownOtherObject = new MarkdownOtherObject(block);
                    menus[0].addSonObject(markdownOtherObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        MarkdownMenuObject nowMenuObject = menus[0];
        for (int i = 0; i < blockList.size(); i++) {
            Block block = blockList.get(i);
            if (block.getType() == BlockType.HEADLINE) {
                try {
                    MarkdownMenuObject newMenuObject = new MarkdownMenuObject(block);
                    menus[newMenuObject.getLevel() - 1].addSonMenu(newMenuObject);
                    menus[newMenuObject.getLevel()] = newMenuObject;
                    nowMenuObject = newMenuObject;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MarkdownOtherObject newOtherObject = new MarkdownOtherObject(block);
                    nowMenuObject.addSonObject(newOtherObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return menus[0];
    }

    public static void main(String[] args) {
        MarkdownUtil4j markdownUtil4j = new MarkdownUtil4j();
        MarkdownMenuObject menu0 = markdownUtil4j.ParserMdFile("/Users/mac/IdeaProjects/JavaCS/JaydeApp/src/main/java/com/jayde/util/test.md");
//        menu0.showMenuTree("");
    }
}

