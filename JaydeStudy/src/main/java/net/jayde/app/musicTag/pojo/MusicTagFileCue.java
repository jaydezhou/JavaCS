package net.jayde.app.musicTag.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicTagFileCue {

    private static Logger logger = LogManager.getLogger(MusicTagFileCue.class.getName());

    String name = "";
    String id = "";
    AbstractCueTag cueTag = null;
}
