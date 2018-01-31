package net.jayde.app.music.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ReadWebSource {

  public String getSource(String urlString) {
    try {
      String source = "";
      // 生成一个URL对象
      URL url = new URL(urlString);
      // 打开URL
      HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
      // 得到输入流，即获得了网页的内容
      BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
      String line;
      // 读取输入流的数据，并显示
      while ((line = reader.readLine()) != null) {
        // System.out.println(line);
        source = source + "\n" + line;
      }
      return source;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      return "";
    }
  }
  public Music163Singer findSinger(String sid) {
    Music163Singer singer= new Music163Singer();
    singer.setSid(sid);
    String  urlString = "http://music.163.com/artist?id="+sid;
    String fullText = getSource(urlString);
    System.out.println(fullText);
    return singer;
  }
  public List<Music163Singer> splitPerson(String fullText) {
    // System.out.println(fullText);
    List<Music163Singer> persons = new LinkedList<Music163Singer>();
    String startStr = "<a href=\"/artist?id=";
    String endStr = "</a>";
    String[] fullTexts = fullText.split("\n");
    String startId = "id=";
    String endId = "\"";
    String startName = ">";
    String endName = "<";
    for (String line : fullTexts) {
      line = line.trim();
      // if (line.startsWith(startStr) && line.endsWith(endStr)) {
      if (line.contains("nm nm-icn f-thide s-fc0")) {
        // System.out.println(line);
        int sId = line.indexOf(startId);
        int eId = line.indexOf(endId, sId + 1);
        String id = line.substring(sId + 3, eId);
        // System.out.println(id);
        int sName = line.indexOf(startName);
        int eName = line.indexOf(endName, sName + 1);
        String name = line.substring(sName + 1, eName);
        // System.out.println(name);
        Music163Singer singer = new Music163Singer();
        singer.setSid(id);
        singer.setSname(name);
        persons.add(singer);
      }
    }
    System.out.println("singers:"+persons.size());
    return persons;
  }

  public Music163Group findGroup(String gid, String gname) {
    Music163Group groud = new Music163Group();
    String[] orders = {"0"
    , "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79",
        "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90"};
    String urlString = "";
    groud.setGid(gid);
    groud.setGname(gname);
    for (int i = 0; i < orders.length; i++) {
      urlString = "http://music.163.com/discover/artist/cat?id=" + gid + "&initial=" + orders[i];
      System.out.println(urlString);
      groud.getSingers().addAll(splitPerson(getSource(urlString)));
    }
    return groud;
  }


  public static void main(String[] args) {
    ReadWebSource readWebSource = new ReadWebSource();

    String[] groupIds = {"1001"};
//    , "1002", "1003", "2001", "2002", "2003", "6001", "6002", "6003", "7001", "7002", "7003",
//        "4001", "4002", "4003"};
    String[] groupNames = {"华男"};
//    , "华女", "华组", "欧男", "欧女", "欧组", "日男", "日女", "日组", "韩男", "韩女", "韩组", "他男", "她女", "它组"};
//    Music163Group[] groups = new Music163Group[groupIds.length];
//    for (int i = 0; i < groups.length; i++) {
//      groups[i] = readWebSource.findGroup(groupIds[i], groupNames[i]);
//      groups[i].showGroup();
//    }
    readWebSource.findSinger("1159129");
    // String fulltext =
    // readWebSource.getSource("http://music.163.com/discover/artist/cat?id=1001&initial=66");
    // readWebSource.splitPerson(fulltext);
  }
}


