package com.jayde.apps.appMarkdown.bo;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class MarkdownMenu {
    MarkdownMenu preMenu = null;
    String context = "";
    int level = 0;
    String name = "";

    int menuLine = 0;
    int contextStartLine = 0;
    int contextEndLine = 0;


    List<MarkdownMenu> sonMenus = new ArrayList<>();

    public MarkdownMenu(int iLevel) {
        level = iLevel;
    }

    public MarkdownMenu(int iLevel, String sContext) {
        this(iLevel);
        context = sContext;
    }

    @Override
    public String toString() {
        if(preMenu==null)
            return "MarkdownMenu{" +
                    "preMenu=" +
                    ", level=" + level +
                    ", name='" + name + '\'' +
                    '}';
        else
            return "MarkdownMenu{" +
                    "preMenu=" + preMenu.getName() +
                    ", level=" + level +
                    ", name='" + name + '\'' +
                    ", line='" + menuLine+ '\'' +
                    ", sline='" + contextStartLine+ '\'' +
                    ", eline='" + contextEndLine+ '\'' +
                    '}';

    }
}
