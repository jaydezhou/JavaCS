package com.jayde.apps.appKnowledgeLibrary.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appKnowledgeLibrary.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-01-19 22:36
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-01-19 22:36
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Data
public class RedmineIssueGW {
    public static int lenth = 9;
    private String issueId;
    private String subject;
    private String yw;
    private String fy;
    private String zs;
    private String other[];

    public RedmineIssueGW() {
        other = new String[lenth];
    }

    public void setOtherText(int pos, String text) {
        other[pos] = text;
    }
}
