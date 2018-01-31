package net.jayde.app.music.util;

import java.util.LinkedList;
import java.util.List;

public class Music163Group {
    String gid ;
    String gname;
    List<Music163Singer> singers = new LinkedList<Music163Singer>();

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public List<Music163Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Music163Singer> singers) {
        this.singers = singers;
    }

    public void showGroup(){
        System.out.println("\nGroup[id="+gid+",name="+gname+"]:");
        for (Music163Singer singer : singers) {
            System.out.println("  Singer[id="+singer.getSid()+",name="+singer.getSname()+"]");
        }
    }
}
