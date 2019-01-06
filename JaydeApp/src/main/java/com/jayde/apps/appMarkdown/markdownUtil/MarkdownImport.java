package com.jayde.apps.appMarkdown.markdownUtil;

import com.jayde.apps.appMarkdown.bo.MarkdownMenu;
import com.jayde.apps.appMarkdown.bo.MarkdownTree;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MarkdownImport {

    public MarkdownTree ImportFileToMarkdownTree(File file) {
        if (file == null || file.exists() == false) {
            log.error("file not exist");
            return null;
        }
        List<MarkdownMenu> allMenus = new ArrayList<>();
        MarkdownMenu menu0 = new MarkdownMenu(0);
        allMenus.add(menu0);
        MarkdownMenu currentMenu = menu0;

        BufferedReader reader = null;
        try {
            log.info("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
//                log.warn("line " + line + ": " + tempString);
                MarkdownMenu menu = line2Menu(tempString);
                if (menu == null) {
                    if (currentMenu.getContext().endsWith("")) {
                        currentMenu.setContext(tempString);
                    } else {
                        currentMenu.setContext(currentMenu.getContext() + "\r\n" + tempString);
                    }
                } else {
                    currentMenu.setContextEndLine(line - 1);
                    for(int i=allMenus.size()-1;i>=0;i-- ){
                        MarkdownMenu menu1 = allMenus.get(i);
                        if(menu1.getLevel()<menu.getLevel()){
                            menu1.getSonMenus().add(menu);
                            menu.setPreMenu(menu1);
                            break;
                        }
                    }
                    allMenus.add(menu);
                    currentMenu = menu;
                    currentMenu.setMenuLine(line);
                    currentMenu.setContextStartLine(line+1);
                }
                line++;
            }
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

        for (MarkdownMenu menu : allMenus) {
            log.info(menu);
        }
        return null;
    }

    public boolean validateMarkdownTree(MarkdownTree markdownTree) {
        return true;
    }

    private MarkdownMenu line2Menu(String linetext) {
        MarkdownMenu menu = null;
        if (linetext.startsWith("# ")) {
            menu = new MarkdownMenu(1);
            menu.setName(linetext.substring(2));
        } else if (linetext.startsWith("## ")) {
            menu = new MarkdownMenu(2);
            menu.setName(linetext.substring(3));
        } else if (linetext.startsWith("### ")) {
            menu = new MarkdownMenu(3);
            menu.setName(linetext.substring(4));
        } else if (linetext.startsWith("#### ")) {
            menu = new MarkdownMenu(4);
            menu.setName(linetext.substring(5));
        } else if (linetext.startsWith("##### ")) {
            menu = new MarkdownMenu(5);
            menu.setName(linetext.substring(6));
        } else if (linetext.startsWith("###### ")) {
            menu = new MarkdownMenu(6);
            menu.setName(linetext.substring(7));
        }
        return menu;
    }

    public static void main(String[] args) {
        System.out.println("# 12".substring(2));
        log.info("hello,world");
        log.warn("warn");
        log.error("error");
        MarkdownImport markdownImport = new MarkdownImport();
        markdownImport.ImportFileToMarkdownTree(new File("/Users/mac/Library/Mobile Documents/com~apple~CloudDocs/文档集/3.技术文档集/3.3）编程技能/Java/java.md"));
    }
}
