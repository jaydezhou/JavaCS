package net.jayde.app.music.util;

import net.jayde.app.music.dao.mybatisDao.MusicDaoMybatis;
import net.jayde.app.music.pojo.MusicAlbum;
import net.jayde.app.music.pojo.MusicPerson;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ReadMusic163Alubms {
    public static String filePath = "/Users/zhouyongjian/Desktop/163";

    public static List<MusicAlbum> readAlbums(MusicPerson musicPerson) {
        List<MusicAlbum> list = readAlbums(musicPerson.getM163Id());
        for (MusicAlbum album : list) {
            album.setMpId(musicPerson.getId());
            album.setMgId(musicPerson.getMgId());
            album.setId(musicPerson.getId() + "-" + album.getM163Id());
        }
        return list;
    }

    private static List<MusicAlbum> readAlbums(String m163id) {
        List<MusicAlbum> listAblums = new LinkedList<MusicAlbum>();
        String fileName = filePath + "/album-" + m163id + ".xml";
        Document doc = null;
        try {
            SAXReader reader = new SAXReader();
            doc = reader.read(new File(fileName));// 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            List<Element> listLi = rootElt.elements("li");
            for (Element eleLi : listLi) {
                System.out.println("----------------------------------");
                MusicAlbum album = readAlbum(eleLi);
                listAblums.add(album);
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }
        return listAblums;
    }

    public static MusicAlbum readAlbum(Element eleLi) {
        MusicAlbum album = new MusicAlbum();
        List<Element> listSonEles = eleLi.elements();
        for (Element ele : listSonEles) {
            if (ele.getName().equals("div")) {
                album.setName(ele.attributeValue("title"));
//                System.out.println(ele.attributeValue("title"));
                Element eleImg = ele.element("img");
                album.setImageUrl(eleImg.attributeValue("src"));
            } else {
                if (ele.getName().equals("p") && ele.attributeCount() == 2) {
                    Element eleA = ele.element("a");
                    String hrefId = eleA.attributeValue("href");
                    hrefId = hrefId.replace("/album?id=", "");
                    album.setM163Id(hrefId);
                } else {
                    Element eleSpan = ele.element("span");
                    album.setYmd(eleSpan.getStringValue());
                }
            }

        }
        return album;
    }

    public static void main(String[] args) {
        MusicDaoMybatis dao = new MusicDaoMybatis();
        MusicPerson mp = dao.getMusicPerson("11-101");
        List<MusicAlbum> list = ReadMusic163Alubms.readAlbums(mp);
        for (MusicAlbum musicAlbum : list) {
            System.out.println(musicAlbum);
            dao.insertSonAlbum(musicAlbum);
        }
    }
}
