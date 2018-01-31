package net.jayde.app.music.temp;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class MoveAlbumFile {

  private static Logger logger = LogManager.getLogger(MoveAlbumFile.class.getName());

  Map<String, FileInfo> maps = new HashMap<>();

  String pathName = "D:\\音频\\SS4\\500";

  public MoveAlbumFile(){
        File path = new File(pathName);
        String[] files = path.list();
        for (String filename:files){
//            System.out.println(filename);
            int i = filename.indexOf("《");
//            System.out.println(i);
            String artistName = filename.substring(0,i);
//            System.out.println(artistName);
            if(maps.containsKey(artistName)){
                FileInfo fileInfo = maps.get(artistName);
                fileInfo.getFileName().add(filename);
            }else{
                FileInfo fileInfo = new FileInfo();
                fileInfo.setArtistName(artistName);
                fileInfo.getFileName().add(filename);
                maps.put(artistName,fileInfo);
            }
        }

        for(String artist:maps.keySet()){
            System.out.println("mkdir \""+artist+"\"");
            System.out.println("cd \""+artist+"\"");
            System.out.println("mkdir \"★"+artist+"精选★\"");
            System.out.println("mkdir \"★"+artist+"专辑★\"");
            System.out.println("cd \"★"+artist+"专辑★\"");
            FileInfo fileInfo = maps.get(artist);
            for (String filename:fileInfo.getFileName()){
            }
            System.out.println("cd ..");
            System.out.println("cd ..");
        }
    }

  public static void main(String[] args) {
    MoveAlbumFile moveAlbumFile = new MoveAlbumFile();
  }

}


class FileInfo {
  String artistName="";
  Set<String> fileName=new HashSet<>();

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

    public Set<String> getFileName() {
        return fileName;
    }

    public void setFileName(Set<String> fileName) {
        this.fileName = fileName;
    }
}
