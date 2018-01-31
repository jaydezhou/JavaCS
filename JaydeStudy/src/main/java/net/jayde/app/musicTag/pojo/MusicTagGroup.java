package net.jayde.app.musicTag.pojo;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicTagGroup {

    private static Logger logger = LogManager.getLogger(MusicTagGroup.class.getName());

    String name = "";
    String id = "";
    MusicTagGroup preGroup = null;
    List<MusicTagGroup> sonGroups = new LinkedList<>();
    List<MusicTagFile> sonFiles = new LinkedList<>();
}
