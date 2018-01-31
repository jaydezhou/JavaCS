package net.jayde.app.music.dao.mybatisDao;

import net.jayde.app.music.dao.MusicDao;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicPerson;
import net.jayde.app.music.util.Music163Group;
import net.jayde.app.music.util.Music163Singer;
import net.jayde.app.music.util.ReadMusic163Singer;
import net.jayde.app.music.util.ReadWebSource;

import java.util.List;

public class TestMybatis {
    public static void main(String[] args) {
        MusicDao dao = new MusicDaoMybatis();
        MusicPerson mp = dao.getMusicPerson("11-1092");
        System.out.println(mp);
        ReadMusic163Singer readMusic163Singer = new ReadMusic163Singer();
        readMusic163Singer.getMusic163SingerAlbums(mp);
//        MusicGroup mg11 = dao.getMusicGroup("53");
////        dao.insertMusicPerson(mj);
//        ReadWebSource readWebSource = new ReadWebSource();
//        Music163Group music163Group = readWebSource.findGroup(mg11.getM163Id(), mg11.getName());
//        music163Group.showGroup();
//        List<Music163Singer> singers = music163Group.getSingers();
//        int i = 1;
//        for(Music163Singer singer:singers){
//            MusicPerson person = new MusicPerson();
//            person.setId(mg11.getId()+"-"+i++);
//            person.setName(singer.getSname().trim());
//            person.setM163Id(singer.getSid().trim());
//            person.setMgId(mg11.getId());
//            person.setFavourite(MusicPerson.PERSON_ALL);
//            dao.insertMusicPerson(person);
//        }

//        System.out.println(mg1);
//        MusicGroup mg11 = dao.getMusicGroup("11");
//        System.out.println(mg11);
//        List<MusicGroup> groups = dao.getSonMusicGroups(mg1.getId());
//        for(MusicGroup mg:groups){
//            System.out.println(mg);
//            List<MusicPerson> persons = dao.getSonMusicPersons(mg.getId());
//            for(MusicPerson mp:persons){
//                System.out.println("    "+mp);
//                List<MusicAlbum> albums = dao.getSonAlbums(mp.getId());
//                for(MusicAlbum ma :albums) {
//                    System.out.println("        "+ma);
//                }
//            }
//        }

    }
}
