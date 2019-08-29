package com.jayde.apps.apprk;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.apprk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-07-02 22:11
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-07-02 22:11
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoC {
    String cid;
    String cname;
    String pid;
    String ctext;

    BoC parentChapter = null;
    List<BoC> listSonChapters = new ArrayList<>();

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public BoC getParentChapter() {
        return parentChapter;
    }

    public void setParentChapter(BoC parentChapter) {
        this.parentChapter = parentChapter;
    }

    public List<BoC> getListSonChapters() {
        return listSonChapters;
    }

    public void setListSonChapters(List<BoC> listSonChapters) {
        this.listSonChapters = listSonChapters;
    }

    public String getCtext() {
        return ctext;
    }

    public void setCtext(String ctext) {
        this.ctext = ctext;
    }

    @Override
    public String toString() {
        String text = "BoC{" +
                "cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", pid='" + pid + '\'' +
                '}';
        for (BoC sonBoc : listSonChapters) {
            text += "\n\r  " + sonBoc;
        }
        return text;
    }


}
