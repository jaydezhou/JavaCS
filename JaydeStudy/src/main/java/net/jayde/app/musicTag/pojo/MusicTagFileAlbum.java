package net.jayde.app.musicTag.pojo;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicTagFileAlbum {

    private static Logger logger = LogManager.getLogger(MusicTagFileAlbum.class.getName());

    String name = "";
    String id = "";

    MusicTagFile preFile = null;
    AbstractAlbumTag albumTag = null;

}
