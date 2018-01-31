package net.jayde.app.music.util;

import net.jayde.app.music.dao.MusicDao;
import net.jayde.app.music.dao.mybatisDao.MusicDaoMybatis;
import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.pojo.MusicPerson;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadMusic163Singer extends ReadWebSource {
    private static final String STR_FORMAT = "*0000";
    private static DecimalFormat df = new DecimalFormat(STR_FORMAT);

    static String beginDesc = "<meta name=\"description\" content=\"";
    static String endDesc = "/>";

    static String beginSongli = "<ul class=\"f-hide\"><li>";
    static String beginSong = "<ul class=\"f-hide\">";
    static String endSong = "</ul>";

    public MusicPerson getMusic163SingerAlbums(MusicPerson person) {
        System.out.println("------------------");
//        String pageText = getSource("http://music.163.com/m/artist/album?id="+person.getM163Id()+"&limit=9999&offset=0" );
        String pageText = getSource("http://music.163.com/#/artist/album?id=6452&limit=9999");
        System.out.println(pageText);
        return person;
    }


    public MusicPerson getMusic163SingerFavourite(MusicPerson person) {
        Pattern pattern = Pattern.compile("<a.*?/a>");
        Pattern pattern1 = Pattern.compile("(id=)[\\d]{1,}");
        Pattern pattern2 = Pattern.compile(">(.*?)<");

//        System.out.println("http://music.163.com/artist?id=" + person.getM163Id());
        String pageText = getSource("http://music.163.com/artist?id=" + person.getM163Id());
        System.out.println(pageText);


        if (pageText.contains(beginDesc)) {
            int begin = pageText.indexOf(beginDesc);
            int end = pageText.indexOf(endDesc, begin + 1);
            String desc = pageText.substring(begin + beginDesc.length(), end).trim();
//            System.out.println(desc);
        }
        person.getFavouriteSet();
        if (pageText.contains(beginSongli)) {
            int i = 1;
            int begin = pageText.indexOf(beginSong);
            int end = pageText.indexOf(endSong, begin + 1);
            String songs = pageText.substring(begin + beginSong.length(), end).trim();
//            System.out.println(songs);
            Matcher mt = pattern.matcher(songs);
            while (mt.find()) {
                String song = mt.group();
//                System.out.println(song);
                String sid = "";
                Matcher matcher1 = pattern1.matcher(song);

                if (matcher1.find()) {
                    sid = matcher1.group().substring(3);
//                    System.out.println(sid);
                }
                Matcher matcher2 = pattern2.matcher(song);
                if (matcher2.find()) {
                    String sname = matcher2.group();
                    sname = sname.substring(1, sname.length() - 1);
//                    System.out.println(sname);
                    MusicFavourite musicFavourite = new MusicFavourite();
                    musicFavourite.setId(person.getId() + df.format(i++));
                    musicFavourite.setName(sname);
//                    musicFavourite.setMgId();
                    musicFavourite.setM163Id(sid);
                    musicFavourite.setMpid(person.getId());
//                    System.out.println(musicFavourite.getId());
                    person.getFavouriteSet().add(musicFavourite);
                }
            }
        }
        System.out.println(person);
        MusicSaveDb saveDb = new MusicSaveDb();
        saveDb.insertMusicFavourite(person);
        return person;
    }


    public static void main(String[] args) {
        ReadMusic163Singer readMusic163Singer = new ReadMusic163Singer();
        MusicDao dao = new MusicDaoMybatis();
        MusicLibrary library = dao.iniMusicLibrary();
        for (MusicGroup mg : library.getGroups()) {
            for (MusicPerson mp : mg.getSonPersons()) {
//                readMusic163Singer.getMusic163SingerFavourite(mp);
                System.out.println(mp);
                readMusic163Singer.getMusic163SingerAlbums(mp);
//                System.out.println(mp);
            }
        }
//
//        readMusic163Singer.getMusic163Singer(person);
    }
}
