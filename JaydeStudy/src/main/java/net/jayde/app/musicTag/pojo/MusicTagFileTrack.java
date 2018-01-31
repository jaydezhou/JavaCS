package net.jayde.app.musicTag.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicTagFileTrack {

  private static Logger logger = LogManager.getLogger(MusicTagFileTrack.class.getName());

  String name = "";
  String id = "";

  MusicTagFile preFile = null;
  AbstractTrackTag trackTag = null;

}
