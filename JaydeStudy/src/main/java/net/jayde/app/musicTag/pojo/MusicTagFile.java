package net.jayde.app.musicTag.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicTagFile {

  private static Logger logger = LogManager.getLogger(MusicTagFile.class.getName());
  static int ALBUM_FILE = 1;
  static int TRACK_FILE = 2;
  static int CUE_FILE = 3;
  static int OTHER_FILE = 4;
  String name = "";
  String id = "";
  int fileType = 4;
  String filepath = "";
  MusicTagGroup preGroup = null;
  MusicTagFileAlbum albumTag = null;
  MusicTagFileTrack trackTag = null;
  MusicTagFileCue cueTag = null;
  MusicTagFileOther otherTag = null;

}
