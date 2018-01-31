package net.jayde.app.musicTag.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * parse cue file tool eg： File cueFile = new File("/sdcard/test.cue"); CueFileBean bean =
 * utilCue.parseCueFile(cueFile);
 *
 * PERFORMER "test" TITLE "10.Moonlight+ShadowWinner" FILE "10.Moonlight ShadowWinner.ape" WAVE
 * TRACK 01 AUDIO TITLE "La lettre" PERFORMER "Lara Fabian" INDEX 01 00:00:00 TRACK 02 AUDIO TITLE
 * "Un ave maria" PERFORMER "Lara Fabian" INDEX 00 03:52:57 INDEX 01 03:52:99 TRACK 03 AUDIO TITLE
 * "Si tu n'as pas d'amour" PERFORMER "Lara Fabian" INDEX 00 08:50:49 INDEX 01 08:50:65 TRACK 04
 * AUDIO TITLE "Il ne manquait que toi" PERFORMER "Lara Fabian" INDEX 00 12:36:17 INDEX 01 12:40:19
 * 
 * @author xuweilin
 *
 */
public class CueUtil {

  private static Logger logger = LogManager.getLogger(CueUtil.class.getName());
  private static String TAG = "utilCue";

  /**
   * parse cue file
   * 
   * @param cueFile file
   * @return CueFileBean
   */
  public static CueFileBean parseCueFile(File cueFile) {
    LineNumberReader reader = null;
    CueFileBean cueFileBean = new CueFileBean();
    List<CueSongBean> songs = new ArrayList<CueSongBean>();
    CueSongBean cueSongBean = new CueSongBean();
    boolean parseSong = false;
    int songIndex = 0;
    try {
      reader = new LineNumberReader(new InputStreamReader(new FileInputStream(cueFile), "UTF-8"));
      while (true) {
        String s = new String();
        s = reader.readLine();
        if (s != null) {
          if (!parseSong && s.trim().toUpperCase().startsWith("PERFORMER")) {
            cueFileBean.setPerformer(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
          }
          if (!parseSong && s.trim().toUpperCase().startsWith("TITLE")) {
            cueFileBean.setAlbumName(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
          }
          if (s.trim().toUpperCase().startsWith("FILE")) {
            cueFileBean.setFileName(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
          }
          if (s.trim().toUpperCase().startsWith("TRACK")) {
            parseSong = true;
            songIndex++;
          }
          if (parseSong && s.trim().toUpperCase().startsWith("TITLE")) {
            cueSongBean.setTitle(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
          }
          if (parseSong && s.trim().toUpperCase().startsWith("PERFORMER")) {
            cueSongBean.setPerformer(s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\"")));
          }
          if (songIndex == 1 && s.trim().toUpperCase().startsWith("INDEX")) {
            cueSongBean.setIndexBegin(s.trim().split(" 01 ")[1].trim());
          }
          if (songIndex > 1 && s.trim().toUpperCase().startsWith("INDEX")) {
            if (s.trim().contains(" 00 ")) {
              songs.get(songIndex - 2).setIndexEnd(s.trim().split(" 00 ")[1].trim());
            }
            if (s.trim().contains(" 01 ")) {
              cueSongBean.setIndexBegin(s.trim().split(" 01 ")[1].trim());
            }
          }
          if (songIndex >= 1 && s.trim().toUpperCase().startsWith("INDEX") && s.trim().contains(" 01 ")) {
            songs.add(cueSongBean);
            cueSongBean = new CueSongBean();
          }
        } else {
          cueFileBean.setSongs(songs);
          break;
        }
      }

    } catch (UnsupportedEncodingException e) {} catch (FileNotFoundException e) {} catch (IOException e) {} finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (Exception e) {
        // Log.e(TAG, "Exception:"+e.getMessage());
      }
    }
    return cueFileBean;
  }

  public static void main(String[] args) {
    String filename = "D:\\music\\java\\flac_all.cue";
    CueFileBean cueFileBean = CueUtil.parseCueFile(new File(filename));
    System.out.println(cueFileBean.getAlbumName());
    System.out.println(cueFileBean.getPerformer());
    System.out.println(cueFileBean.getSongs());
    System.out.println(cueFileBean.getSongs().get(2));
    System.out.println(cueFileBean);
  }
}
