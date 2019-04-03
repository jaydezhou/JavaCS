package com.jayde.util.markdownutil;

import com.jayde.util.markdownutil.mdtool.BlockType;
import com.jayde.util.markdownutil.mdtool.bean.Block;
import lombok.Data;
import lombok.extern.log4j.Log4j;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.util.markdownutil
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-03-08 16:54
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-03-08 16:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Log4j
@Data
public class MarkdownOtherObject {
    MarkdownMenuObject pMenu = null;
    Block block;

    public MarkdownOtherObject(Block inputBlock) throws Exception {
        if (inputBlock.getType() != BlockType.HEADLINE) {
            block = inputBlock;
        } else {
            throw new Exception();
        }
    }

    public void showOtherTree(String blank) {
        System.out.println(blank + block.getType()+"   "+block.toString());
        log.warn(block.contentText);
    }
}
