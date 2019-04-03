package com.jayde.util.markdownutil;

import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.bean.Block;
import com.jayde.util.markdownutil.mdtool.bean.ValuePart;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.apache.commons.collections4.list.TreeList;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.util.markdownutil
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-08 16:34
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-08 16:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
public @Data
class MarkdownMenuObject {
    MarkdownMenuObject pMenu = null;
    List<MarkdownMenuObject> sMenus = new LinkedList<>();
    List<String> sText = new LinkedList<>();
    int level;
    String menuName = "";

    Block block;
    List<MarkdownOtherObject> blockList = new TreeList<>();

    @Override
    public String toString() {
        return "MarkdownMenuObject{" +
                "level=" + level +
                ", menuName='" + menuName + '\'' +
                '}';
    }

    public void showMenuTree(String blank) {
        System.out.println(blank + menuName);
        for (MarkdownOtherObject otherObject : blockList) {
            otherObject.showOtherTree(blank + "  ");
        }

        for (MarkdownMenuObject menuObject : sMenus) {
            menuObject.showMenuTree(blank + "  ");
        }
    }

    public MarkdownMenuObject() {
        block = new Block();
        block.setType(BlockType.HEADLINE);
        block.setLevel(0);

        level = block.getLevel();
        menuName = "";
    }

    public MarkdownMenuObject(Block inputBlock) throws Exception {
        if (inputBlock.getType() == BlockType.HEADLINE) {
            block = inputBlock;
            level = block.getLevel();
//            ValuePart[] valueParts = block.getValueParts();
//            menuName = valueParts[0].getValue();
//            for (ValuePart valuePart : valueParts) {
//                contentText += valuePart.getValue();
//            }
        } else {
            throw new Exception();
        }
    }

    public void addSonMenu(MarkdownMenuObject sonMenu) {
        sMenus.add(sonMenu);
        sonMenu.setPMenu(this);
    }

    public void addSonObject(MarkdownOtherObject sonObject) {
        blockList.add(sonObject);
        sonObject.setPMenu(this);
//        if (sonObject.getBlock().getType() != BlockType.HEADLINE) {
//            ValuePart[] valueParts = sonObject.getBlock().getValueParts();
//            if (valueParts != null && valueParts.length > 0) {
//                for (ValuePart valuePart : valueParts) {
//                    contentText += valuePart.getValue();
//                }
//            }
//        }
    }


//    public MarkdownMenuObject(int inputLevel) {
//        level = inputLevel;
//        menuName = "";
//    }
//
//    public MarkdownMenuObject(String line) {
//        if (line.startsWith("# ")) {
//            level = 1;
//            menuName = line.substring(2);
//        } else if (line.startsWith("## ")) {
//            level = 2;
//            menuName = line.substring(3);
//        } else if (line.startsWith("### ")) {
//            level = 3;
//            menuName = line.substring(4);
//        } else if (line.startsWith("#### ")) {
//            level = 4;
//            menuName = line.substring(5);
//        } else if (line.startsWith("##### ")) {
//            level = 5;
//            menuName = line.substring(6);
//        } else if (line.startsWith("###### ")) {
//            level = 6;
//            menuName = line.substring(7);
//        }
//    }


}